/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai8;

public class OtoKhach extends Xe {
    private final int soCho;

    public OtoKhach(double giaTri, int soCho) {
        super(giaTri);
        this.soCho = soCho;
    }

    @Override
    public double tinhThue() {
        double thueDacBiet = (soCho < 5) ? 0.5 : 0.3;
        return giaTri * thueDacBiet + giaTri * 0.1 + giaTri * 0.2;
    }

    @Override
    public void displayInfo() {
        System.out.println("O to khach:");
        super.displayInfo();
        System.out.println("So cho: " + soCho);
        System.out.println("------------------------");
    }
}
