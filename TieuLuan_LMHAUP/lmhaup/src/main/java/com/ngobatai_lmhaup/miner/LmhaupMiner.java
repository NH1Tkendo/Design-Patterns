package com.ngobatai_lmhaup.miner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ngobatai_lmhaup.bounds.MRAUCalculator;
import com.ngobatai_lmhaup.bounds.TMAUBCaculator;
import com.ngobatai_lmhaup.model.UtilityDatabase;
import com.ngobatai_lmhaup.struct.TAList;
import com.ngobatai_lmhaup.struct.TAListFactory;
import com.ngobatai_lmhaup.util.MemoryMonitor;

public class LmhaupMiner {

    // Giới hạn kết quả đầu ra
    private static final int MAX_CACHE_SIZE = 5000;
    private static final int MAX_DEPTH = 8;
    private static final int MAX_PATTERNS_PER_LEVEL = 2000;
    private static final int MAX_HAUPS = 10000;

    // Các thành phần cốt lõi
    private final UtilityDatabase db;
    private final double minutil;
    private final TMAUBCaculator tmaubCalc = new TMAUBCaculator();
    private final MRAUCalculator mrauCalc = new MRAUCalculator();
    private final Joiner joiner = new Joiner();

    // Các lớp helper
    private final CacheManager cacheManager;
    private final HAUPChecker haupChecker;
    private final MiningStatistics statistics;
    private final ItemOrderBuilder orderBuilder;
    private final PatternGenerator patternGenerator;

    // Lưu trữ các haup
    public final List<int[]> haupItemsets;
    public final List<Double> haupAU;

    public LmhaupMiner(UtilityDatabase db, double delta) {
        // 1. Gán db và minutil
        this.db = db;
        double number = db.totalUtilityDB * delta;
        this.minutil = Math.round(number * 100.0) / 100.0;

        // 2. Khởi tạo các lớp helper
        this.cacheManager = new CacheManager(MAX_CACHE_SIZE);
        this.haupChecker = new HAUPChecker(minutil, MAX_HAUPS);
        this.statistics = new MiningStatistics();
        this.orderBuilder = new ItemOrderBuilder(db, minutil);
        this.patternGenerator = new PatternGenerator(joiner, cacheManager, null); // itemOrder will be set later

        joiner.setUtilityCache(cacheManager.getCache());

        this.haupItemsets = haupChecker.getHAUPItemsets();
        this.haupAU = haupChecker.getHAUPAU();
    }

    public LmhaupMiner(UtilityDatabase db, double minutil, boolean isAbsolute) {
        this.db = db;
        if (isAbsolute) {
            this.minutil = minutil;
        } else {
            double number = db.totalUtilityDB * minutil;
            this.minutil = Math.round(number * 100.0) / 100.0;
        }

        // Khởi tạo lớp helper
        this.cacheManager = new CacheManager(MAX_CACHE_SIZE);
        this.haupChecker = new HAUPChecker(this.minutil, MAX_HAUPS);
        this.statistics = new MiningStatistics();
        this.orderBuilder = new ItemOrderBuilder(db, this.minutil);
        this.patternGenerator = new PatternGenerator(joiner, cacheManager, null); // itemOrder will be set later

        joiner.setUtilityCache(cacheManager.getCache());

        this.haupItemsets = haupChecker.getHAUPItemsets();
        this.haupAU = haupChecker.getHAUPAU();
    }

    public void mine() {
        printMiningConfig();
        MemoryMonitor.printMemoryUsage("Start");

        // 1) Tính TMAUB và lọc ra các promising item
        TMAUBCaculator.TmaubStats st = tmaubCalc.compute(db);
        Set<Integer> promising = orderBuilder.filterPromisingItems(st);
        List<Integer> orderList = orderBuilder.buildOrder(st, promising);

        // Set item order for pattern generator after it's been built
        patternGenerator.setItemOrder(db.itemOrder);

        // 2) Xây dựng các TA-List với 1 item
        TAListFactory factory = new TAListFactory(db, promising, db.itemOrder);
        TAListBuilder taListBuilder = new TAListBuilder(factory, mrauCalc, cacheManager, haupChecker);

        Map<Integer, TAList> oneLists = taListBuilder.buildOneItemTALists();
        List<TAList> seeds = taListBuilder.processOneItemsets(oneLists, orderList);

        // 3) Mining larger patterns
        System.out.println("========== MINING LARGER PATTERNS ==========");
        List<TAList> twoItemsets = patternGenerator.generateTwoItemsets(seeds);

        if (!twoItemsets.isEmpty()) {
            taMiner(twoItemsets, 1);
        }

        // Print statistics
        statistics.printFinalStatistics(cacheManager.size());
        MemoryMonitor.printMemoryUsage("End");
    }

    // In cấu hình
    private void printMiningConfig() {
        System.out.println("minutil: " + minutil);
        System.out.println("Max cache size: " + MAX_CACHE_SIZE);
        System.out.println("Max depth: " + MAX_DEPTH);
        System.out.println("Max HAUPs: " + MAX_HAUPS);
    }

    /**
     * Recursive mining method
     */
    private void taMiner(List<TAList> patterns, int currentPrefixLen) {
        // Check và áp dụng limits
        if (!checkDepthLimit(patterns))
            return;
        patterns = applyPatternsLimit(patterns);

        for (int i = 0; i < patterns.size(); i++) {
            TAList pattern = patterns.get(i);
            statistics.incrementPatternsChecked();

            // Print progress
            statistics.printProgress(
                    statistics.getCurrentDepth(),
                    haupChecker.getHAUPCount(),
                    cacheManager.size());

            // Check HAUP
            double au = mrauCalc.auOf(pattern);
            if (haupChecker.checkAndAdd(pattern.itemset, au)) {
                statistics.incrementHAUPsFound();
            }

            // Generate extensions
            double mrau = mrauCalc.mrauOf(pattern);
            if (mrau >= minutil) {
                List<TAList> extensions = patternGenerator.generateExtensions(patterns, i, currentPrefixLen);
                if (!extensions.isEmpty()) {
                    taMiner(extensions, pattern.len);
                }
            }
        }
    }

    /**
     * Kiểm tra depth limit
     */
    private boolean checkDepthLimit(List<TAList> patterns) {
        int currentDepth = patterns.isEmpty() ? 0 : patterns.get(0).len;
        statistics.setCurrentDepth(currentDepth);

        if (currentDepth > MAX_DEPTH) {
            System.out.println("⚠ Reached max depth limit: " + MAX_DEPTH);
            return false;
        }
        return true;
    }

    /**
     * Áp dụng giới hạn số lượng patterns mỗi level
     */
    private List<TAList> applyPatternsLimit(List<TAList> patterns) {
        if (patterns.size() > MAX_PATTERNS_PER_LEVEL) {
            System.out.printf("⚠ Too many patterns at depth %d (%d > %d), limiting...%n",
                    statistics.getCurrentDepth(), patterns.size(), MAX_PATTERNS_PER_LEVEL);
            return patterns.subList(0, MAX_PATTERNS_PER_LEVEL);
        }
        return patterns;
    }
}
