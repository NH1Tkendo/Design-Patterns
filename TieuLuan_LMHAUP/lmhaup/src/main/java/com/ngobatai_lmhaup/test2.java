package com.ngobatai_lmhaup;

import com.ngobatai_lmhaup.builder.DatabaseBuilder;
import com.ngobatai_lmhaup.miner.LmhaupMiner;
import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Test class - Now using Builder Pattern for cleaner code
 */
public class test2 {
    public static void main(String[] args) {
        // Build database using Builder Pattern
        UtilityDatabase db = buildTestDatabase();

        // Run mining with test delta
        double delta = 0.16;
        LmhaupMiner miner = new LmhaupMiner(db, delta);
        miner.mine();

        // Print results
        printResults(miner, db);
    }

    /**
     * Build test database using Builder Pattern
     */
    private static UtilityDatabase buildTestDatabase() {
        DatabaseBuilder builder = DatabaseBuilder.create();

        // Define items with Builder Pattern
        builder.items()
                .item(1, "a", 5.0).add()
                .item(2, "b", 1.0).add()
                .item(3, "c", 2.0).add()
                .item(4, "d", 3.0).add()
                .item(5, "e", 4.0).add()
                .item(6, "f", 1.0).add();

        // Define transactions with Builder Pattern
        // Transaction 1: {b:1, d:5, e:13, f:4}
        builder.transactions()
                .id(1)
                .addItem(1, 1) // b:1
                .addItem(2, 6) // d:5
                .addItem(3, 3) // e:13
                .addItem(4, 3) // f:4
                .addItem(6, 6) // f:4
                .add();

        // Transaction 2: {a:8, b:2, d:10}
        builder.transactions()
                .id(2)
                .addItem(2, 2) // a:8
                .addItem(3, 3) // b:2
                .addItem(5, 2) // d:10
                .add();

        // Transaction 3: {a:2, b:1, c:1}
        builder.transactions()
                .id(3)
                .addItem(1, 2) // a:2
                .addItem(3, 1) // b:1
                .addItem(4, 2) // c:1
                .addItem(5, 1) // c:1
                .add();

        // Transaction 4: {a:1, c:10, d:3}
        builder.transactions()
                .id(4)
                .addItem(1, 1) // a:1
                .addItem(2, 9) // c:10
                .addItem(3, 3) // d:3
                .addItem(4, 2) // d:3
                .addItem(6, 2) // d:3
                .add();
        // Transaction 4: {a:1, c:10, d:3}
        builder.transactions()
                .id(5)
                .addItem(1, 3) // a:1
                .addItem(2, 9) // c:10
                .addItem(3, 3) // d:3
                .addItem(4, 1) // d:3
                .addItem(5, 1) // d:3
                .add();

        // Transaction 4: {a:1, c:10, d:3}
        builder.transactions()
                .id(6)
                .addItem(3, 4) // a:1
                .addItem(4, 1) // c:10
                .addItem(5, 1) // d:3
                .add();

        return builder.build();
    }

    private static void printResults(LmhaupMiner miner, UtilityDatabase db) {
        if (miner.haupItemsets.isEmpty()) {
            System.out.println("Không tìm thấy pattern nào thỏa mãn ngưỡng minutil.");
        } else {
            System.out.println(miner.haupItemsets.size() + " HAUP");
            System.out.println("┌─────────────┬──────────────────┐");
            System.out.println("│   Pattern   │ Average Utility  │");
            System.out.println("├─────────────┼──────────────────┤");

            for (int k = 0; k < miner.haupItemsets.size(); k++) {
                int[] itemset = miner.haupItemsets.get(k);
                double au = miner.haupAU.get(k);
                String patternStr = db.itemsetToString(itemset);
                System.out.printf("│ %-11s │ %16.4f │%n", patternStr, au);
            }

            System.out.println("└─────────────┴──────────────────┘");
        }

        System.out.println("\n" + "═".repeat(60));
    }
}