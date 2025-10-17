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
    private static final int MAX_CACHE_SIZE = 100000; // Tăng lên 100K để cache tốt hơn
    private static final int MAX_DEPTH = 8;
    private static final int MAX_PATTERNS_PER_LEVEL = 2000;
    private static final int MAX_HAUPS = 10000;

    // Các thành phần cốt lõi
    private final UtilityDatabase db;
    private final double minutil;
    private final Joiner joiner = new Joiner();

    // TMAUB stats
    private TMAUBCaculator.TmaubStats tmaubStats;

    // MRAUCalculator for AU calculation
    private final MRAUCalculator mrauCalc = new MRAUCalculator();

    // Các lớp helper
    private final CacheManager cacheManager;
    private final HAUPChecker haupChecker;
    private final MiningStatistics statistics;
    private final ItemOrderBuilder orderBuilder;
    private final PatternGenerator patternGenerator;

    // Lưu trữ các haup
    public final List<int[]> haupItemsets;
    public final List<Double> haupAU;

    // Constructor với delta (%)
    public LmhaupMiner(UtilityDatabase db, double delta) {
        this.db = db;
        double number = db.totalUtilityDB * delta;
        this.minutil = Math.round(number * 100.0) / 100.0;

        // Khởi tạo các lớp helper
        this.cacheManager = new CacheManager(MAX_CACHE_SIZE);
        this.haupChecker = new HAUPChecker(minutil, MAX_HAUPS);
        this.statistics = new MiningStatistics();
        this.orderBuilder = new ItemOrderBuilder(db, minutil);
        this.patternGenerator = new PatternGenerator(joiner, cacheManager, null);

        joiner.setUtilityCache(cacheManager.getCache());

        this.haupItemsets = haupChecker.getHAUPItemsets();
        this.haupAU = haupChecker.getHAUPAU();
    }

    // Constructor với minutil tuyệt đối
    public LmhaupMiner(UtilityDatabase db, double minutil, boolean isAbsolute) {
        this.db = db;
        this.minutil = minutil;

        // Khởi tạo các lớp helper
        this.cacheManager = new CacheManager(MAX_CACHE_SIZE);
        this.haupChecker = new HAUPChecker(minutil, MAX_HAUPS);
        this.statistics = new MiningStatistics();
        this.orderBuilder = new ItemOrderBuilder(db, minutil);
        this.patternGenerator = new PatternGenerator(joiner, cacheManager, null);

        joiner.setUtilityCache(cacheManager.getCache());

        this.haupItemsets = haupChecker.getHAUPItemsets();
        this.haupAU = haupChecker.getHAUPAU();
    }

    public void mine() {
        printMiningConfig();
        MemoryMonitor.printMemoryUsage("Start");

        // 1) Tính TMAUB và lọc ra các promising item
        TMAUBCaculator tmaubCalc = new TMAUBCaculator();
        tmaubStats = tmaubCalc.compute(db);
        Set<Integer> promising = orderBuilder.filterPromisingItems(tmaubStats);
        List<Integer> orderList = orderBuilder.buildOrder(tmaubStats, promising);

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

            // Generate extensions using TMAUB upper bound
            double tmaub = calculateTMAUB(pattern.itemset);
            if (tmaub >= minutil) {
                List<TAList> extensions = patternGenerator.generateExtensions(patterns, i, currentPrefixLen);
                if (!extensions.isEmpty()) {
                    taMiner(extensions, pattern.len);
                }
            }
        }
    }

    private boolean checkDepthLimit(List<TAList> patterns) {
        int currentDepth = patterns.isEmpty() ? 0 : patterns.get(0).len;
        statistics.setCurrentDepth(currentDepth);

        if (currentDepth > MAX_DEPTH) {
            System.out.println("⚠ Reached max depth limit: " + MAX_DEPTH);
            return false;
        }
        return true;
    }

    private List<TAList> applyPatternsLimit(List<TAList> patterns) {
        if (patterns.size() > MAX_PATTERNS_PER_LEVEL) {
            System.out.printf("⚠ Too many patterns at depth %d (%d > %d), limiting...%n",
                    statistics.getCurrentDepth(), patterns.size(), MAX_PATTERNS_PER_LEVEL);
            return patterns.subList(0, MAX_PATTERNS_PER_LEVEL);
        }
        return patterns;
    }

    /**
     * Calculate TMAUB for an itemset by summing TMAUB values of all items
     */
    private double calculateTMAUB(int[] itemset) {
        double tmaub = 0.0;
        for (int item : itemset) {
            tmaub += tmaubStats.tmaubPerItem.getOrDefault(item, 0.0);
        }
        return tmaub;
    }
}
