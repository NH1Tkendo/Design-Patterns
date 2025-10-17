package com.ngobatai_lmhaup.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.ngobatai_lmhaup.builder.DatabaseBuilder;
import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Dataset Reader using Builder Pattern to construct UtilityDatabase from .dat
 * files
 */
public class DatasetReader {

    private final String filePath;
    private int maxTransactions = Integer.MAX_VALUE;
    private double defaultProfit = 1.0;
    private boolean verbose = false;

    public DatasetReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Set maximum number of transactions to read
     */
    public DatasetReader maxTransactions(int max) {
        this.maxTransactions = max;
        return this;
    }

    /**
     * Set default profit for all items
     */
    public DatasetReader defaultProfit(double profit) {
        this.defaultProfit = profit;
        return this;
    }

    /**
     * Enable verbose logging
     */
    public DatasetReader verbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    /**
     * Read dataset and build UtilityDatabase using Builder Pattern
     */
    public UtilityDatabase build() throws IOException {
        DatabaseBuilder builder = DatabaseBuilder.create();

        Set<Integer> uniqueItems = new HashSet<>();
        int transactionCount = 0;

        if (verbose) {
            System.out.println("Reading dataset from: " + filePath);
        }

        // First pass: collect all unique items
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null && transactionCount < maxTransactions) {
                String[] items = line.trim().split("\\s+");
                for (String itemStr : items) {
                    if (!itemStr.isEmpty()) {
                        uniqueItems.add(Integer.parseInt(itemStr));
                    }
                }
                transactionCount++;
            }
        }

        if (verbose) {
            System.out.println("Found " + uniqueItems.size() + " unique items");
            System.out.println("Found " + transactionCount + " transactions");
        }

        // Build items with Builder Pattern
        for (Integer itemId : uniqueItems) {
            builder.items()
                    .item(itemId, "item_" + itemId, defaultProfit)
                    .add();
        }

        // Second pass: build transactions with Builder Pattern
        transactionCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null && transactionCount < maxTransactions) {
                String[] items = line.trim().split("\\s+");
                if (items.length > 0) {
                    transactionCount++;

                    // Build transaction using Builder Pattern
                    var txBuilder = builder.transactions().id(transactionCount);

                    for (String itemStr : items) {
                        if (!itemStr.isEmpty()) {
                            int itemId = Integer.parseInt(itemStr);
                            // Default quantity = 1 for .dat files
                            txBuilder.addItem(itemId, 1);
                        }
                    }

                    txBuilder.add();

                    if (verbose && transactionCount % 1000 == 0) {
                        System.out.println("Processed " + transactionCount + " transactions...");
                    }
                }
            }
        }

        if (verbose) {
            System.out.println("Database built successfully!");
        }

        return builder.build();
    }

    /**
     * Static factory method for fluent API
     */
    public static DatasetReader from(String filePath) {
        return new DatasetReader(filePath);
    }

    /**
     * Read dataset from resources folder
     */
    public static DatasetReader fromResource(String resourceName) {
        String basePath = "lmhaup/src/main/java/com/ngobatai_lmhaup/datasets/";
        return new DatasetReader(basePath + resourceName);
    }
}
