/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai5;

class HoaDonNgay extends HoaDon {
    private int soNgay;

    public HoaDonNgay(String maHD, int ngay, int thang, int nam, String tenKH, String maPhong, double donGia,
            int soNgay) {
        super(maHD, ngay, thang, nam, tenKH, maPhong, donGia);
        this.soNgay = soNgay;
        tinhThanhTien();
    }

    @Override
    public void tinhThanhTien() {
        if (soNgay > 7) {
            this.thanhTien = 7 * donGia + (soNgay - 7) * donGia * 0.8;
        } else {
            this.thanhTien = soNgay * donGia;
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Hoa don ngay:");
        super.displayInfo();
        System.out.println("So ngay: " + soNgay);
        System.out.println("------------------------");
    }
}
