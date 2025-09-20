package com.ngobatai_lab5.BaiTap;

public class LoggerFactory {
    public boolean isLogFileAvailable() {
        return true;
    }

    // Trả về Logger phù hợp
    public Logger getLogger() {
        if (isLogFileAvailable()) {
            return FileLogger.getFileLogger();
        } else {
            return new ConsoleLogger();
        }
    }
}
