package com.ngobatai_lmhaup.service;

import com.ngobatai_lmhaup.miner.LmhaupMiner;
import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Service class responsible for formatting and printing mining results.
 * Provides methods to display high average-utility patterns in a readable
 * format.
 */
public class ResultPrinter {

    /**
     * Print mining results in a formatted table
     * 
     * @param miner    The LMHAUP miner containing results
     * @param database The utility database for item name lookup
     */
    public void printResults(LmhaupMiner miner, UtilityDatabase database) {
        if (miner.haupItemsets.isEmpty()) {
            printNoResults();
        } else {
            printResultTable(miner, database);
        }
        printSeparator();
    }

    /**
     * Print a detailed summary of mining results
     * 
     * @param miner    The LMHAUP miner containing results
     * @param database The utility database for item name lookup
     * @param delta    The minimum average utility threshold
     */
    public void printDetailedResults(LmhaupMiner miner, UtilityDatabase database, double delta) {
        System.out.println();
        System.out.println("Mining Summary:");
        System.out.println("-".repeat(60));
        System.out.printf("Minimum Average Utility (delta): %.2f%n", delta);
        System.out.printf("Total patterns found: %d%n", miner.haupItemsets.size());
        System.out.println();

        printResults(miner, database);
    }

    /**
     * Print message when no patterns are found
     */
    private void printNoResults() {
        System.out.println("No patterns found that satisfy the minimum utility threshold.");
    }

    /**
     * Print the result table with patterns and their average utilities
     * 
     * @param miner    The LMHAUP miner containing results
     * @param database The utility database for item name lookup
     */
    private void printResultTable(LmhaupMiner miner, UtilityDatabase database) {
        System.out.println(miner.haupItemsets.size() + " High Average-Utility Patterns (HAUP)");
        printTableHeader();

        for (int k = 0; k < miner.haupItemsets.size(); k++) {
            int[] itemset = miner.haupItemsets.get(k);
            double au = miner.haupAU.get(k);
            String patternStr = database.itemsetToString(itemset);
            printTableRow(patternStr, au);
        }

        printTableFooter();
    }

    /**
     * Print the table header
     */
    private void printTableHeader() {
        System.out.println("┌─────────────┬──────────────────┐");
        System.out.println("│   Pattern   │ Average Utility  │");
        System.out.println("├─────────────┼──────────────────┤");
    }

    /**
     * Print a single row in the table
     * 
     * @param pattern    The pattern string
     * @param avgUtility The average utility value
     */
    private void printTableRow(String pattern, double avgUtility) {
        System.out.printf("│ %-11s │ %16.4f │%n", pattern, avgUtility);
    }

    /**
     * Print the table footer
     */
    private void printTableFooter() {
        System.out.println("└─────────────┴──────────────────┘");
    }

    /**
     * Print a separator line
     */
    private void printSeparator() {
        System.out.println("\n" + "═".repeat(60));
    }

    /**
     * Print the application header
     */
    public void printHeader() {
        System.out.println("═".repeat(60));
        System.out.println("       LMHAUP Mining - File-Based Demo");
        System.out.println("═".repeat(60));
        System.out.println();
    }
}
