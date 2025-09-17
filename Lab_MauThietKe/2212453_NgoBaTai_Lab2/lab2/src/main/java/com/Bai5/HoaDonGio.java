/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai5;

class HoaDonGio extends HoaDon {
    private int soGio;

    public HoaDonGio(String maHD, int ngay, int thang, int nam, String tenKH, String maPhong, double donGia,
            int soGio) {
        super(maHD, ngay, thang, nam, tenKH, maPhong, donGia);
        this.soGio = soGio;
        tinhThanhTien();
    }

    @Override
    public void tinhThanhTien() {
        if (soGio > 30) {
            this.thanhTien = 0; // Khong tinh
            System.out.println("So gio >30, khong su dung loai nay!");
        } else if (soGio > 24) {
            this.thanhTien = 24 * donGia;
        } else {
            this.thanhTien = soGio * donGia;
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Hoa don gio:");
        super.displayInfo();
        System.out.println("So gio: " + soGio);
        System.out.println("------------------------");
    }
}
