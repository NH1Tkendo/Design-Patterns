package com.ngobatai_lab4.Bai3;

// Lớp Mã
class Ma extends QuanCo {
    public Ma(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "Mã Đỏ" : "Mã Đen");
    }

    @Override
    public void hienThiCachDi() {
        System.out.println("=== CÁCH ĐI CỦA " + ten + " ===");
        System.out.println("- Đi ngang 2 ô và dọc 1 ô (hoặc dọc 2 ô và ngang 1 ô)");
        System.out.println("- Nếu có quân cản đường ngang 2 (hay đường dọc 2), mã bị cản");
        System.out.println("- Vị trí hiện tại: (" + x + ", " + y + ")");
        System.out.println("- Các nước đi hợp lệ:");

        int[][] moves = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 } };
        int[][] blocks = { { 1, 0 }, { 1, 0 }, { -1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 0, 1 }, { 0, -1 } };

        for (int i = 0; i < moves.length; i++) {
            int newX = x + moves[i][0];
            int newY = y + moves[i][1];
            if (isValidPosition(newX, newY)) {
                int blockX = x + blocks[i][0];
                int blockY = y + blocks[i][1];
                System.out.println("  + Có thể đi đến (" + newX + ", " + newY + ") nếu không bị cản tại (" + blockX
                        + ", " + blockY + ")");
            }
        }
    }
}
