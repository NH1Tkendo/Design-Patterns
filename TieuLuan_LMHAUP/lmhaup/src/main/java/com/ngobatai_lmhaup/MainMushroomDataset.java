package com.ngobatai_lmhaup;

import java.io.IOException;

import com.ngobatai_lmhaup.miner.LmhaupMiner;
import com.ngobatai_lmhaup.model.UtilityDatabase;
import com.ngobatai_lmhaup.reader.DatasetReader;

/**
 * Main class demonstrating reading from mushroom.dat file
 * Uses Builder Pattern through DatasetReader
 */
public class MainMushroomDataset {

    public static void main(String[] args) {
        try {
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.println("║     LMHAUP MINING - MUSHROOM DATASET (Builder Pattern)     ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝\n");

            // Read dataset using DatasetReader with Builder Pattern
            System.out.println("📂 Reading mushroom.dat...\n");

            UtilityDatabase db = DatasetReader
                    .fromResource("mushroom.dat")
                    .maxTransactions(1000) // Limit to first 1000 transactions for demo
                    .defaultProfit(1.0) // All items have profit = 1.0
                    .verbose(true) // Show progress
                    .build();

            System.out.println("\n" + "─".repeat(60) + "\n");

            // Run LMHAUP mining
            double delta = 0.01; // 1% threshold
            System.out.println("⛏️  Running LMHAUP mining...");
            System.out.println("Delta (threshold): " + delta);
            System.out.println();

            LmhaupMiner miner = new LmhaupMiner(db, delta);
            miner.mine();

            // Print results
            printResults(miner, db);

        } catch (IOException e) {
            System.err.println("❌ Error reading dataset file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error during mining: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Print mining results in a formatted table
     */
    private static void printResults(LmhaupMiner miner, UtilityDatabase db) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("\n📊 MINING RESULTS:\n");

        if (miner.haupItemsets.isEmpty()) {
            System.out.println("❌ Không tìm thấy pattern nào thỏa mãn ngưỡng minutil.");
        } else {
            System.out.println("✅ Số lượng High Average Utility Patterns: " + miner.haupItemsets.size());
            System.out.println();

            // Print top 20 patterns
            int displayCount = Math.min(20, miner.haupItemsets.size());
            System.out.println("Top " + displayCount + " patterns:");
            System.out.println("┌──────┬─────────────────────────────────────┬──────────────────┐");
            System.out.println("│  No  │              Pattern                │ Average Utility  │");
            System.out.println("├──────┼─────────────────────────────────────┼──────────────────┤");

            for (int k = 0; k < displayCount; k++) {
                int[] itemset = miner.haupItemsets.get(k);
                double au = miner.haupAU.get(k);
                String patternStr = formatItemset(itemset);
                System.out.printf("│ %4d │ %-35s │ %16.4f │%n", (k + 1), patternStr, au);
            }

            System.out.println("└──────┴─────────────────────────────────────┴──────────────────┘");

            if (miner.haupItemsets.size() > displayCount) {
                System.out.println("\n... and " + (miner.haupItemsets.size() - displayCount) + " more patterns");
            }
        }

        System.out.println("\n" + "═".repeat(60));
    }

    /**
     * Format itemset for display (show item IDs)
     */
    private static String formatItemset(int[] itemset) {
        if (itemset.length == 0)
            return "{}";
        if (itemset.length > 8) {
            // Truncate long itemsets
            StringBuilder sb = new StringBuilder("{");
            for (int i = 0; i < 7; i++) {
                sb.append(itemset[i]).append(" ");
            }
            sb.append("... +").append(itemset.length - 7).append("}");
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder("{");
            for (int i = 0; i < itemset.length; i++) {
                sb.append(itemset[i]);
                if (i < itemset.length - 1)
                    sb.append(" ");
            }
            sb.append("}");
            return sb.toString();
        }
    }
}
