/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai6;

public class Employee extends Person {
    private final double heSoLuong;

    public Employee(String hoTen, String diaChi, double heSoLuong) {
        super(hoTen, diaChi);
        this.heSoLuong = heSoLuong;
    }

    public double luong() {
        return heSoLuong * 1000000; // Gia su luong co ban 1tr
    }

    public String danhGia() {
        double l = luong();
        if (l >= 10000000)
            return "Cao";
        if (l >= 5000000)
            return "Trung binh";
        return "Thap";
    }

    @Override
    public String toString() {
        return "Employee:\n" + super.toString() + "\nHe so luong: " + heSoLuong + "\nLuong: " + luong() + "\nDanh gia: "
                + danhGia();
    }
}
