package com.ngobatai_lab4.Bai1;

public class VeTronGoi extends Ve {
    public VeTronGoi(String maVe, String hoTen, int namSin, int soTroChoi) {
        super(maVe, hoTen, namSin, soTroChoi);
    }

    @Override
    public double tinhTien() {
        return 200.000;
    }
}
