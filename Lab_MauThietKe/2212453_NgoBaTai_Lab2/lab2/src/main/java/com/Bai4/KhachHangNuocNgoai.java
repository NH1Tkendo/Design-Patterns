/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai4;

class KhachHangNuocNgoai extends KhachHang {
    private final String quocTich;

    public KhachHangNuocNgoai(String maKH, String hoTen, int ngay, int thang, int nam, int soLuong, double donGia,
            String quocTich) {
        super(maKH, hoTen, ngay, thang, nam, soLuong, donGia);
        this.quocTich = quocTich;
        tinhThanhTien();
    }

    @Override
    public void tinhThanhTien() {
        this.thanhTien = soLuong * donGia;
    }

    @Override
    public void displayInfo() {
        System.out.println("Khach hang nuoc ngoai:");
        super.displayInfo();
        System.out.println("Quoc tich: " + quocTich);
        System.out.println("------------------------");
    }
}
