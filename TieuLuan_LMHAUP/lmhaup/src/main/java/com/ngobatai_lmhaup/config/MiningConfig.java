package com.ngobatai_lmhaup.config;

/**
 * Configuration class để tránh OutOfMemory và tối ưu performance
 */
public class MiningConfig {

    // Cache settings
    public static final int MAX_CACHE_SIZE = 10000; // Giảm nếu vẫn bị OutOfMemory
    public static final double CACHE_CLEAR_RATIO = 0.7; // Giữ lại 70% khi clear

    // Mining limits
    public static final int MAX_DEPTH = 10; // Giới hạn độ sâu
    public static final int MAX_PATTERNS_PER_LEVEL = 5000; // Giới hạn patterns mỗi level

    // Progress reporting
    public static final int PROGRESS_REPORT_INTERVAL = 1000; // In progress mỗi 1000 patterns

    // Memory management
    public static final boolean ENABLE_GC_HINTS = true; // Có gọi System.gc() không
    public static final long MAX_MEMORY_MB = 512; // Max memory cho JVM (MB)

    /**
     * Kiểm tra memory usage hiện tại
     */
    public static boolean isMemoryLow() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        long maxMemory = runtime.maxMemory();

        double usage = (double) usedMemory / maxMemory;
        return usage > 0.8; // Cảnh báo nếu dùng > 80% memory
    }

    /**
     * In memory status
     */
    public static void printMemoryStatus() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        long maxMemory = runtime.maxMemory() / 1024 / 1024;

        System.out.printf("Memory: %d MB / %d MB (%.1f%%)%n",
                usedMemory, maxMemory,
                (double) usedMemory / maxMemory * 100);
    }
}
