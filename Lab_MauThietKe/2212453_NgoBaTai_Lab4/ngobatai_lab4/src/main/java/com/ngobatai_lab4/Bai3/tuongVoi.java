package com.ngobatai_lab4.Bai3;

// Lớp Tượng
class TuongVoi extends QuanCo {
    public TuongVoi(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "Tượng Đỏ" : "Voi Đen");
    }

    @Override
    public void hienThiCachDi() {
        System.out.println("=== CÁCH ĐI CỦA " + ten + " ===");
        System.out.println("- Đi chéo 2 ô (ngang 2 và dọc 2) cho mỗi nước đi");
        System.out.println("- Chỉ được ở một bên của bàn cờ, không được vượt sông");
        System.out.println("- Nước đi không hợp lệ khi có quân cờ chặn giữa đường đi");
        System.out.println("- Vị trí hiện tại: (" + x + ", " + y + ")");
        System.out.println("- Các nước đi hợp lệ:");

        int[][] directions = { { 2, 2 }, { 2, -2 }, { -2, 2 }, { -2, -2 } };
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (isValidPosition(newX, newY) && !crossRiver(newX, newY, isRed)) {
                // Kiểm tra điểm chặn (điểm giữa đường đi chéo)
                int blockX = x + dir[0] / 2;
                int blockY = y + dir[1] / 2;
                System.out.println("  + Có thể đi đến (" + newX + ", " + newY + ") nếu không bị chặn tại (" + blockX
                        + ", " + blockY + ")");
            }
        }
    }
}
