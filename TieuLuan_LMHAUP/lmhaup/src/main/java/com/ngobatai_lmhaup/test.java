package com.ngobatai_lmhaup;

import com.ngobatai_lmhaup.builder.DatabaseBuilder;
import com.ngobatai_lmhaup.miner.LmhaupMiner;
import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Test class - Now using Builder Pattern for cleaner code
 */
public class test {
    public static void main(String[] args) {
        // Build database using Builder Pattern
        UtilityDatabase db = buildTestDatabase();

        // Run mining with test delta
        double delta = 0.083;
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
                .item(1, "a", 1.0).add()
                .item(2, "b", 2.0).add()
                .item(3, "c", 2.0).add()
                .item(4, "d", 1.0).add()
                .item(5, "e", 3.0).add()
                .item(6, "f", 4.0).add();

        // Define transactions with Builder Pattern
        // Transaction 1: {b:1, d:5, e:13, f:4}
        builder.transactions()
                .id(1)
                .addItem(2, 1) // b:1
                .addItem(4, 5) // d:5
                .addItem(5, 13) // e:13
                .addItem(6, 4) // f:4
                .add();

        // Transaction 2: {a:8, b:2, d:10}
        builder.transactions()
                .id(2)
                .addItem(1, 8) // a:8
                .addItem(2, 2) // b:2
                .addItem(4, 10) // d:10
                .add();

        // Transaction 3: {a:2, b:1, c:1}
        builder.transactions()
                .id(3)
                .addItem(1, 2) // a:2
                .addItem(2, 1) // b:1
                .addItem(3, 1) // c:1
                .add();

        // Transaction 4: {a:1, c:10, d:3}
        builder.transactions()
                .id(4)
                .addItem(1, 1) // a:1
                .addItem(3, 10) // c:10
                .addItem(4, 3) // d:3
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