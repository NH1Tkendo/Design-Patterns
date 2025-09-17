/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai9;

import java.util.Scanner;

public class NhanVien extends ThongTinConNguoi {
    private double luong;

    public void nhapLuong(Scanner scanner) {
        System.out.print("Nhap luong: ");
        luong = scanner.nextDouble();
        scanner.nextLine();
    }

    public double tinhThuong() {
        return luong * 0.1; // Giả sử thưởng 10%
    }

    public void thayDoiThongTin(Scanner scanner) {
        System.out.print("Nhap luong moi: ");
        luong = scanner.nextDouble();
        scanner.nextLine();
    }

    @Override
    public String toString() {
        return "Nhan vien:\n" + super.toString() + "\nLuong: " + luong + "\nThuong: " + tinhThuong();
    }
}
