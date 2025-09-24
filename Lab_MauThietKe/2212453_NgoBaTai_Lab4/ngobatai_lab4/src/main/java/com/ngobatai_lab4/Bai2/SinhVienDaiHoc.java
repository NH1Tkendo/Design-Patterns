package com.ngobatai_lab4.Bai2;

public class SinhVienDaiHoc extends SinhVien {
    private String tenLV;
    private float diemLV;

    public SinhVienDaiHoc(String maSV, String hoTen, String diaChi, int tongTV, float diemTB, String tenLV,
            float diemLV) {
        super(maSV, hoTen, diaChi, tongTV, diemTB);
        this.tenLV = tenLV;
        this.diemLV = diemLV;
    }

    @Override
    public boolean DuDKTotNghiep() {
        return tongTV >= 150 && diemTB >= 5 && diemLV >= 5;
    }

    public float getDiemLV() {
        return diemLV;
    }

    @Override
    public String toString() {
        return super.toString() + " - LV: " + tenLV + " - Điểm LV: " + diemLV;
    }
}
