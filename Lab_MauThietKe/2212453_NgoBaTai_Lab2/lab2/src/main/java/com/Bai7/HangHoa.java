/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai7;

abstract class HangHoa {
    protected String maHang;
    protected String tenHang;
    protected int soLuongTon;
    protected double donGia;

    public HangHoa(String maHang, String tenHang, int soLuongTon, double donGia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongTon = (soLuongTon >= 0) ? soLuongTon : 0;
        this.donGia = (donGia > 0) ? donGia : 1;
    }

    public abstract String danhGiaMucDoBan();

    public abstract double tinhVAT();

    public String getMaHang() {
        return maHang;
    }

    public void displayInfo() {
        System.out.println("Ma hang: " + maHang);
        System.out.println("Ten hang: " + tenHang);
        System.out.println("So luong ton: " + soLuongTon);
        System.out.println("Don gia: " + donGia);
        System.out.println("VAT: " + tinhVAT());
        System.out.println("Danh gia: " + danhGiaMucDoBan());
    }
}
