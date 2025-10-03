package com.ngobatai_lmhaup.struct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ngobatai_lmhaup.model.Transaction;
import com.ngobatai_lmhaup.model.TransactionItem;
import com.ngobatai_lmhaup.model.UtilityDatabase;

public class TAListFactory {
    private UtilityDatabase utilityDatabase;
    private Set<Integer> promisingItems;
    private int[] order;

    public TAListFactory(UtilityDatabase udb, Set<Integer> set, int[] order) {
        udb = utilityDatabase;
        set = promisingItems;
        this.order = order;
    }

    // Xây dựng TAList 1-item sử dụng sRLU và nRLUI
    // Sử dụng def 8-10
    public Map<Integer, TAList> TAList1Item() {
        Map<Integer, TAList> lists = new HashMap<>();
        for (int i : promisingItems) {
            lists.put(i, new TAList(new int[] { i }));
        }
        for (Transaction t : utilityDatabase.transactions) {
            List<TransactionItem> L = new ArrayList<>();
            for (TransactionItem ti : t.items) {
                if (promisingItems.contains(ti.itemId)) {
                    L.add(ti);
                }
            }
            // Sắp xếp tăng dần
            L.sort(Comparator.comparingInt(cur -> order[cur.itemId]));
            for (int i = L.size() - 1; i >= 0; --i) {
                TransactionItem cur = L.get(i);
                // Tính S{P,T}, sRLU, nRLUI
                double sRLU = 0.0;
                int nRLUI = 0;
                double uP = cur.util;
                double avgPrev = uP / 1.0;
                for (int j = i + 1; j < L.size(); ++j) {
                    TransactionItem candidate = L.get(j);
                    if (candidate.util > avgPrev) {
                        sRLU += candidate.util;
                        nRLUI += 1;
                        avgPrev = (uP + sRLU) / (1.0 + nRLUI);
                    }
                }
                TAList list = lists.get(cur.itemId);
                list.add(new TAListEntry(t.tid, uP, sRLU, nRLUI));
            }
        }
        for (TAList l : lists.values()) {
            l.entries.sort(Comparator.comparingInt(e -> e.tid));
        }
        return lists;
    }
}
