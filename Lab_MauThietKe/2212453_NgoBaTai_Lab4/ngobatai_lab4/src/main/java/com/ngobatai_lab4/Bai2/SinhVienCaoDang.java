package com.ngobatai_lab4.Bai2;

public class SinhVienCaoDang extends SinhVien {
    private float diemThiTN;

    public SinhVienCaoDang(String maSV, String hoTen, String diaChi, int tongTV, float diemTB, float diemThiTN) {
        super(maSV, hoTen, diaChi, tongTV, diemTB);
        this.diemThiTN = diemThiTN;
    }

    @Override
    public boolean DuDKTotNghiep() {
        return tongTV >= 120 && diemTB >= 5 && diemThiTN >= 5;
    }

    public float getDiemThiTN() {
        return diemThiTN;
    }

    @Override
    public String toString() {
        return super.toString() + " - Điểm TN: " + diemThiTN;
    }
}
