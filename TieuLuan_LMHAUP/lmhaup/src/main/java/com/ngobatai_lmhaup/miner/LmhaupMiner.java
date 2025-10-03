package com.ngobatai_lmhaup.miner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.ngobatai_lmhaup.bounds.MRAUCalculator;
import com.ngobatai_lmhaup.bounds.TMAUBCaculator;
import com.ngobatai_lmhaup.model.UtilityDatabase;
import com.ngobatai_lmhaup.struct.TAList;
import com.ngobatai_lmhaup.struct.TAListFactory;

public class LmhaupMiner {

    private final UtilityDatabase db;
    private final double minutil; // = U(DB) * delta
    private final TMAUBCaculator tmaubCalc = new TMAUBCaculator();
    private final MRAUCalculator mrauCalc = new MRAUCalculator();
    private final Joiner joiner = new Joiner();

    // results
    public final List<int[]> haupItemsets = new ArrayList<>();
    public final List<Double> haupAU = new ArrayList<>();

    public LmhaupMiner(UtilityDatabase db, double delta) {
        this.db = db;
        this.minutil = db.totalUtilityDB * delta;
    }

    public void mine() {
        // 1) First scan: compute tmaub and support to define promising 1-items and
        // global order
        TMAUBCaculator.TmaubStats st = tmaubCalc.compute(db);
        // choose promising items
        Set<Integer> promising = st.tmaubPerItem.entrySet().stream()
                .filter(e -> e.getValue() >= minutil)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        // build global order by ascending support
        List<Integer> orderList = promising.stream()
                .sorted(Comparator.comparingInt(i -> st.support.getOrDefault(i, 0)))
                .collect(Collectors.toList());
        int[] order = new int[1 + db.items.keySet().stream().mapToInt(i -> i).max().orElse(0)];
        int[] rev = new int[orderList.size()];
        for (int r = 0; r < orderList.size(); r++) {
            int item = orderList.get(r);
            order[item] = r;
            rev[r] = item;
        }
        db.itemOrder = order;
        db.orderToItem = rev;

        // 2) Second scan: build TA-List 1-item
        TAListFactory factory = new TAListFactory(db, promising, order);
        Map<Integer, TAList> oneLists = factory.TAList1Item();

        // 3) Filter 1-items by mrau to start DFS
        List<TAList> seeds = new ArrayList<>();
        for (int item : orderList) {
            TAList L = oneLists.get(item);
            if (L == null)
                continue;
            double mrau = mrauCalc.mrauOf(L);
            if (mrau >= minutil)
                seeds.add(L);
        }

        // 4) DFS TA-Miner
        for (int i = 0; i < seeds.size(); i++) {
            TAList prefix = seeds.get(i);
            // output if au >= minutil
            double au = mrauCalc.auOf(prefix);
            if (au >= minutil) {
                haupItemsets.add(prefix.itemset);
                haupAU.add(au);
            }
            // extend with following seeds by order
            List<TAList> suffixes = new ArrayList<>();
            for (int j = i + 1; j < seeds.size(); j++) {
                TAList next = seeds.get(j);
                TAList joined = joiner.join(prefix, next, 0);
                double mrau = mrauCalc.mrauOf(joined);
                if (mrau >= minutil)
                    suffixes.add(joined);
                // else pruned
            }
            // recursive mining
            taMiner(prefix, suffixes);
        }
    }

    private void taMiner(TAList prefix, List<TAList> exts) {
        for (int i = 0; i < exts.size(); i++) {
            TAList P = exts.get(i);
            double au = mrauCalc.auOf(P);
            if (au >= minutil) {
                haupItemsets.add(P.itemset);
                haupAU.add(au);
            }
            // build candidates by joining with following extensions sharing prefix
            List<TAList> nextLevel = new ArrayList<>();
            for (int j = i + 1; j < exts.size(); j++) {
                TAList Q = exts.get(j);
                // common prefix length = prefix.len
                TAList R = joiner.join(P, Q, prefix.len);
                double mrau = mrauCalc.mrauOf(R);
                if (mrau >= minutil)
                    nextLevel.add(R);
            }
            if (!nextLevel.isEmpty())
                taMiner(P, nextLevel);
        }
    }
}