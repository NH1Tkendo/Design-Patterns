/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai5;

abstract class HoaDon {
    protected String maHD;
    protected int ngay, thang, nam;
    protected String tenKH;
    protected String maPhong;
    protected double donGia;
    protected double thanhTien;

    public HoaDon(String maHD, int ngay, int thang, int nam, String tenKH, String maPhong, double donGia) {
        this.maHD = maHD;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.tenKH = tenKH;
        this.maPhong = maPhong;
        this.donGia = donGia;
    }

    public abstract void tinhThanhTien();

    public int getThang() {
        return thang;
    }

    public int getNam() {
        return nam;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void displayInfo() {
        System.out.println("Ma HD: " + maHD);
        System.out.println("Ngay HD: " + ngay + "/" + thang + "/" + nam);
        System.out.println("Ten KH: " + tenKH);
        System.out.println("Ma phong: " + maPhong);
        System.out.println("Don gia: " + donGia);
        System.out.println("Thanh tien: " + thanhTien);
    }
}
