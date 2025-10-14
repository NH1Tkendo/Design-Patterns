package com.ngobatai_lmhaup.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ngobatai_lmhaup.model.Item;
import com.ngobatai_lmhaup.model.Transaction;
import com.ngobatai_lmhaup.model.TransactionItem;
import com.ngobatai_lmhaup.model.UtilityDatabase;

public class DatasetReader {
    public static UtilityDatabase readDataset(String filePath, double minProfit, double maxProfit, int minQuantity, int maxQuantity, long seed) throws IOException {
        UtilityDatabase db = new UtilityDatabase();
        Random random = new Random(seed);
        List<List<Integer>> allTransactions = new ArrayList<>();
        Map<Integer, Integer> itemCount = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                List<Integer> transaction = new ArrayList<>();
                for (String part : parts) {
                    try {
                        int itemId = Integer.parseInt(part);
                        transaction.add(itemId);
                        itemCount.put(itemId, itemCount.getOrDefault(itemId, 0) + 1);
                    } catch (NumberFormatException e) { }
                }
                if (!transaction.isEmpty()) allTransactions.add(transaction);
            }
        }
        for (Integer itemId : itemCount.keySet()) {
            double profit = minProfit + (maxProfit - minProfit) * random.nextDouble();
            db.addItemDef(new Item(itemId, profit));
        }
        int tid = 1;
        for (List<Integer> itemIds : allTransactions) {
            List<TransactionItem> items = new ArrayList<>();
            for (Integer itemId : itemIds) {
                int quantity = minQuantity + random.nextInt(maxQuantity - minQuantity + 1);
                items.add(new TransactionItem(itemId, quantity, db));
            }
            db.addTransaction(new Transaction(tid++, items));
        }
        return db;
    }
    public static void printDatasetStats(UtilityDatabase db) {
        System.out.println("Dataset Statistics:");
        System.out.println("  Number of transactions: " + db.transactions.size());
        System.out.println("  Number of distinct items: " + db.items.size());
        System.out.println("  Total utility: " + String.format("%.2f", db.totalUtilityDB));
        double avgItems = db.transactions.stream().mapToInt(t -> t.items.size()).average().orElse(0.0);
        System.out.println("  Average items per transaction: " + String.format("%.2f", avgItems));
        int maxItems = db.transactions.stream().mapToInt(t -> t.items.size()).max().orElse(0);
        int minItems = db.transactions.stream().mapToInt(t -> t.items.size()).min().orElse(0);
        System.out.println("  Items per transaction: [" + minItems + ", " + maxItems + "]");
        System.out.println();
    }
}
