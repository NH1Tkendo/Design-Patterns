package com.ngobatai_lmhaup.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityDatabase {
    // Lưu danh sách giao dịch để tính U(DB)
    // Sắp xếp tăng theo support
    // Dựng TA-List theo mục 3-2 và 3-3 trong bài báo
    public final List<Transaction> transactions = new ArrayList<>();

    // Lưu định nghĩa mặt hàng và profit (Theo bảng 2 trong bài báo)
    // Dùng để tính u(i,T)
    public final Map<Integer, Item> items = new HashMap<>();

    // ======================Thứ tự toàn cục=============================
    // lưu ánh xạ từ mã mục sang hạng trong thứ tự hỗ trợ tăng dần để đảm bảo chỉ
    // nối với các mục “đứng sau” tiền tố
    public int[] itemOrder;
    // Duyệt theo thứ tự đã sắp khi dựng TA-List 1-mục
    public int[] orderToItem;
    // =======================Thống kê toàn cục==============================
    // Lưu trữ U(DB) dùng để tính minutil
    public double totalUtilityDB;
    // Đếm số support để sắp như bảng 4 trong bài báo
    public Map<Integer, Integer> support;

    // Thêm vào item profit table - def1
    public void addItemDef(Item it) {
        items.put(it.id, it);
    }

    // Thêm giao dịch
    public void addTransaction(Transaction t) {
        transactions.add(t);
        totalUtilityDB += t.totalUtility();
        for (TransactionItem io : t.items) {
            support.merge(io.itemId, 1, Integer::sum);
        }
    }
}
