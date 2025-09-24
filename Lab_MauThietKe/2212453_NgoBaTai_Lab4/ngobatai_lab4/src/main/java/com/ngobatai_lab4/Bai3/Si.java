package com.ngobatai_lab4.Bai3;

// Lớp Sĩ
class Si extends QuanCo {
    public Si(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "Sĩ Đỏ" : "Sĩ Đen");
    }

    @Override
    public void hienThiCachDi() {
        System.out.println("=== CÁCH ĐI CỦA " + ten + " ===");
        System.out.println("- Đi xéo 1 ô mỗi nước");
        System.out.println("- Luôn phải ở trong cung như Tướng");
        System.out.println("- Vị trí hiện tại: (" + x + ", " + y + ")");
        System.out.println("- Các nước đi hợp lệ:");

        int[][] directions = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (isValidPosition(newX, newY) && isInPalace(newX, newY, isRed)) {
                System.out.println("  + Có thể đi đến (" + newX + ", " + newY + ")");
            }
        }
    }
}
