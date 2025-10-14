package com.ngobatai_lmhaup.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ngobatai_lmhaup.model.Item;
import com.ngobatai_lmhaup.model.Transaction;
import com.ngobatai_lmhaup.model.TransactionItem;
import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Đọc dataset từ file .dat và tạo UtilityDatabase
 */
public class DatasetReader {

    /**
     * Đọc dataset từ file .dat
     * Format: mỗi dòng là một transaction với các item IDs cách nhau bởi dấu cách
     * 
     * @param filePath    Đường dẫn đến file dataset
     * @param minProfit   Profit tối thiểu cho items
     * @param maxProfit   Profit tối đa cho items
     * @param minQuantity Quantity tối thiểu
     * @param maxQuantity Quantity tối đa
     * @param seed        Seed cho random generator (để reproducible)
     * @return UtilityDatabase
     * @throws IOException
     */
    public static UtilityDatabase readDataset(String filePath,
            double minProfit,
            double maxProfit,
            int minQuantity,
            int maxQuantity,
            long seed) throws IOException {
        UtilityDatabase db = new UtilityDatabase();
        Random random = new Random(seed);

        // Map để lưu profit đã generate cho mỗi item
        Map<Integer, Double> itemProfits = new HashMap<>();

        // Đọc file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int tid = 1;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                // Parse item IDs
                String[] parts = line.split("\\s+");
                List<TransactionItem> items = new ArrayList<>();

                for (String part : parts) {
                    try {
                        int itemId = Integer.parseInt(part);

                        // Generate profit cho item nếu chưa có
                        if (!itemProfits.containsKey(itemId)) {
                            double profit = minProfit + (maxProfit - minProfit) * random.nextDouble();
                            profit = Math.round(profit * 100.0) / 100.0; // Round to 2 decimal places
                            itemProfits.put(itemId, profit);
                            db.addItemDef(new Item(itemId, profit));
                        }

                        // Generate random quantity
                        int quantity = minQuantity + random.nextInt(maxQuantity - minQuantity + 1);

                        items.add(new TransactionItem(itemId, quantity, db));
                    } catch (NumberFormatException e) {
                        // Skip invalid items
                        continue;
                    }
                }

                if (!items.isEmpty()) {
                    db.addTransaction(new Transaction(tid++, items));
                }
            }
        }

        return db;
    }

    /**
     * Đọc dataset với cấu hình mặc định
     */
    public static UtilityDatabase readDataset(String filePath) throws IOException {
        return readDataset(filePath, 1.0, 10.0, 1, 5, 12345L);
    }
}
