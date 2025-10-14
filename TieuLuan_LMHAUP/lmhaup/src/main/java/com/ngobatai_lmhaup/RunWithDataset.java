package com.ngobatai_lmhaup;

import java.io.IOException;
import java.util.Arrays;

import com.ngobatai_lmhaup.io.DatasetReader;
import com.ngobatai_lmhaup.miner.LmhaupMiner;
import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Chạy thuật toán LMHAUP với các dataset có sẵn
 */
public class RunWithDataset {
    public static void main(String[] args) {
        // Cấu hình
        String datasetPath = "src/main/java/com/ngobatai_lmhaup/datasets/mushroom.dat";
        double minutil = 3.0; // minutil = 3 (giá trị tuyệt đối)

        // Cấu hình utility
        double minProfit = 1.0;
        double maxProfit = 10.0;
        int minQuantity = 1;
        int maxQuantity = 5;
        long seed = 12345; // Seed để reproducible results

        System.out.println("========================================");
        System.out.println("LMHAUP ALGORITHM - DATASET MINING");
        System.out.println("========================================");
        System.out.println("Dataset: " + datasetPath);
        System.out.println("MinUtil (absolute): " + minutil);
        System.out.println("Profit range: [" + minProfit + ", " + maxProfit + "]");
        System.out.println("Quantity range: [" + minQuantity + ", " + maxQuantity + "]");
        System.out.println("Seed: " + seed);
        System.out.println("========================================\n");

        try {
            // Đọc dataset
            System.out.println("Loading dataset...");
            long startLoad = System.currentTimeMillis();

            UtilityDatabase db = DatasetReader.readDataset(
                    datasetPath,
                    minProfit,
                    maxProfit,
                    minQuantity,
                    maxQuantity,
                    seed);

            long endLoad = System.currentTimeMillis();
            System.out.println("Dataset loaded successfully!");
            System.out.println("Number of transactions: " + db.transactions.size());
            System.out.println("Number of distinct items: " + db.items.size());
            System.out.println("Total utility: " + db.totalUtilityDB);
            System.out.println("Loading time: " + (endLoad - startLoad) + " ms\n");

            // Chạy LMHAUP với minutil tuyệt đối
            System.out.println("Running LMHAUP algorithm...");
            long startMining = System.currentTimeMillis();

            LmhaupMiner miner = new LmhaupMiner(db, minutil, true); // true = minutil tuyệt đối
            miner.mine();

            long endMining = System.currentTimeMillis();
            System.out.println("Mining completed!");
            System.out.println("Mining time: " + (endMining - startMining) + " ms\n");

            // In kết quả
            if (miner.haupItemsets.isEmpty()) {
                System.out.println("No HAUPs found with minutil = " + minutil);
            } else {
                System.out.println("========================================");
                System.out.println("HIGH AVERAGE UTILITY PATTERNS (HAUPs)");
                System.out.println("========================================");
                System.out.println("Total HAUPs found: " + miner.haupItemsets.size() + "\n");

                // In tối đa 50 patterns đầu tiên
                int maxDisplay = Math.min(50, miner.haupItemsets.size());
                System.out.println("Displaying first " + maxDisplay + " patterns:");
                System.out.println("+-------------------------+---------------------+");
                System.out.println("| Pattern                 | Average Utility     |");
                System.out.println("+-------------------------+---------------------+");

                for (int k = 0; k < maxDisplay; k++) {
                    int[] itemset = miner.haupItemsets.get(k);
                    double au = miner.haupAU.get(k);

                    String patternStr = Arrays.toString(itemset);
                    if (patternStr.length() > 23) {
                        patternStr = patternStr.substring(0, 20) + "...";
                    }
                    System.out.printf("| %-23s | %19.4f |%n", patternStr, au);
                }

                System.out.println("+-------------------------+---------------------+");

                if (miner.haupItemsets.size() > maxDisplay) {
                    System.out.println("... and " + (miner.haupItemsets.size() - maxDisplay) + " more patterns");
                }
            }

            System.out.println("\n========================================");
            System.out.println("SUMMARY");
            System.out.println("========================================");
            System.out.println("Total time: " + (endMining - startLoad) + " ms");
            System.out.println("Total HAUPs: " + miner.haupItemsets.size());
            System.out.println("========================================");

        } catch (IOException e) {
            System.err.println("Error reading dataset: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during mining: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
