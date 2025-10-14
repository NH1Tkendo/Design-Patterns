package com.ngobatai_lmhaup.util;

public class MemoryMonitor {

    private static final long MEGABYTE = 1024L * 1024L;

    // In thông tin về bộ nhớ
    public static void printMemoryUsage(String label) {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();

        System.out.println("========== MEMORY STATUS: " + label + " ==========");
        System.out.printf("Used Memory:  %d MB%n", usedMemory / MEGABYTE);
        System.out.printf("Free Memory:  %d MB%n", freeMemory / MEGABYTE);
        System.out.printf("Total Memory: %d MB%n", totalMemory / MEGABYTE);
        System.out.printf("Max Memory:   %d MB%n", maxMemory / MEGABYTE);
        System.out.printf("Memory Usage: %.2f%%%n", (usedMemory * 100.0) / maxMemory);
        System.out.println("================================================");
    }

    // Kiểm tra memory có vượt quá 80% bộ nhớ không
    public static boolean isMemoryHigh() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();

        double usagePercent = (usedMemory * 100.0) / maxMemory;
        return usagePercent > 80.0;
    }

    // Gián đoạn luồng để dọn bộ nhớ
    public static void gcIfNeeded() {
        if (isMemoryHigh()) {
            System.out.println("⚠ Memory usage high, running GC...");
            System.gc();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Tính toán memory còn lại
    public static long getAvailableMemoryMB() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        return (maxMemory - usedMemory) / MEGABYTE;
    }
}
