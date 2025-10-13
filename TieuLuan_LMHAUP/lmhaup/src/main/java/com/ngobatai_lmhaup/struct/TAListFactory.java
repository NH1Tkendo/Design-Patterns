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

    public TAListFactory(UtilityDatabase utilityDatabase, Set<Integer> promisingItems, int[] order) {
        this.utilityDatabase = utilityDatabase;
        this.promisingItems = promisingItems;
        this.order = order;
    }

    // Xây dựng TAList 1-item sử dụng sRLU và nRLUI
    // Sử dụng def 8-10
    public Map<Integer, TAList> TAList1Item() {
        Map<Integer, TAList> lists = new HashMap<>();

        for (int itemId : promisingItems) {
            lists.put(itemId, new TAList(new int[] { itemId }));
        }

        for (Transaction t : utilityDatabase.transactions) {
            // keep only promising items, sort by global order ascending
            List<TransactionItem> L = new ArrayList<>();
            for (TransactionItem io : t.items) {
                if (promisingItems.contains(io.itemId)) {
                    L.add(io);
                }
            }
            L.sort(Comparator.comparingInt(io -> order[io.itemId]));

            // traverse from the end to compute sRLU and nRLUI
            for (int i = L.size() - 1; i >= 0; --i) {
                TransactionItem cur = L.get(i);

                // BƯỚC 1: Thu thập các item còn lại (TR)
                List<TransactionItem> TR = new ArrayList<>();
                for (int j = i + 1; j < L.size(); j++) {
                    TR.add(L.get(j));
                }

                // BƯỚC 2: Sắp xếp TR theo utility GIẢM DẦN (quan trọng!)
                TR.sort((a, b) -> Double.compare(b.util, a.util));

                // BƯỚC 3: Tính S(P,T) theo Definition 8-10
                double sRLU = 0.0;
                int nRLUI = 0;
                double uP = cur.util;
                double b_prev = uP / 1.0; // b₀

                for (TransactionItem cand : TR) {
                    // Tính b_n nếu thêm cand vào S(P,T)
                    double b_n = (uP + sRLU + cand.util) / (1.0 + nRLUI + 1);

                    // Kiểm tra 2 điều kiện:
                    // 1. u(i'_n, T) >= b_{n-1}
                    // 2. b_n >= b_{n-1}
                    if (cand.util >= b_prev && b_n >= b_prev) {
                        sRLU += cand.util;
                        nRLUI += 1;
                        b_prev = b_n;
                    } else {
                        // Dừng ngay khi không thỏa điều kiện
                        break;
                    }
                }

                // BƯỚC 4: Xử lý trường hợp đặc biệt khi S(P,T) rỗng
                // Theo Definition 10
                if (nRLUI == 0 && !TR.isEmpty()) {
                    // sRLU = max utility trong TR
                    sRLU = TR.get(0).util; // Đã sắp xếp giảm dần nên phần tử đầu là max
                }

                TAList list = lists.get(cur.itemId);
                list.add(new TAListEntry(t.tid, uP, sRLU, nRLUI));
            }
        }

        // ensure entries per list sorted by tid
        for (TAList l : lists.values()) {
            l.entries.sort(Comparator.comparingInt(e -> e.tid));
        }

        return lists;
    }
}
