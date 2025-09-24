package com.ngobatai_lab4.Bai3;

// Lớp Pháo
class Phao extends QuanCo {
    public Phao(int x, int y, boolean isRed) {
        super(x, y, isRed, isRed ? "Pháo Đỏ" : "Pháo Đen");
    }

    @Override
    public void hienThiCachDi() {
        System.out.println("=== CÁCH ĐI CỦA " + ten + " ===");
        System.out.println("- Đi ngang và dọc giống như Xe");
        System.out.println("- Khi không ăn quân: đường đi phải không có quân cản");
        System.out.println("- Khi ăn quân: phải nhảy qua đúng 1 quân nào đó");
        System.out.println("- Vị trí hiện tại: (" + x + ", " + y + ")");
        System.out.println("- Các hướng đi có thể:");
        System.out.println("  + Đi ngang: từ cột 0 đến cột 8");
        System.out.println("  + Đi dọc: từ hàng 0 đến hàng 9");
        System.out.println("  (Luật di chuyển khác với Xe khi ăn quân)");
    }
}
