package com.ngobatai.lab3.Cau1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class QuanLyPhongHoc {
    private List<PhongHoc> danhSachPhong;

    public QuanLyPhongHoc() {
        danhSachPhong = new ArrayList<>();
    }

    // Thêm phòng học
    public void themPhongHoc(PhongHoc phong) {
        danhSachPhong.add(phong);
    }

    public PhongHoc timTheoMaPhong(String maPhong) {
        for (PhongHoc ph : danhSachPhong) {
            if (maPhong == ph.getMaPhong()) {
                return ph;
            }
        }
        return null;
    }

    public void hienThiTatCaPhong() {
        System.out.println("\n=== DANH SÁCH TẤT CẢ PHÒNG HỌC ===");
        for (PhongHoc phong : danhSachPhong) {
            System.out.println(phong.toString());
        }
    }

    public void hienThiPhongDatChuan() {
        System.out.println("\n=== DANH SÁCH PHÒNG ĐẠT CHUẨN ===");
        boolean coPhongDatChuan = false;
        for (PhongHoc phong : danhSachPhong) {
            if (phong.datChuan()) {
                System.out.println(phong.toString());
                coPhongDatChuan = true;
            }
        }
        if (!coPhongDatChuan) {
            System.out.println("Không có phòng nào đạt chuẩn!");
        }
    }

    // Sắp xếp danh sách tăng dần theo cột dãy nhà
    public void sapXepTangDanTheoDayNha() {
        danhSachPhong.sort(Comparator.comparing(PhongHoc::getDayNha));
        System.out.println("Đã sắp xếp danh sách tăng dần theo dãy nhà.");
    }

    // Sắp xếp danh sách giảm dần theo cột diện tích
    public void sapXepGiamDanTheoDienTich() {
        danhSachPhong.sort(Comparator.comparing(PhongHoc::getDienTich).reversed());
        System.out.println("Đã sắp xếp danh sách giảm dần theo diện tích.");
    }

    // Sắp xếp danh sách tăng dần theo cột số bóng đèn
    public void sapXepTangDanTheoSoBongDen() {
        danhSachPhong.sort(Comparator.comparing(PhongHoc::getSoBongDen));
        System.out.println("Đã sắp xếp danh sách tăng dần theo số bóng đèn.");
    }

    public boolean capNhatSoMayTinh(String maPhong, int soMayTinhMoi) {
        PhongHoc phong = timTheoMaPhong(maPhong);
        if (phong != null && phong instanceof PhongMayTinh) {
            PhongMayTinh phongMayTinh = (PhongMayTinh) phong;
            int soMayTinhCu = phongMayTinh.getSoMayTinh();
            phongMayTinh.setSoMayTinh(soMayTinhMoi);
            System.out.printf("Đã cập nhật số máy tính phòng %s từ %d máy thành %d máy.\n",
                    maPhong, soMayTinhCu, soMayTinhMoi);
            return true;
        } else {
            System.out.printf("Không tìm thấy phòng máy tính với mã %s!\n", maPhong);
            return false;
        }
    }

    // Xóa một phòng học khi biết mã phòng với xác nhận
    public boolean xoaPhongHoc(String maPhong, Scanner scanner) {
        PhongHoc phong = timTheoMaPhong(maPhong);
        if (phong == null) {
            System.out.printf("Không tìm thấy phòng với mã %s!\n", maPhong);
            return false;
        }

        System.out.println("Thông tin phòng sẽ bị xóa:");
        System.out.println(phong.toString());
        System.out.print("Bạn có chắc chắn muốn xóa phòng này không? (y/n): ");

        String xacNhan = scanner.nextLine().trim().toLowerCase();
        if (xacNhan.equals("y") || xacNhan.equals("yes")) {
            danhSachPhong.remove(phong);
            System.out.printf("Đã xóa phòng %s thành công!\n", maPhong);
            return true;
        } else {
            System.out.println("Hủy thao tác xóa phòng.");
            return false;
        }
    }

    // In ra tổng số phòng học
    public void inTongSoPhongHoc() {
        System.out.printf("Tổng số phòng học: %d phòng\n", danhSachPhong.size());
    }

    // In danh sách các phòng máy có 60 máy trở lên
    public void inDanhSachPhongMay60May() {
        System.out.println("\n=== DANH SÁCH PHÒNG MÁY TÍNH CÓ TỪ 60 MÁY TRỞ LÊN ===");
        boolean coPhongDatYeuCau = false;
        for (PhongHoc phong : danhSachPhong) {
            if (phong instanceof PhongMayTinh) {
                PhongMayTinh phongMayTinh = (PhongMayTinh) phong;
                if (phongMayTinh.getSoMayTinh() >= 60) {
                    System.out.println(phongMayTinh.toString());
                    coPhongDatYeuCau = true;
                }
            }
        }
        if (!coPhongDatYeuCau) {
            System.out.println("Không có phòng máy tính nào có từ 60 máy trở lên!");
        }
    }
}
