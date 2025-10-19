package com.ngobatai_lmhaup.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reader for Transaction Table (BangTransaction) files.
 * 
 * Expected format:
 * transactionId item1:quantity, item2:quantity, ...
 * 
 * Example:
 * 1 b:1, c:3, d:1, e:1
 * 2 a:2, b:1, d:1, e:2, f:4
 * 
 * Items can be identified by either:
 * - Item names (String) which will be mapped to IDs using the itemNameToId map
 * - Item IDs (Integer) which will be used directly
 */
public class TransactionTableReader {

    private final String filePath;
    private Map<String, Integer> itemNameToId;
    private boolean verbose = false;

    public TransactionTableReader(String filePath) {
        this.filePath = filePath;
        this.itemNameToId = new HashMap<>();
    }

    /**
     * Set the mapping from item names to IDs
     * This is used when transactions reference items by name
     */
    public TransactionTableReader itemMapping(Map<String, Integer> itemNameToId) {
        this.itemNameToId = itemNameToId;
        return this;
    }

    /**
     * Enable verbose logging
     */
    public TransactionTableReader verbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    /**
     * Read transaction table and return a list of transactions
     * Each transaction is a map from itemId to quantity
     */
    public List<TransactionData> read() throws IOException {
        List<TransactionData> transactions = new ArrayList<>();

        if (verbose) {
            System.out.println("Reading transaction table from: " + filePath);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Skip empty lines and comments
                if (line.isEmpty() || line.startsWith("#") || line.startsWith("//")) {
                    continue;
                }

                // Parse line: transactionId item1:qty, item2:qty, ...
                String[] parts = line.split("\\s+", 2);

                if (parts.length < 2) {
                    if (verbose) {
                        System.err.println("Warning: Invalid line " + lineNumber + " - expected 'transactionId items'");
                    }
                    continue;
                }

                try {
                    int transactionId = Integer.parseInt(parts[0]);
                    String itemsStr = parts[1];

                    // Parse items: item1:qty, item2:qty, ...
                    Map<Integer, Integer> items = parseItems(itemsStr, lineNumber);

                    if (!items.isEmpty()) {
                        transactions.add(new TransactionData(transactionId, items));

                        if (verbose && transactions.size() % 1000 == 0) {
                            System.out.println("Loaded " + transactions.size() + " transactions...");
                        }
                    }
                } catch (NumberFormatException e) {
                    if (verbose) {
                        System.err.println("Warning: Invalid format on line " + lineNumber + ": " + line);
                    }
                }
            }
        }

        if (verbose) {
            System.out.println("Loaded " + transactions.size() + " transactions");
        }

        return transactions;
    }

    /**
     * Parse items string: item1:qty, item2:qty, ...
     * Items can be names or IDs
     */
    private Map<Integer, Integer> parseItems(String itemsStr, int lineNumber) {
        Map<Integer, Integer> items = new HashMap<>();

        // Split by comma
        String[] itemPairs = itemsStr.split(",");

        for (String itemPair : itemPairs) {
            itemPair = itemPair.trim();

            if (itemPair.isEmpty()) {
                continue;
            }

            // Parse item:quantity
            String[] parts = itemPair.split(":");

            if (parts.length != 2) {
                if (verbose) {
                    System.err.println("Warning: Invalid item format on line " + lineNumber + ": " + itemPair);
                }
                continue;
            }

            String itemIdentifier = parts[0].trim();
            String quantityStr = parts[1].trim();

            try {
                int quantity = Integer.parseInt(quantityStr);
                int itemId = resolveItemId(itemIdentifier, lineNumber);

                if (itemId > 0) {
                    items.put(itemId, quantity);
                }
            } catch (NumberFormatException e) {
                if (verbose) {
                    System.err.println("Warning: Invalid quantity on line " + lineNumber + ": " + itemPair);
                }
            }
        }

        return items;
    }

    /**
     * Resolve item identifier to item ID
     * Can be either a direct ID or a name that needs to be mapped
     */
    private int resolveItemId(String identifier, int lineNumber) {
        // Try to parse as integer first
        try {
            return Integer.parseInt(identifier);
        } catch (NumberFormatException e) {
            // It's a name, look up in mapping
            Integer itemId = itemNameToId.get(identifier);

            if (itemId == null) {
                if (verbose) {
                    System.err.println("Warning: Unknown item name on line " + lineNumber + ": " + identifier);
                }
                return -1;
            }

            return itemId;
        }
    }

    /**
     * Data class to hold a transaction
     */
    public static class TransactionData {
        public final int transactionId;
        public final Map<Integer, Integer> items; // itemId -> quantity

        public TransactionData(int transactionId, Map<Integer, Integer> items) {
            this.transactionId = transactionId;
            this.items = items;
        }
    }
}
