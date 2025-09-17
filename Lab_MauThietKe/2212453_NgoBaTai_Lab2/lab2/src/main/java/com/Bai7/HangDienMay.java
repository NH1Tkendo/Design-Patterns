/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai7;

public class HangDienMay extends HangHoa {
    private final int thoiGianBH;
    private final double congSuat;

    public HangDienMay(String maHang, String tenHang, int soLuongTon, double donGia, int thoiGianBH, double congSuat) {
        super(maHang, tenHang, soLuongTon, donGia);
        this.thoiGianBH = (thoiGianBH >= 0) ? thoiGianBH : 0;
        this.congSuat = (congSuat > 0) ? congSuat : 1;
    }

    @Override
    public String danhGiaMucDoBan() {
        if (soLuongTon < 3) {
            return "Ban duoc";
        }
        return "Khong danh gia";
    }

    @Override
    public double tinhVAT() {
        return donGia * 0.1;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Thoi gian BH: " + thoiGianBH);
        System.out.println("Cong suat: " + congSuat);
        System.out.println("------------------------");
    }
}
