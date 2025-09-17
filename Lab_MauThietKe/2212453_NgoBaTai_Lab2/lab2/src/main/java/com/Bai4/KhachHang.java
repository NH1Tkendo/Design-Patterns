/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai4;

abstract class KhachHang {
    protected String maKH;
    protected String hoTen;
    protected int ngay, thang, nam;
    protected int soLuong;
    protected double donGia;
    protected double thanhTien;

    public KhachHang(String maKH, String hoTen, int ngay, int thang, int nam, int soLuong, double donGia) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.soLuong = soLuong;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void displayInfo() {
        System.out.println("Ma KH: " + maKH);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Ngay ra HD: " + ngay + "/" + thang + "/" + nam);
        System.out.println("So luong: " + soLuong);
        System.out.println("Don gia: " + donGia);
        System.out.println("Thanh tien: " + thanhTien);
    }
}
