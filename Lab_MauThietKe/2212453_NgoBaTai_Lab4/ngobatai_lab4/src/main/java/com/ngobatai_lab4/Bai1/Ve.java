package com.ngobatai_lab4.Bai1;

public abstract class Ve {
    private String maVe;
    private String hoTen;
    private int namSinh;
    private int soTroChoi;

    public Ve(String maVe, String hoTen, int namSinh, int soTroChoi) {
        this.maVe = maVe;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.soTroChoi = soTroChoi;
    }

    public abstract double tinhTien();

    public int getSTC() {
        return this.soTroChoi;
    }
}
