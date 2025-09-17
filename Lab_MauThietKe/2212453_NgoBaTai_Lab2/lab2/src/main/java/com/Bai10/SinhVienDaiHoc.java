/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai10;

public class SinhVienDaiHoc extends SinhVien {
    private final String luanVan;

    public SinhVienDaiHoc(String maSV, String ten, String diaChi, String luanVan) {
        super(maSV, ten, diaChi);
        this.luanVan = luanVan;
    }

    @Override
    public void displayInfo() {
        System.out.println("Sinh vien dai hoc:");
        System.out.println("Ma SV: " + maSV);
        System.out.println("Ten: " + ten);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("Luan van: " + luanVan);
        System.out.println("------------------------");
    }
}
