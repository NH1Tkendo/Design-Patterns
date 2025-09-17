package com.Bai2;

class GiaoDichTienTe extends GiaoDich {
    private final double tiGia;
    private final String loaiTienTe;

    public GiaoDichTienTe(String maGD, int ngay, int thang, int nam, double donGia, int soLuong, double tiGia,
            String loaiTienTe) {
        super(maGD, ngay, thang, nam, donGia, soLuong);
        this.tiGia = tiGia;
        this.loaiTienTe = loaiTienTe.toUpperCase();
        tinhThanhTien();
    }

    @Override
    public void tinhThanhTien() {
        if (loaiTienTe.equals("VND")) {
            this.thanhTien = soLuong * donGia;
        } else {
            this.thanhTien = soLuong * donGia * tiGia;
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Giao dich tien te:");
        super.displayInfo();
        System.out.println("Ti gia: " + tiGia);
        System.out.println("Loai tien te: " + loaiTienTe);
        System.out.println("------------------------");
    }
}
