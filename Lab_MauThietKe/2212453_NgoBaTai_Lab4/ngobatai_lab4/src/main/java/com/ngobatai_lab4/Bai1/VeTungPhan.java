package com.ngobatai_lab4.Bai1;

public class VeTungPhan extends Ve {
    public VeTungPhan(String maVe, String hoTen, int namSin, int soTroChoi) {
        super(maVe, hoTen, namSin, soTroChoi);
    }

    @Override
    public double tinhTien() {
        return 70.000 * this.getSTC();
    }
}
