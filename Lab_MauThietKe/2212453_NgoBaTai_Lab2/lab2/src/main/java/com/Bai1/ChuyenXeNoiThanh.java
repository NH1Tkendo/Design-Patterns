package com.Bai1;

public class ChuyenXeNoiThanh extends ChuyenXe {
    private float soKmDiDuoc;
    private int soTuyen;

    public ChuyenXeNoiThanh(String maSoChuyen, String hoTenTaiXe, int soXe, double doanhThu, float soKmDiDuoc,
            int soTuyen) {
        super(maSoChuyen, hoTenTaiXe, soXe, doanhThu);
        this.soKmDiDuoc = soKmDiDuoc;
        this.soTuyen = soTuyen;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%.2f   %d", this.soKmDiDuoc, this.soTuyen);
    }
}
