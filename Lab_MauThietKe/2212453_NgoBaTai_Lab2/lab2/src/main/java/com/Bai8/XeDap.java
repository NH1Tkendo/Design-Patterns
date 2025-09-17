/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai8;

public class XeDap extends Xe {
    public XeDap(double giaTri) {
        super(giaTri);
    }

    @Override
    public double tinhThue() {
        return 0;
    }

    @Override
    public void displayInfo() {
        System.out.println("Xe dap:");
        super.displayInfo();
        System.out.println("------------------------");
    }
}
