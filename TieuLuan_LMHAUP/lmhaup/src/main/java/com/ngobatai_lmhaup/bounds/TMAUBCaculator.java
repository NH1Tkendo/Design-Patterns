package com.ngobatai_lmhaup.bounds;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            st.UDB += t.totalUtility(); // Lấy tổng util toàn transaction U(DB)
            List<Double> utils = new ArrayList<>();
            Map<Integer, Double> utilByItem = new HashMap<>();
            // Quét 1 lần để tính support
            for (TransactionItem ti : t.items) {
                utils.add(ti.util);
                utilByItem.put(ti.itemId, ti.util);
                st.support.merge(ti.itemId, 1, Integer::sum);
            }
            // Tính tmaub cho từng item
            for (TransactionItem ti : t.items) {
                double ui = ti.util;
                List<Double> others = new ArrayList<>();
                // Tạo danh sách chứa utility của tất cả các item trừ item đang xét
                for (TransactionItem j : t.items)
                    if (j.itemId != ti.itemId)
                        others.add(j.util);
                // Sắp xếp giảm dần theo utility
                others.sort(Comparator.reverseOrder());
                double best = ui / 1.0;
                double sum = 0.0;
                int k = 0;
                double prevAvg = ui / 1.0;
                // Sử dụng công thức trong def-7
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
                // Xác định và lưu trữ giá trị TMAUB cho từng Transaction
                st.tmaubPerItem.merge(ti.itemId, best, Double::sum);
            }
        }
        // Sau khi hoàn thành tất cả các transaction
        for (Map.Entry<Integer, Double> entry : st.tmaubPerItem.entrySet()) {
            double value = entry.getValue();
            BigDecimal bd = new BigDecimal(Double.toString(value));
            double rounded = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
            st.tmaubPerItem.put(entry.getKey(), rounded);
        }

        printTmaubTable(st.tmaubPerItem, st.support);
        return st;
    }

    private void printTmaubTable(Map<Integer, Double> tmaubPerItem, Map<Integer, Integer> support) {
        // Tiêu đề bảng
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║        TMAUB PER ITEM STATISTICS           ║");
        System.out.println("╠═══════════╦════════════════╦═══════════════╣");
        System.out.printf("║ %-9s ║ %-14s ║ %-13s ║%n", "Item ID", "TMAUB Value", "Support");
        System.out.println("╠═══════════╬════════════════╬═══════════════╣");

        // Sắp xếp theo Item ID
        List<Integer> sortedKeys = new ArrayList<>(tmaubPerItem.keySet());
        sortedKeys.sort(Integer::compareTo);

        // In từng dòng dữ liệu
        for (Integer itemId : sortedKeys) {
            Double tmaubValue = tmaubPerItem.get(itemId);
            Integer supportValue = support.getOrDefault(itemId, 0);
            System.out.printf("║ %-9d ║ %-14.2f ║ %-13d ║%n",
                    itemId, tmaubValue, supportValue);
        }

        // Dòng cuối bảng
        System.out.println("╚═══════════╩════════════════╩═══════════════╝");
    }
}
