/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai9;

import java.util.Scanner;

public class SinhVien extends ThongTinConNguoi {
    private double diem1, diem2;

    public void nhapDiem(Scanner scanner) {
        System.out.print("Nhap diem 1: ");
        diem1 = scanner.nextDouble();
        System.out.print("Nhap diem 2: ");
        diem2 = scanner.nextDouble();
        scanner.nextLine();
    }

    public double tinhTongDiem() {
        return diem1 + diem2;
    }

    public void thayDoiThongTin(Scanner scanner, int type) {
        if (type == 0) {
            System.out.print("Nhap diem 1 moi: ");
            diem1 = scanner.nextDouble();
            scanner.nextLine();
        } else if (type == -1) {
            System.out.print("Nhap diem 2 moi: ");
            diem2 = scanner.nextDouble();
            scanner.nextLine();
        }
    }

    @Override
    public String toString() {
        return "Sinh vien:\n" + super.toString() + "\nDiem 1: " + diem1 + "\nDiem 2: " + diem2 + "\nTong diem: "
                + tinhTongDiem();
    }
}
