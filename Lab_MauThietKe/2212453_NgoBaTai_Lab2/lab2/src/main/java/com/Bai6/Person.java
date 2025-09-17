/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai6;

class Person {
    protected String hoTen;
    protected String diaChi;

    public Person(String hoTen, String diaChi) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
    }

    public String getHoTen() {
        return hoTen;
    }

    @Override
    public String toString() {
        return "Ho ten: " + hoTen + "\nDia chi: " + diaChi;
    }
}
