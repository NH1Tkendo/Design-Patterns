package com.ngobatai_lab4.Bai3;

public abstract class QuanCo {
    protected int x, y; // Vị trí trên bàn cờ (0-8 cho x, 0-9 cho y)
    protected boolean isRed; // true: đỏ, false: đen
    protected String ten;

    public QuanCo(int x, int y, boolean isRed, String ten) {
        this.x = x;
        this.y = y;
        this.isRed = isRed;
        this.ten = ten;
    }

    // Phương thức trừu tượng để hiển thị cách đi
    public abstract void hienThiCachDi();

    // Kiểm tra vị trí có hợp lệ không
    protected boolean isValidPosition(int x, int y) {
        return x >= 0 && x <= 8 && y >= 0 && y <= 9;
    }

    // Kiểm tra có trong cung không (cho Tướng và Sĩ)
    protected boolean isInPalace(int x, int y, boolean isRed) {
        if (isRed) {
            return x >= 3 && x <= 5 && y >= 0 && y <= 2;
        } else {
            return x >= 3 && x <= 5 && y >= 7 && y <= 9;
        }
    }

    // Kiểm tra có vượt sông không (cho Tượng)
    protected boolean crossRiver(int x, int y, boolean isRed) {
        if (isRed) {
            return y > 4; // Quân đỏ vượt qua giữa sông (y > 4)
        } else {
            return y < 5; // Quân đen vượt qua giữa sông (y < 5)
        }
    }

    public String getTen() {
        return ten;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isRed() {
        return isRed;
    }
}
