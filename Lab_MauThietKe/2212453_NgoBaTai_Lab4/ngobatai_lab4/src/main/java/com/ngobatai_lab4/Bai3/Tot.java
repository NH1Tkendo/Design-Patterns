package com.ngobatai_lab4.Bai3;

// Lớp Tốt
class Tot extends QuanCo {
    public Tot(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "Tốt Đỏ" : "Tốt Đen");
    }

    @Override
    public void hienThiCachDi() {
        System.out.println("=== CÁCH ĐI CỦA " + ten + " ===");
        System.out.println("- Đi một ô mỗi nước");
        System.out.println("- Chưa vượt sông: chỉ có thể đi thẳng tiến");
        System.out.println("- Đã vượt sông: có thể đi ngang 1 nước hay đi thẳng tiến 1 bước");
        System.out.println("- Vị trí hiện tại: (" + x + ", " + y + ")");

        boolean daVuotSong = crossRiver(x, y, isRed);
        System.out.println("- Trạng thái: " + (daVuotSong ? "Đã vượt sông" : "Chưa vượt sông"));
        System.out.println("- Các nước đi hợp lệ:");

        if (daVuotSong) {
            // Đã vượt sông: có thể đi ngang và tiến
            int[][] directions = { { 0, isRed ? 1 : -1 }, { 1, 0 }, { -1, 0 } };
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (isValidPosition(newX, newY)) {
                    System.out.println("  + Có thể đi đến (" + newX + ", " + newY + ")");
                }
            }
        } else {
            // Chưa vượt sông: chỉ có thể tiến
            int newY = y + (isRed ? 1 : -1);
            if (isValidPosition(x, newY)) {
                System.out.println("  + Có thể đi đến (" + x + ", " + newY + ")");
            }
        }
    }
}