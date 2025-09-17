/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai8;

abstract class Xe {
    protected double giaTri;

    public Xe(double giaTri) {
        this.giaTri = giaTri;
    }

    public abstract double tinhThue();

    public double getGiaTri() {
        return giaTri;
    }

    public void displayInfo() {
        System.out.println("Gia tri: " + giaTri);
        System.out.println("Thue: " + tinhThue());
    }
}
