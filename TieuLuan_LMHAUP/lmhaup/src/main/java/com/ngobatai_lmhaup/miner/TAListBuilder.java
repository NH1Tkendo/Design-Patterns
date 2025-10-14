package com.ngobatai_lmhaup.miner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ngobatai_lmhaup.bounds.MRAUCalculator;
import com.ngobatai_lmhaup.struct.TAList;
import com.ngobatai_lmhaup.struct.TAListEntry;
import com.ngobatai_lmhaup.struct.TAListFactory;

/**
 * Class để build và process TA-Lists
 */
public class TAListBuilder {

    private final TAListFactory factory;
    private final MRAUCalculator mrauCalc;
    private final CacheManager cacheManager;
    private final HAUPChecker haupChecker;

    public TAListBuilder(TAListFactory factory, MRAUCalculator mrauCalc,
            CacheManager cacheManager, HAUPChecker haupChecker) {
        this.factory = factory;
        this.mrauCalc = mrauCalc;
        this.cacheManager = cacheManager;
        this.haupChecker = haupChecker;
    }

    // Build TA-Lists cho tất cả 1-itemsets
    public Map<Integer, TAList> buildOneItemTALists() {
        Map<Integer, TAList> oneLists = factory.TAList1Item();

        printOneItemTALists(oneLists);
        cacheOneItemUtilities(oneLists);

        return oneLists;
    }

    // Process 1-itemsets: kiểm tra HAUP và tạo seeds
    public List<TAList> processOneItemsets(Map<Integer, TAList> oneLists, List<Integer> orderList) {
        List<TAList> seeds = new ArrayList<>();
        System.out.println("========== 1-ITEMSET ANALYSIS ==========");

        for (int item : orderList) {
            TAList L = oneLists.get(item);
            if (L == null)
                continue;

            double mrau = mrauCalc.mrauOf(L);
            double au = mrauCalc.auOf(L);

            System.out.printf("Item %d: mrau=%.2f, au=%.2f", item, mrau, au);

            haupChecker.checkAndAdd(L.itemset, au);

            seeds.add(L);
            System.out.println(" -> Added to seeds");
        }
        System.out.println();
        return seeds;
    }

    // In thông tin TA-Lists của 1-itemsets
    private void printOneItemTALists(Map<Integer, TAList> oneLists) {
        System.out.println("========== TA-LISTS FOR 1-ITEMS ==========");
        for (Map.Entry<Integer, TAList> entry : oneLists.entrySet()) {
            int itemId = entry.getKey();
            TAList taList = entry.getValue();

            System.out.println("\n--- Item " + itemId + " ---");
            System.out.println("+---------+----------+----------+--------+");
            System.out.println("|   TID   |   util   |   sRLU   | nRLUI  |");
            System.out.println("+---------+----------+----------+--------+");
            for (TAListEntry e : taList.entries) {
                System.out.printf("| %7d | %8.2f | %8.2f | %6d |%n",
                        e.tid, e.util, e.sRLU, e.nRLUI);
            }
            System.out.println("+---------+----------+----------+--------+");
        }
        System.out.println();
    }

    /**
     * Cache utilities của 1-itemsets
     */
    private void cacheOneItemUtilities(Map<Integer, TAList> oneLists) {
        cacheManager.cacheMultiple(oneLists.values());
    }
}
