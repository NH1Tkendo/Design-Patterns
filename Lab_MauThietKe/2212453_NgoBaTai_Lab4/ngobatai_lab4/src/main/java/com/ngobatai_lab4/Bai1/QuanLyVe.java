package com.ngobatai_lab4.Bai1;

import java.util.ArrayList;
import java.util.List;

public class QuanLyVe {
    private List<Ve> danhSachVe = new ArrayList<>();

    public void nhapDanhSach(Ve ve) {
        danhSachVe.add(ve);
    }

    public double tinhTongTien() {
        Double tong = 0.0;
        for (Ve ve : danhSachVe) {
            tong += ve.tinhTien();
        }
        return tong;
    }

    public int demVeTungPhan() {
        int count = 0;
        for (Ve ve : danhSachVe) {
            if (ve instanceof VeTungPhan) {
                count++;
            }
        }
        return count;
    }
}
