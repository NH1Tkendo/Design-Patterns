/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai6;

public class Customer extends Person {
    private final String tenCongTy;
    private final double triGiaHD;

    public Customer(String hoTen, String diaChi, String tenCongTy, double triGiaHD) {
        super(hoTen, diaChi);
        this.tenCongTy = tenCongTy;
        this.triGiaHD = triGiaHD;
    }

    public String danhGia() {
        if (triGiaHD >= 100000000)
            return "Lon";
        if (triGiaHD >= 10000000)
            return "Trung binh";
        return "Nho";
    }

    @Override
    public String toString() {
        return "Customer:\n" + super.toString() + "\nTen cong ty: " + tenCongTy + "\nTri gia HD: " + triGiaHD
                + "\nDanh gia: " + danhGia();
    }
}
