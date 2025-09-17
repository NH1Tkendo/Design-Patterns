/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai10;

public class SinhVienLienKet extends SinhVien {
    private final String truongLienKet;

    public SinhVienLienKet(String maSV, String ten, String diaChi, String truongLienKet) {
        super(maSV, ten, diaChi);
        this.truongLienKet = truongLienKet;
    }

    @Override
    public void displayInfo() {
        System.out.println("Sinh vien lien ket:");
        System.out.println("Ma SV: " + maSV);
        System.out.println("Ten: " + ten);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("Truong lien ket: " + truongLienKet);
        System.out.println("------------------------");
    }
}
