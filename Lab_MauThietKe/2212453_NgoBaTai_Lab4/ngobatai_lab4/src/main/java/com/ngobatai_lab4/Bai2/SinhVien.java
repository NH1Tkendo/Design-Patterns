package com.ngobatai_lab4.Bai2;

public abstract class SinhVien {
    protected String maSV;
    protected String hoTen;
    protected String diaChi;
    protected int tongTV; // tổng số tín chỉ
    protected float diemTB; // điểm trung bình

    public SinhVien(String maSV, String hoTen, String diaChi, int tongTV, float diemTB) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.tongTV = tongTV;
        this.diemTB = diemTB;
    }

    public abstract boolean DuDKTotNghiep();

    public String getMaSV() {
        return maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public float getDiemTB() {
        return diemTB;
    }

    @Override
    public String toString() {
        return maSV + " - " + hoTen + " - " + diaChi + " - TC:" + tongTV + " - ĐTB:" + diemTB;
    }
}
