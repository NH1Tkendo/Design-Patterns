package com.ngobatai_lab4.Bai3;

public class Tuong extends QuanCo {
    public Tuong(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "Tướng Đỏ" : "Tướng Đen");
    }

    @Override
    public void hienThiCachDi() {
        System.out.println("=== CÁCH ĐI CỦA " + ten + " ===");
        System.out.println("- Đi từng ô một, đi ngang hoặc dọc");
        System.out.println("- Luôn phải ở trong phạm vi cung (hình vuông 3x3 có đường chéo)");
        System.out.println("- Vị trí hiện tại: (" + x + ", " + y + ")");
        System.out.println("- Các nước đi hợp lệ:");

        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (isValidPosition(newX, newY) && isInPalace(newX, newY, isRed)) {
                System.out.println("  + Có thể đi đến (" + newX + ", " + newY + ")");
            }
        }
    }
}
