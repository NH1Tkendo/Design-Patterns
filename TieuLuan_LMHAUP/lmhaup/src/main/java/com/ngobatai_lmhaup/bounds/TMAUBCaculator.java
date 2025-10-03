package com.ngobatai_lmhaup.bounds;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ngobatai_lmhaup.model.Transaction;
import com.ngobatai_lmhaup.model.TransactionItem;
import com.ngobatai_lmhaup.model.UtilityDatabase;

public class TMAUBCaculator {
    public static class TmaubStats {
        public final Map<Integer, Double> tmaubPerItem = new HashMap<>();
        public final Map<Integer, Integer> support = new HashMap<>();
        public double UDB;
    }

    // Lần scan đầu tien: tính U(DB), support, and tmaub(item)
    public TmaubStats compute(UtilityDatabase db) {
        TmaubStats st = new TmaubStats();
        st.UDB = 0.0;
        for (Transaction t : db.transactions) {
            st.UDB += t.totalUtility();
            List<Double> utils = new ArrayList<>();
            Map<Integer, Double> utilByItem = new HashMap<>();
            for (TransactionItem ti : t.items) {
                utils.add(ti.util);
                utilByItem.put(ti.itemId, ti.util);
                st.support.merge(ti.itemId, 1, Integer::sum);
            }
            // Tính tmaub
            for (TransactionItem ti : t.items) {
                double ui = ti.util;
                List<Double> others = new ArrayList<>();
                for (TransactionItem j : t.items)
                    if (j.itemId != ti.itemId)
                        others.add(j.util);
                others.sort(Comparator.reverseOrder());
                double best = ui; // a_0 = ui / 1
                double sum = 0.0;
                int k = 0;
                double prevAvg = ui / 1.0;
                for (double v : others) {
                    double newAvg = (ui + sum + v) / (1.0 + (k + 1));
                    if (newAvg > prevAvg) {
                        sum += v;
                        k += 1;
                        prevAvg = newAvg;
                        best = prevAvg;
                    } else {
                        break;
                    }
                }
                st.tmaubPerItem.merge(ti.itemId, best, Double::sum);
            }
        }
        return st;
    }
}
