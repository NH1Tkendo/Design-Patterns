package com.ngobatai_lmhaup.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Reader for Profit Table files.
 * Format: itemName profitValue
 */
public class ProfitTableReader {

    private final String filePath;
    private boolean verbose = false;

    public ProfitTableReader(String filePath) {
        this.filePath = filePath;
    }

    public ProfitTableReader verbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    public Map<String, Double> read() throws IOException {
        Map<String, Double> profitMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    String itemName = parts[0];
                    double profit = Double.parseDouble(parts[1]);
                    profitMap.put(itemName, profit);
                }
            }
        }

        if (verbose) {
            System.out.println("Loaded " + profitMap.size() + " profit entries");
        }

        return profitMap;
    }
}
