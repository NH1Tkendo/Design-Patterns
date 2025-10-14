package com.ngobatai_lmhaup.miner;

import com.ngobatai_lmhaup.util.MemoryMonitor;

/**
 * Class để track và report mining statistics
 */
public class MiningStatistics {

    private int totalPatternsChecked = 0;
    private int totalHAUPsFound = 0;
    private int currentDepth = 0;
    private long startTime;

    public MiningStatistics() {
        this.startTime = System.currentTimeMillis();
    }

    public void incrementPatternsChecked() {
        totalPatternsChecked++;
    }

    public void incrementHAUPsFound() {
        totalHAUPsFound++;
    }

    public void setCurrentDepth(int depth) {
        this.currentDepth = Math.max(this.currentDepth, depth);
    }

    public int getTotalPatternsChecked() {
        return totalPatternsChecked;
    }

    public int getTotalHAUPsFound() {
        return totalHAUPsFound;
    }

    public int getCurrentDepth() {
        return currentDepth;
    }

    /**
     * Print progress mỗi 1000 patterns
     */
    public void printProgress(int depth, int haupCount, int cacheSize) {
        if (totalPatternsChecked % 1000 == 0) {
            long availableMB = MemoryMonitor.getAvailableMemoryMB();
            System.out.printf("Progress: Depth=%d, Checked=%d, HAUPs=%d, Cache=%d, AvailMem=%dMB%n",
                    depth, totalPatternsChecked, haupCount, cacheSize, availableMB);

            if (availableMB < 100) {
                System.out.println("⚠⚠⚠ LOW MEMORY WARNING! Available: " + availableMB + "MB");
                MemoryMonitor.gcIfNeeded();
            }
        }
    }

    /**
     * Print final mining statistics
     */
    public void printFinalStatistics(int cacheSize) {
        long endTime = System.currentTimeMillis();
        System.out.println("\n========== MINING STATISTICS ==========");
        System.out.println("Total patterns checked: " + totalPatternsChecked);
        System.out.println("Total HAUPs found: " + totalHAUPsFound);
        System.out.println("Max depth reached: " + currentDepth);
        System.out.println("Final cache size: " + cacheSize);
        System.out.println("Mining time: " + (endTime - startTime) + " ms");
        System.out.println("========================================");
    }
}
