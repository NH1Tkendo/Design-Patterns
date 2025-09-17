/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai10;

public class SinhVienTrungCap extends SinhVien {
    private final int thoiGianHoc;

    public SinhVienTrungCap(String maSV, String ten, String diaChi, int thoiGianHoc) {
        super(maSV, ten, diaChi);
        this.thoiGianHoc = thoiGianHoc;
    }

    @Override
    public void displayInfo() {
        System.out.println("Sinh vien trung cap:");
        System.out.println("Ma SV: " + maSV);
        System.out.println("Ten: " + ten);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("Thoi gian hoc: " + thoiGianHoc + " nam");
        System.out.println("------------------------");
    }
}
