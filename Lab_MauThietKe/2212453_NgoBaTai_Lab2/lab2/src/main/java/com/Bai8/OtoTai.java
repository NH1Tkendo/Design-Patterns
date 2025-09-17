/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai8;

class OtoTai extends Xe {
    public OtoTai(double giaTri) {
        super(giaTri);
    }

    @Override
    public double tinhThue() {
        return giaTri * 0.1 + giaTri * 0.02;
    }

    @Override
    public void displayInfo() {
        System.out.println("O to tai:");
        super.displayInfo();
        System.out.println("------------------------");
    }
}
