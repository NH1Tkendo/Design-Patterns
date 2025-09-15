package com.Bai1;

public abstract class ChuyenXe {
    protected String maSoChuyen;
    protected String hoTenTaiXe;
    protected int soXe;
    protected double doanhThu;

    public ChuyenXe(String maSoChuyen, String hoTenTaiXe, int soXe, double doanhThu) {
        this.maSoChuyen = maSoChuyen;
        this.hoTenTaiXe = hoTenTaiXe;
        this.soXe = soXe;
        this.doanhThu = doanhThu;
    }

    @Override
    public String toString() {
        return String.format("%s    %s    %d    %.2f", this.maSoChuyen, this.hoTenTaiXe, this.soXe, this.doanhThu);
    }

    public double getDoanhThu() {
        return this.doanhThu;
    }
}
