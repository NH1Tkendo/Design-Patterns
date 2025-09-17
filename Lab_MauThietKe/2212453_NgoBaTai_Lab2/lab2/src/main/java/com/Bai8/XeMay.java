/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai8;

public class XeMay extends Xe {
    public XeMay(double giaTri) {
        super(giaTri);
    }

    @Override
    public double tinhThue() {
        return giaTri * 0.01 + giaTri * 0.05;
    }

    @Override
    public void displayInfo() {
        System.out.println("Xe may:");
        super.displayInfo();
        System.out.println("------------------------");
    }
}
