package com.ngobatai_lab4.Bai3;

// Lớp Xe
class Xe extends QuanCo {
    public Xe(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "Xe Đỏ" : "Xe Đen");
    }

    @Override
    public void hienThiCachDi() {
        System.out.println("=== CÁCH ĐI CỦA " + ten + " ===");
        System.out.println("- Đi ngang hay dọc trên bàn cờ");
        System.out.println("- Không được có quân khác cản đường từ điểm đi đến điểm đến");
        System.out.println("- Vị trí hiện tại: (" + x + ", " + y + ")");
        System.out.println("- Các hướng đi có thể:");
        System.out.println("  + Đi ngang: từ cột 0 đến cột 8");
        System.out.println("  + Đi dọc: từ hàng 0 đến hàng 9");
        System.out.println("  (Cần kiểm tra không có quân nào cản đường)");
    }
}
