package com.ngobatai_lmhaup.service;

import com.ngobatai_lmhaup.builder.DatabaseBuilder;
import com.ngobatai_lmhaup.model.UtilityDatabase;
import com.ngobatai_lmhaup.reader.TransactionTableReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseLoader {
    private final String profitFilePath;
    private final String transactionFilePath;
    private final boolean verbose;

    public DatabaseLoader(String profitFilePath, String transactionFilePath) {
        this(profitFilePath, transactionFilePath, true);
    }

    public DatabaseLoader(String profitFilePath, String transactionFilePath, boolean verbose) {
        this.profitFilePath = profitFilePath;
        this.transactionFilePath = transactionFilePath;
        this.verbose = verbose;
    }

    public UtilityDatabase loadDatabase() throws IOException {
        // Step 1: Read profit table
        if (verbose) {
            System.out.println("Step 1: Reading profit table from " + profitFilePath);
        }

        Map<String, Double> profitTable = readProfitTable();

        if (verbose) {
            System.out.println("Loaded " + profitTable.size() + " items with profit values");
            System.out.println();
        }

        // Step 2: Create item name to ID mapping
        Map<String, Integer> itemNameToId = createItemMapping(profitTable);

        // Step 3: Read transactions
        if (verbose) {
            System.out.println("Step 2: Reading transactions from " + transactionFilePath);
        }

        List<TransactionTableReader.TransactionData> transactions = readTransactions(itemNameToId);

        if (verbose) {
            System.out.println();
            System.out.println("Step 3: Building UtilityDatabase using Builder Pattern");
        }

        // Step 4: Build database using Builder Pattern
        UtilityDatabase database = buildDatabase(profitTable, itemNameToId, transactions);

        if (verbose) {
            System.out.println("Database built successfully:");
            System.out.println("  - " + profitTable.size() + " items");
            System.out.println("  - " + transactions.size() + " transactions");
            System.out.println();
        }

        return database;
    }

    private Map<String, Double> readProfitTable() throws IOException {
        Map<String, Double> profitTable = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(profitFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // Skip empty lines and comments
                }

                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    String itemName = parts[0];
                    double profit = Double.parseDouble(parts[1]);
                    profitTable.put(itemName, profit);
                }
            }
        }

        return profitTable;
    }

    private Map<String, Integer> createItemMapping(Map<String, Double> profitTable) {
        Map<String, Integer> itemNameToId = new HashMap<>();
        int itemId = 1;
        for (String itemName : profitTable.keySet()) {
            itemNameToId.put(itemName, itemId++);
        }
        return itemNameToId;
    }

    private List<TransactionTableReader.TransactionData> readTransactions(Map<String, Integer> itemNameToId)
            throws IOException {
        TransactionTableReader transactionReader = new TransactionTableReader(transactionFilePath);
        transactionReader.itemMapping(itemNameToId).verbose(verbose);
        return transactionReader.read();
    }

    private UtilityDatabase buildDatabase(Map<String, Double> profitTable,
            Map<String, Integer> itemNameToId,
            List<TransactionTableReader.TransactionData> transactions) {
        DatabaseBuilder builder = DatabaseBuilder.create();

        // Add items with profits
        for (Map.Entry<String, Integer> entry : itemNameToId.entrySet()) {
            String itemName = entry.getKey();
            int id = entry.getValue();
            double profit = profitTable.get(itemName);

            builder.items()
                    .item(id, itemName, profit)
                    .add();
        }

        // Add transactions
        for (TransactionTableReader.TransactionData txData : transactions) {
            builder.transactions().id(txData.transactionId);

            for (Map.Entry<Integer, Integer> itemEntry : txData.items.entrySet()) {
                builder.transactions().addItem(itemEntry.getKey(), itemEntry.getValue());
            }

            builder.transactions().add();
        }

        return builder.build();
    }
}
