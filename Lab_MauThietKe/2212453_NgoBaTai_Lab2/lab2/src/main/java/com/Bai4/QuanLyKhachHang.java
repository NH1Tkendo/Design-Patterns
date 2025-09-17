/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai4;

import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyKhachHang {
    private ArrayList<KhachHang> danhSach;

    public QuanLyKhachHang() {
        danhSach = new ArrayList<>();
    }

    public void nhapDanhSach() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong khach hang: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Khach hang thu " + (i + 1));
            System.out.print("Loai khach hang (1 - VN, 2 - Nuoc ngoai): ");
            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ma KH: ");
            String maKH = scanner.nextLine();
            System.out.print("Ho ten: ");
            String hoTen = scanner.nextLine();
            System.out.print("Ngay: ");
            int ngay = scanner.nextInt();
            System.out.print("Thang: ");
            int thang = scanner.nextInt();
            System.out.print("Nam: ");
            int nam = scanner.nextInt();
            System.out.print("So luong: ");
            int soLuong = scanner.nextInt();
            System.out.print("Don gia: ");
            double donGia = scanner.nextDouble();
            scanner.nextLine();

            if (type == 1) {
                System.out.print("Doi tuong (sinh hoat/kinh doanh/san xuat): ");
                String doiTuong = scanner.nextLine();
                System.out.print("Dinh muc: ");
                int dinhMuc = scanner.nextInt();
                scanner.nextLine();
                danhSach.add(new KhachHangVN(maKH, hoTen, ngay, thang, nam, soLuong, donGia, doiTuong, dinhMuc));
            } else {
                System.out.print("Quoc tich: ");
                String quocTich = scanner.nextLine();
                danhSach.add(new KhachHangNuocNgoai(maKH, hoTen, ngay, thang, nam, soLuong, donGia, quocTich));
            }
        }
    }

    public void xuatDanhSach() {
        System.out.println("\nDanh sach khach hang:");
        for (KhachHang kh : danhSach) {
            kh.displayInfo();
        }
    }

    public void tongSoLuong() {
        int vn = 0, nn = 0;
        for (KhachHang kh : danhSach) {
            if (kh instanceof KhachHangVN) {
                vn += kh.getSoLuong();
            } else if (kh instanceof KhachHangNuocNgoai) {
                nn += kh.getSoLuong();
            }
        }
        System.out.println("\nTong so luong:");
        System.out.println("VN: " + vn);
        System.out.println("Nuoc ngoai: " + nn);
    }

    public void trungBinhThanhTienNuocNgoai() {
        double tong = 0;
        int dem = 0;
        for (KhachHang kh : danhSach) {
            if (kh instanceof KhachHangNuocNgoai) {
                tong += kh.getThanhTien();
                dem++;
            }
        }
        if (dem > 0) {
            System.out.println("Trung binh thanh tien nuoc ngoai: " + (tong / dem));
        } else {
            System.out.println("Khong co khach hang nuoc ngoai!");
        }
    }

    public void hoaDonThang9Nam2013() {
        System.out.println("\nHoa don thang 9 nam 2013:");
        for (KhachHang kh : danhSach) {
            if (kh.getThang() == 9 && kh.getNam() == 2013) {
                kh.displayInfo();
            }
        }
    }
}
