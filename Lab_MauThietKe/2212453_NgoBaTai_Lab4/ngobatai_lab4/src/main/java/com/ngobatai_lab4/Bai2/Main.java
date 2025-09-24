package com.ngobatai_lab4.Bai2;

public class Main {
    public static void main(String[] args) {
        DanhSachSV ds = new DanhSachSV();
        ds.NhapDSSV();

        System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
        ds.XuatDSSV();

        System.out.println("\nSố lượng SV đủ ĐK tốt nghiệp: " + ds.SoLuongSVDuDKTN());

        SinhVien svMax = ds.SVDHCoDiemTBCaoNhat();
        if (svMax != null) {
            System.out.println("SV ĐH có ĐTB cao nhất: " + svMax.getHoTen() + " - ĐTB: " + svMax.getDiemTB());
        } else {
            System.out.println("Không có SV Đại học nào.");
        }
    }
}
