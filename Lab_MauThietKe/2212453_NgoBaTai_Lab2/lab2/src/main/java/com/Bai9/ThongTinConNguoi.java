/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai9;

import java.util.Scanner;

public class ThongTinConNguoi {
    protected String ten;
    protected String diaChi;

    public void nhapTen(Scanner scanner) {
        System.out.print("Nhap ten: ");
        ten = scanner.nextLine();
    }

    public void nhapDiaChi(Scanner scanner) {
        System.out.print("Nhap dia chi: ");
        diaChi = scanner.nextLine();
    }

    @Override
    public String toString() {
        return "Ten: " + ten + "\nDia chi: " + diaChi;
    }
}
