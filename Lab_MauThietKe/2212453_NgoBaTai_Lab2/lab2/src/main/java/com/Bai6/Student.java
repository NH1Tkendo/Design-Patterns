/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai6;

public class Student extends Person {
    private final double diem1;
    private final double diem2;

    public Student(String hoTen, String diaChi, double diem1, double diem2) {
        super(hoTen, diaChi);
        this.diem1 = diem1;
        this.diem2 = diem2;
    }

    public double diemTB() {
        return (diem1 + diem2) / 2;
    }

    public String danhGia() {
        double tb = diemTB();
        if (tb >= 8)
            return "Gioi";
        if (tb >= 5)
            return "Kha";
        return "Yeu";
    }

    @Override
    public String toString() {
        return "Student:\n" + super.toString() + "\nDiem 1: " + diem1 + "\nDiem 2: " + diem2 + "\nDiem TB: " + diemTB()
                + "\nDanh gia: " + danhGia();
    }
}
