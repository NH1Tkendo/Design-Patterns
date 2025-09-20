package com.ngobatai.lab3.Cau1;

public class PhongMayTinh extends PhongHoc {
    private int soMayTinh;

    public PhongMayTinh(String maPhong, String dayNha, double dienTich, int soBongDen, int soMayTinh) {
        super(maPhong, dayNha, dienTich, soBongDen);
        this.soMayTinh = soMayTinh;
    }

    @Override
    public boolean datChuan() {
        // Phòng máy tính đạt chuẩn nếu đủ ánh sáng và trung bình 1.5m2/1 máy
        return duAnhSang() && soMayTinh >= dienTich / 1.5;
    }

    public int getSoMayTinh() {
        return soMayTinh;
    }

    public void setSoMayTinh(int soMayTinh) {
        this.soMayTinh = soMayTinh;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Số máy tính: %d, Đạt chuẩn: %s",
                soMayTinh, datChuan() ? "Có" : "Không");
    }
}
