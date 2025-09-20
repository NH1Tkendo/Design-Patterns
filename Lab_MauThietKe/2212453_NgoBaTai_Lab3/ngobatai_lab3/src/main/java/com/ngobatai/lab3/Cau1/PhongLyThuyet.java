package com.ngobatai.lab3.Cau1;

public class PhongLyThuyet extends PhongHoc {
    private boolean coMayChieu;

    public PhongLyThuyet(String maPhong, String dayNha, double dienTich, int soBongDen, boolean coMayChieu) {
        super(maPhong, dayNha, dienTich, soBongDen);
        this.coMayChieu = coMayChieu;
    }

    @Override
    public boolean datChuan() {
        return duAnhSang() && coMayChieu;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Có máy chiếu: %s, Đạt chuẩn: %s",
                coMayChieu ? "Có" : "Không",
                datChuan() ? "Có" : "Không");
    }
}
