/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai10;

public class SinhVienCaoDang extends SinhVien {
    private final String chuyenNganh;

    public SinhVienCaoDang(String maSV, String ten, String diaChi, String chuyenNganh) {
        super(maSV, ten, diaChi);
        this.chuyenNganh = chuyenNganh;
    }

    @Override
    public void displayInfo() {
        System.out.println("Sinh vien cao dang:");
        System.out.println("Ma SV: " + maSV);
        System.out.println("Ten: " + ten);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("Chuyen nganh: " + chuyenNganh);
        System.out.println("------------------------");
    }
}
