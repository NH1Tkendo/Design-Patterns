package com.ngobatai_lab5.BaiTap;

public class FileLogger implements Logger {
    private static FileLogger logger;

    private FileLogger() {
    }

    public static synchronized FileLogger getFileLogger() {
        if (logger == null) {
            logger = new FileLogger();
        }
        return logger;
    }

    // Ghi log
    @Override
    public synchronized void log(String message) {
        System.out.println("[FileLogger] " + message);
    }
}
