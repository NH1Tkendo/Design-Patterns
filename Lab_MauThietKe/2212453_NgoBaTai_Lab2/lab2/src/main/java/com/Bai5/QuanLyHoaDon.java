/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai5;

import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyHoaDon {
    private ArrayList<HoaDon> danhSach;

    public QuanLyHoaDon() {
        danhSach = new ArrayList<>();
    }

    public void nhapDanhSach() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong hoa don: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Hoa don thu " + (i + 1));
            System.out.print("Loai hoa don (1 - Gio, 2 - Ngay): ");
            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ma HD: ");
            String maHD = scanner.nextLine();
            System.out.print("Ten KH: ");
            String tenKH = scanner.nextLine();
            System.out.print("Ma phong: ");
            String maPhong = scanner.nextLine();
            System.out.print("Ngay: ");
            int ngay = scanner.nextInt();
            System.out.print("Thang: ");
            int thang = scanner.nextInt();
            System.out.print("Nam: ");
            int nam = scanner.nextInt();
            System.out.print("Don gia: ");
            double donGia = scanner.nextDouble();

            if (type == 1) {
                System.out.print("So gio: ");
                int soGio = scanner.nextInt();
                danhSach.add(new HoaDonGio(maHD, ngay, thang, nam, tenKH, maPhong, donGia, soGio));
            } else {
                System.out.print("So ngay: ");
                int soNgay = scanner.nextInt();
                danhSach.add(new HoaDonNgay(maHD, ngay, thang, nam, tenKH, maPhong, donGia, soNgay));
            }
            scanner.nextLine();
        }
    }

    public void xuatDanhSach() {
        System.out.println("\nDanh sach hoa don:");
        for (HoaDon hd : danhSach) {
            hd.displayInfo();
        }
    }

    public void tongSoLuong() {
        int gio = 0, ngay = 0;
        for (HoaDon hd : danhSach) {
            if (hd instanceof HoaDonGio) {
                gio++;
            } else if (hd instanceof HoaDonNgay) {
                ngay++;
            }
        }
        System.out.println("\nTong so luong:");
        System.out.println("Gio: " + gio);
        System.out.println("Ngay: " + ngay);
    }

    public void trungBinhThanhTienThang9Nam2025() {
        double tong = 0;
        int dem = 0;
        for (HoaDon hd : danhSach) {
            if (hd.getThang() == 9 && hd.getNam() == 2025) {
                tong += hd.getThanhTien();
                dem++;
            }
        }
        if (dem > 0) {
            System.out.println("Trung binh thanh tien thang 9 nam 2025: " + (tong / dem));
        } else {
            System.out.println("Khong co hoa don thang 9 nam 2025!");
        }
    }
}
