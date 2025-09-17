/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai10;

abstract class SinhVien {
    protected String maSV;
    protected String ten;
    protected String diaChi;

    public SinhVien(String maSV, String ten, String diaChi) {
        this.maSV = maSV;
        this.ten = ten;
        this.diaChi = diaChi;
    }

    public abstract void displayInfo();

    public String getMaSV() {
        return maSV;
    }
}
