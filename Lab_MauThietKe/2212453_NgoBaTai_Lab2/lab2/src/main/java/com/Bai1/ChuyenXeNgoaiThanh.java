package com.Bai1;

public class ChuyenXeNgoaiThanh extends ChuyenXe {
    private String noiDen;
    private int soNgayDiDuoc;

    public ChuyenXeNgoaiThanh(String maSoChuyen, String hoTenTaiXe, int soXe, double doanhThu, String noiDen,
            int soNgayDiDuoc) {
        super(maSoChuyen, hoTenTaiXe, soXe, doanhThu);
        this.noiDen = noiDen;
        this.soNgayDiDuoc = soNgayDiDuoc;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%s   %d", this.noiDen, this.soNgayDiDuoc);
    }
}
