package com.ngobatai_lab4.Bai3;

import java.util.*;

// Lớp chính để chạy chương trình
public class XiangqiGame {
    private static BanCo banCo;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        banCo = new BanCo();

        System.out.println("CHƯƠNG TRÌNH MÔ PHỎNG CỜ TƯỚNG");
        System.out.println("================================");

        while (true) {
            hienThiMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    banCo.hienThiBanCo();
                    break;
                case 2:
                    chonQuanCoVaHienThiCachDi();
                    break;
                case 3:
                    hienThiTatCaQuan();
                    break;
                case 4:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

            System.out.println("\nNhấn Enter để tiếp tục...");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    private static void hienThiMenu() {
        System.out.println("\n=== MENU CHÍNH ===");
        System.out.println("1. Hiển thị bàn cờ");
        System.out.println("2. Chọn quân cờ và xem cách đi");
        System.out.println("3. Hiển thị danh sách tất cả quân cờ");
        System.out.println("4. Thoát");
        System.out.print("Nhập lựa chọn: ");
    }

    private static void chonQuanCoVaHienThiCachDi() {
        System.out.println("\n=== CHỌN QUÂN CỜ ===");
        System.out.print("Nhập tọa độ x (0-8): ");
        int x = scanner.nextInt();
        System.out.print("Nhập tọa độ y (0-9): ");
        int y = scanner.nextInt();

        QuanCo quan = banCo.getQuanTaiViTri(x, y);
        if (quan != null) {
            quan.hienThiCachDi();
        } else {
            System.out.println("Không có quân cờ tại vị trí (" + x + ", " + y + ")");
        }
    }

    private static void hienThiTatCaQuan() {
        System.out.println("\n=== DANH SÁCH TẤT CẢ QUÂN CỜ ===");
        List<QuanCo> danhSachQuan = banCo.getDanhSachQuan();

        System.out.println("QUÂN ĐỎ:");
        for (QuanCo quan : danhSachQuan) {
            if (quan.isRed()) {
                System.out.println("- " + quan.getTen() + " tại (" + quan.getX() + ", " + quan.getY() + ")");
            }
        }

        System.out.println("\nQUÂN ĐEN:");
        for (QuanCo quan : danhSachQuan) {
            if (!quan.isRed()) {
                System.out.println("- " + quan.getTen() + " tại (" + quan.getX() + ", " + quan.getY() + ")");
            }
        }
    }
}
