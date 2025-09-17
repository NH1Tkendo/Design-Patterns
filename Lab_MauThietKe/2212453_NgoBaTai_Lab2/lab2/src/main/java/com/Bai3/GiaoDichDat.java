package com.Bai3;

class GiaoDichDat extends GiaoDichNhaDat {
    private String loaiDat; // A, B, C

    public GiaoDichDat(String maGD, int ngay, int thang, int nam, double donGia, double dienTich, String loaiDat) {
        super(maGD, ngay, thang, nam, donGia, dienTich);
        this.loaiDat = loaiDat.toUpperCase();
        tinhThanhTien();
    }

    @Override
    public void tinhThanhTien() {
        if (loaiDat.equals("A")) {
            this.thanhTien = dienTich * donGia * 1.5;
        } else {
            this.thanhTien = dienTich * donGia;
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Giao dich dat:");
        super.displayInfo();
        System.out.println("Loai dat: " + loaiDat);
        System.out.println("------------------------");
    }
}
