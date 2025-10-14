package com.ngobatai_lmhaup;

import java.util.Arrays;

import com.ngobatai_lmhaup.miner.LmhaupMiner;
import com.ngobatai_lmhaup.model.Item;
import com.ngobatai_lmhaup.model.Transaction;
import com.ngobatai_lmhaup.model.TransactionItem;
import com.ngobatai_lmhaup.model.UtilityDatabase;

public class test {
    public static void main(String[] args) {
        UtilityDatabase db = new UtilityDatabase();

        // Định nghĩa theo table-2 (name, profit)
        db.addItemDef(new Item(1, 1.0)); // a
        db.addItemDef(new Item(2, 2.0)); // b
        db.addItemDef(new Item(3, 2.0)); // c
        db.addItemDef(new Item(4, 1.0)); // d
        db.addItemDef(new Item(5, 3.0)); // e
        db.addItemDef(new Item(6, 4.0)); // f

        // Thêm các transaction
        // Transaction 1: {b:1, c:3, d:1, e:1}
        db.addTransaction(new Transaction(1, Arrays.asList(
                new TransactionItem(2, 1, db),
                new TransactionItem(4, 5, db),
                new TransactionItem(5, 13, db),
                new TransactionItem(6, 4, db))));

        // Transaction 2: {a:2, b:1, d:1, e:2, f:4}
        db.addTransaction(new Transaction(2, Arrays.asList(
                new TransactionItem(1, 8, db),
                new TransactionItem(2, 2, db),
                new TransactionItem(4, 10, db))));

        // Transaction 3: {a:2, c:2, e:3, f:4}
        db.addTransaction(new Transaction(3, Arrays.asList(
                new TransactionItem(1, 2, db),
                new TransactionItem(2, 1, db),
                new TransactionItem(3, 1, db))));

        // Transaction 4: {a:3, c:1, e:2, f:5}
        db.addTransaction(new Transaction(4, Arrays.asList(
                new TransactionItem(1, 1, db),
                new TransactionItem(3, 10, db),
                new TransactionItem(4, 3, db))));

        double delta = 0.083;
        LmhaupMiner miner = new LmhaupMiner(db, delta);
        miner.mine();

        if (miner.haupItemsets.isEmpty()) {
            System.out.println("Không tìm thấy pattern nào thỏa mãn ngưỡng minutil.");
        } else {
            System.out.println("\nDanh sách các High Average Utility Pattern:\n");
            System.out.println("Số lượng : " + miner.haupItemsets.size());
            System.out.println("+-----------+------------------+");
            System.out.println("| Pattern | Average Utility |");
            System.out.println("+-----------+------------------+");

            for (int k = 0; k < miner.haupItemsets.size(); k++) {
                int[] itemset = miner.haupItemsets.get(k);
                double au = miner.haupAU.get(k);

                // Chuyển đổi itemset thành chuỗi đẹp
                String patternStr = Arrays.toString(itemset);
                System.out.printf("| %-9s | %16.4f |%n", patternStr, au);
            }

            System.out.println("+-----------+------------------+");
        }

        System.out.println("\n================================================================");
    }
}