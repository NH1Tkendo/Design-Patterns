package com.ngobatai_lab5.BaiTap;

public class Main {
    public static void main(String[] args) {
        LoggerFactory factory = new LoggerFactory();

        Logger logger1 = factory.getLogger();
        Logger logger2 = factory.getLogger();

        logger1.log("Xin chào");
        logger2.log("Đây là log thử nghiệm");

        // Kiểm tra Singleton
        System.out.println(logger1 == logger2);
    }
}
