package com.Bai1;

import java.util.ArrayList;
import java.util.List;

public class DSChuyenXe {
    private List<ChuyenXe> dscx;

    public DSChuyenXe() {
        dscx = new ArrayList<>();
    }

    public void Them(ChuyenXe cx) {
        dscx.add(cx);
    }

    public void HienThiDS() {
        for (ChuyenXe cx : dscx) {
            System.out.println(cx);
        }
    }

    public void tinhTongDoanhThu() {
        double tongNoiThanh = 0, tongNgoaiThanh = 0;
        for (ChuyenXe cx : dscx) {
            if (cx instanceof ChuyenXeNoiThanh) {
                tongNoiThanh += cx.getDoanhThu();
            } else if (cx instanceof ChuyenXeNgoaiThanh) {
                tongNgoaiThanh += cx.getDoanhThu();
            }
        }
        System.out.println("Tổng doanh thu nội thành: " + tongNoiThanh);
        System.out.println("Tổng doanh thu ngoại thành: " + tongNgoaiThanh);
    }
}
