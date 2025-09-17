package com.Bai2;

import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyGiaoDich {
    private ArrayList<GiaoDich> danhSach;

    public QuanLyGiaoDich() {
        danhSach = new ArrayList<>();
    }

    public void nhapDanhSach() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong giao dich: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Giao dich thu " + (i + 1));
            System.out.print("Loai giao dich (1 - Vang, 2 - Tien te): ");
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
            System.out.print("So luong: ");
            int soLuong = scanner.nextInt();
            scanner.nextLine();

            if (type == 1) {
                System.out.print("Loai vang: ");
                String loaiVang = scanner.nextLine();
                danhSach.add(new GiaoDichVang(maGD, ngay, thang, nam, donGia, soLuong, loaiVang));
            } else {
                System.out.print("Ti gia: ");
                double tiGia = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Loai tien te (VND/USD/Euro): ");
                String loaiTienTe = scanner.nextLine();
                danhSach.add(new GiaoDichTienTe(maGD, ngay, thang, nam, donGia, soLuong, tiGia, loaiTienTe));
            }
        }
    }

    public void xuatDanhSach() {
        System.out.println("\nDanh sach giao dich:");
        for (GiaoDich gd : danhSach) {
            gd.displayInfo();
        }
    }

    public void tongSoLuong() {
        int vang = 0, tienTe = 0;
        for (GiaoDich gd : danhSach) {
            if (gd instanceof GiaoDichVang) {
                vang += gd.getSoLuong();
            } else if (gd instanceof GiaoDichTienTe) {
                tienTe += gd.getSoLuong();
            }
        }
        System.out.println("\nTong so luong:");
        System.out.println("Vang: " + vang);
        System.out.println("Tien te: " + tienTe);
    }

    public void trungBinhThanhTienTienTe() {
        double tong = 0;
        int dem = 0;
        for (GiaoDich gd : danhSach) {
            if (gd instanceof GiaoDichTienTe) {
                tong += gd.getThanhTien();
                dem++;
            }
        }
        if (dem > 0) {
            System.out.println("Trung binh thanh tien tien te: " + (tong / dem));
        } else {
            System.out.println("Khong co giao dich tien te!");
        }
    }

    public void giaoDichDonGiaLon() {
        System.out.println("\nGiao dich don gia > 1 ty:");
        for (GiaoDich gd : danhSach) {
            if (gd.getDonGia() > 1000000000) {
                gd.displayInfo();
            }
        }
    }
}
