/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai4;

class KhachHangVN extends KhachHang {
    private final String doiTuong; // sinh hoat, kinh doanh, san xuat
    private final int dinhMuc;

    public KhachHangVN(String maKH, String hoTen, int ngay, int thang, int nam, int soLuong, double donGia,
            String doiTuong, int dinhMuc) {
        super(maKH, hoTen, ngay, thang, nam, soLuong, donGia);
        this.doiTuong = doiTuong;
        this.dinhMuc = dinhMuc;
        tinhThanhTien();
    }

    @Override
    public void tinhThanhTien() {
        if (soLuong <= dinhMuc) {
            this.thanhTien = soLuong * donGia;
        } else {
            this.thanhTien = dinhMuc * donGia + (soLuong - dinhMuc) * donGia * 2.5;
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Khach hang VN:");
        super.displayInfo();
        System.out.println("Doi tuong: " + doiTuong);
        System.out.println("Dinh muc: " + dinhMuc);
        System.out.println("------------------------");
    }
}
