package com.Bai2;

class GiaoDichVang extends GiaoDich {
    private final String loaiVang;

    public GiaoDichVang(String maGD, int ngay, int thang, int nam, double donGia, int soLuong, String loaiVang) {
        super(maGD, ngay, thang, nam, donGia, soLuong);
        this.loaiVang = loaiVang;
        tinhThanhTien();
    }

    @Override
    public void tinhThanhTien() {
        this.thanhTien = soLuong * donGia;
    }

    @Override
    public void displayInfo() {
        System.out.println("Giao dich vang:");
        super.displayInfo();
        System.out.println("Loai vang: " + loaiVang);
        System.out.println("------------------------");
    }
}
