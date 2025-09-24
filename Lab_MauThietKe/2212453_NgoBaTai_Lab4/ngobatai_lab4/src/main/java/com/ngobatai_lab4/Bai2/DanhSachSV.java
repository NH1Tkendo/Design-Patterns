package com.ngobatai_lab4.Bai2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DanhSachSV {
    private List<SinhVien> dssv;

    public DanhSachSV() {
        dssv = new ArrayList<>();
    }

    public void NhapDSSV() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số lượng SV: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("Chọn loại SV (1. Đại học, 2. Cao đẳng): ");
            int loai = Integer.parseInt(sc.nextLine());

            System.out.print("Mã SV: ");
            String ma = sc.nextLine();
            System.out.print("Họ tên: ");
            String ten = sc.nextLine();
            System.out.print("Địa chỉ: ");
            String diaChi = sc.nextLine();
            System.out.print("Tổng số tín chỉ: ");
            int tc = Integer.parseInt(sc.nextLine());
            System.out.print("Điểm trung bình: ");
            float dtb = Float.parseFloat(sc.nextLine());

            if (loai == 1) {
                System.out.print("Tên luận văn: ");
                String tenLV = sc.nextLine();
                System.out.print("Điểm luận văn: ");
                float diemLV = Float.parseFloat(sc.nextLine());
                dssv.add(new SinhVienDaiHoc(ma, ten, diaChi, tc, dtb, tenLV, diemLV));
            } else {
                System.out.print("Điểm thi tốt nghiệp: ");
                float diemTN = Float.parseFloat(sc.nextLine());
                dssv.add(new SinhVienCaoDang(ma, ten, diaChi, tc, dtb, diemTN));
            }
        }
        sc.close();
    }

    public int SoLuongSVDuDKTN() {
        int count = 0;
        for (SinhVien sv : dssv) {
            if (sv.DuDKTotNghiep()) {
                count++;
            }
        }
        return count;
    }

    public SinhVien SVDHCoDiemTBCaoNhat() {
        SinhVienDaiHoc max = null;
        for (SinhVien sv : dssv) {
            if (sv instanceof SinhVienDaiHoc) {
                SinhVienDaiHoc dh = (SinhVienDaiHoc) sv;
                if (max == null || dh.getDiemTB() > max.getDiemTB()) {
                    max = dh;
                }
            }
        }
        return max;
    }

    public void XuatDSSV() {
        for (SinhVien sv : dssv) {
            System.out.println(sv + " - Tốt nghiệp: " + (sv.DuDKTotNghiep() ? "Được" : "Không"));
        }
    }
}
