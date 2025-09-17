/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai9;

import java.util.Scanner;

public class KhachHang extends ThongTinConNguoi {
    private String tenCongTy;
    private double triGiaHoaDon;

    public void nhapThongTin(Scanner scanner) {
        System.out.print("Nhap ten cong ty: ");
        tenCongTy = scanner.nextLine();
        System.out.print("Nhap tri gia hoa don: ");
        triGiaHoaDon = scanner.nextDouble();
        scanner.nextLine();
    }

    public void thayDoiThongTin(Scanner scanner) {
        System.out.print("Nhap tri gia hoa don moi: ");
        triGiaHoaDon = scanner.nextDouble();
        scanner.nextLine();
    }

    @Override
    public String toString() {
        return "Khach hang:\n" + super.toString() + "\nTen cong ty: " + tenCongTy + "\nTri gia hoa don: "
                + triGiaHoaDon;
    }
}
