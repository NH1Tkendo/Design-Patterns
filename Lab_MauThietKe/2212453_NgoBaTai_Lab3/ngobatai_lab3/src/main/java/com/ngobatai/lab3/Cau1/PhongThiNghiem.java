package com.ngobatai.lab3.Cau1;

public class PhongThiNghiem extends PhongHoc {
    private String chuyenNganh;
    private int sucChua;
    private boolean coBonRua;

    public PhongThiNghiem(String maPhong, String dayNha, double dienTich, int soBongDen,
            String chuyenNganh, int sucChua, boolean coBonRua) {
        super(maPhong, dayNha, dienTich, soBongDen);
        this.chuyenNganh = chuyenNganh;
        this.sucChua = sucChua;
        this.coBonRua = coBonRua;
    }

    @Override
    public boolean datChuan() {
        return duAnhSang() && coBonRua;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Chuyên ngành: %s, Sức chứa: %d, Có bồn rửa: %s, Đạt chuẩn: %s",
                chuyenNganh, sucChua,
                coBonRua ? "Có" : "Không",
                datChuan() ? "Có" : "Không");
    }
}
