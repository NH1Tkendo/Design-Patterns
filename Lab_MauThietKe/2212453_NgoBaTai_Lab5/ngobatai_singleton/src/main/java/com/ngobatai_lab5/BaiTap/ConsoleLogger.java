package com.ngobatai_lab5.BaiTap;

public class ConsoleLogger implements Logger {
    @Override
    public synchronized void log(String message) {
        System.out.println("[ConsoleLogger] " + message);
    }
}
