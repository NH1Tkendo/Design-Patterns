package com.ngobatai_lab4.Bai3;

import java.util.ArrayList;
import java.util.List;

// Lớp bàn cờ
class BanCo {
    private QuanCo[][] banCo;
    private List<QuanCo> danhSachQuan;

    public BanCo() {
        banCo = new QuanCo[9][10];
        danhSachQuan = new ArrayList<>();
        khoiTaoBanCo();
    }

    private void khoiTaoBanCo() {
        // Khởi tạo quân đỏ (bên dưới)
        danhSachQuan.add(new Xe(0, 0, true));
        danhSachQuan.add(new Ma(1, 0, true));
        danhSachQuan.add(new TuongVoi(2, 0, true));
        danhSachQuan.add(new Si(3, 0, true));
        danhSachQuan.add(new Tuong(4, 0, true));
        danhSachQuan.add(new Si(5, 0, true));
        danhSachQuan.add(new TuongVoi(6, 0, true));
        danhSachQuan.add(new Ma(7, 0, true));
        danhSachQuan.add(new Xe(8, 0, true));

        danhSachQuan.add(new Phao(1, 2, true));
        danhSachQuan.add(new Phao(7, 2, true));

        for (int i = 0; i < 9; i += 2) {
            danhSachQuan.add(new Tot(i, 3, true));
        }

        // Khởi tạo quân đen (bên trên)
        danhSachQuan.add(new Xe(0, 9, false));
        danhSachQuan.add(new Ma(1, 9, false));
        danhSachQuan.add(new TuongVoi(2, 9, false));
        danhSachQuan.add(new Si(3, 9, false));
        danhSachQuan.add(new Tuong(4, 9, false));
        danhSachQuan.add(new Si(5, 9, false));
        danhSachQuan.add(new TuongVoi(6, 9, false));
        danhSachQuan.add(new Ma(7, 9, false));
        danhSachQuan.add(new Xe(8, 9, false));

        danhSachQuan.add(new Phao(1, 7, false));
        danhSachQuan.add(new Phao(7, 7, false));

        for (int i = 0; i < 9; i += 2) {
            danhSachQuan.add(new Tot(i, 6, false));
        }

        // Đặt quân lên bàn cờ
        for (QuanCo quan : danhSachQuan) {
            banCo[quan.getX()][quan.getY()] = quan;
        }
    }

    public void hienThiBanCo() {
        System.out.println("\n=== BÀN CỜ TƯỚNG ===");
        System.out.println("  0 1 2 3 4 5 6 7 8");

        for (int y = 9; y >= 0; y--) {
            System.out.print(y + " ");
            for (int x = 0; x < 9; x++) {
                if (banCo[x][y] != null) {
                    QuanCo quan = banCo[x][y];
                    if (quan instanceof Tuong)
                        System.out.print(quan.isRed() ? "T" : "t");
                    else if (quan instanceof Si)
                        System.out.print(quan.isRed() ? "S" : "s");
                    else if (quan instanceof TuongVoi)
                        System.out.print(quan.isRed() ? "V" : "v");
                    else if (quan instanceof Xe)
                        System.out.print(quan.isRed() ? "X" : "x");
                    else if (quan instanceof Ma)
                        System.out.print(quan.isRed() ? "M" : "m");
                    else if (quan instanceof Phao)
                        System.out.print(quan.isRed() ? "P" : "p");
                    else if (quan instanceof Tot)
                        System.out.print(quan.isRed() ? "C" : "c");
                } else {
                    if (y == 4 || y == 5)
                        System.out.print("~"); // Sông
                    else
                        System.out.print(".");
                }
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println("\nChú thích:");
        System.out.println("Quân Đỏ (chữ hoa): T=Tướng, S=Sĩ, V=Tượng, X=Xe, M=Mã, P=Pháo, C=Tốt");
        System.out.println("Quân Đen (chữ thường): t=tướng, s=sĩ, v=voi, x=xe, m=mã, p=pháo, c=tốt");
        System.out.println("~: Sông, .: Ô trống");
    }

    public List<QuanCo> getDanhSachQuan() {
        return danhSachQuan;
    }

    public QuanCo getQuanTaiViTri(int x, int y) {
        if (x >= 0 && x < 9 && y >= 0 && y < 10) {
            return banCo[x][y];
        }
        return null;
    }
}
