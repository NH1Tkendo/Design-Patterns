package com.ngobatai_lmhaup;

import java.io.IOException;

import com.ngobatai_lmhaup.miner.LmhaupMiner;
import com.ngobatai_lmhaup.model.UtilityDatabase;
import com.ngobatai_lmhaup.reader.DatasetReader;

/**
 * Demo class showing how to use DatasetReader with different datasets
 * Demonstrates Builder Pattern for dataset reading
 */
public class DatasetReaderDemo {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        DATASET READER DEMO (Builder Pattern)              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // Demo 1: Mushroom dataset
        demoMushroomDataset();

        System.out.println("\n" + "═".repeat(60) + "\n");

        // Demo 2: Retail dataset
        demoRetailDataset();

        System.out.println("\n" + "═".repeat(60) + "\n");

        // Demo 3: Connect dataset
        demoConnectDataset();
    }

    /**
     * Demo 1: Read and mine mushroom.dat
     */
    private static void demoMushroomDataset() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("DEMO 1: MUSHROOM DATASET");
        System.out.println("═══════════════════════════════════════════════════════════\n");

        try {
            // Using Builder Pattern through DatasetReader
            UtilityDatabase db = DatasetReader
                    .fromResource("mushroom.dat")
                    .maxTransactions(500)
                    .defaultProfit(1.0)
                    .verbose(true)
                    .build();

            System.out.println("\n✅ Database built successfully!");
            System.out.println("   Items: " + db.items.size());
            System.out.println("   Transactions: " + db.transactions.size());

            // Mine with small delta
            LmhaupMiner miner = new LmhaupMiner(db, 0.01);
            miner.mine();

            System.out.println("   HAUPs found: " + miner.haupItemsets.size());

        } catch (IOException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Demo 2: Read and mine retail.dat
     */
    private static void demoRetailDataset() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("DEMO 2: RETAIL DATASET");
        System.out.println("═══════════════════════════════════════════════════════════\n");

        try {
            // Using Builder Pattern with different configuration
            UtilityDatabase db = DatasetReader
                    .fromResource("retail.dat")
                    .maxTransactions(1000)
                    .defaultProfit(2.0) // Different profit
                    .verbose(true)
                    .build();

            System.out.println("\n✅ Database built successfully!");
            System.out.println("   Items: " + db.items.size());
            System.out.println("   Transactions: " + db.transactions.size());

            // Mine with different delta
            LmhaupMiner miner = new LmhaupMiner(db, 0.005);
            miner.mine();

            System.out.println("   HAUPs found: " + miner.haupItemsets.size());

        } catch (IOException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Demo 3: Read and mine connect.dat
     */
    private static void demoConnectDataset() {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("DEMO 3: CONNECT DATASET");
        System.out.println("═══════════════════════════════════════════════════════════\n");

        try {
            // Using Builder Pattern with another configuration
            UtilityDatabase db = DatasetReader
                    .fromResource("connect.dat")
                    .maxTransactions(300)
                    .defaultProfit(1.5)
                    .verbose(true)
                    .build();

            System.out.println("\n✅ Database built successfully!");
            System.out.println("   Items: " + db.items.size());
            System.out.println("   Transactions: " + db.transactions.size());

            // Mine
            LmhaupMiner miner = new LmhaupMiner(db, 0.02);
            miner.mine();

            System.out.println("   HAUPs found: " + miner.haupItemsets.size());

        } catch (IOException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}
