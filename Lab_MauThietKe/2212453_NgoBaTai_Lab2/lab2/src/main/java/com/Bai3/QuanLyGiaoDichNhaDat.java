package com.Bai3;

import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyGiaoDichNhaDat {
    private ArrayList<GiaoDichNhaDat> danhSach;

    public QuanLyGiaoDichNhaDat() {
        danhSach = new ArrayList<>();
    }

    public void nhapDanhSach() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong giao dich: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Giao dich thu " + (i + 1));
            System.out.print("Loai giao dich (1 - Dat, 2 - Nha): ");
            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ma GD: ");
            String maGD = scanner.nextLine();
            System.out.print("Ngay: ");
            int ngay = scanner.nextInt();
            System.out.print("Thang: ");
            int thang = scanner.nextInt();
            System.out.print("Nam: ");
            int nam = scanner.nextInt();
            System.out.print("Don gia: ");
            double donGia = scanner.nextDouble();
            System.out.print("Dien tich: ");
            double dienTich = scanner.nextDouble();
            scanner.nextLine();

            if (type == 1) {
                System.out.print("Loai dat (A/B/C): ");
                String loaiDat = scanner.nextLine();
                danhSach.add(new GiaoDichDat(maGD, ngay, thang, nam, donGia, dienTich, loaiDat));
            } else {
                System.out.print("Loai nha (cao cap/thuong): ");
                String loaiNha = scanner.nextLine();
                System.out.print("Dia chi: ");
                String diaChi = scanner.nextLine();
                danhSach.add(new GiaoDichNha(maGD, ngay, thang, nam, donGia, dienTich, loaiNha, diaChi));
            }
        }
    }

    public void xuatDanhSach() {
        System.out.println("\nDanh sach giao dich:");
        for (GiaoDichNhaDat gd : danhSach) {
            gd.displayInfo();
        }
    }

    public void tongSoLuong() {
        double dat = 0, nha = 0;
        for (GiaoDichNhaDat gd : danhSach) {
            if (gd instanceof GiaoDichDat) {
                dat += gd.getDienTich();
            } else if (gd instanceof GiaoDichNha) {
                nha += gd.getDienTich();
            }
        }
        System.out.println("\nTong so luong:");
        System.out.println("Dat: " + dat);
        System.out.println("Nha: " + nha);
    }

    public void trungBinhThanhTienDat() {
        double tong = 0;
        int dem = 0;
        for (GiaoDichNhaDat gd : danhSach) {
            if (gd instanceof GiaoDichDat) {
                tong += gd.getThanhTien();
                dem++;
            }
        }
        if (dem > 0) {
            System.out.println("Trung binh thanh tien dat: " + (tong / dem));
        } else {
            System.out.println("Khong co giao dich dat!");
        }
    }

    public void giaoDichThang9Nam2025() {
        System.out.println("\nGiao dich thang 9 nam 2025:");
        for (GiaoDichNhaDat gd : danhSach) {
            if (gd.getThang() == 9 && gd.getNam() == 2025) {
                gd.displayInfo();
            }
        }
    }
}
