package com.Bai3;

class GiaoDichNha extends GiaoDichNhaDat {
    private final String loaiNha; // cao cap, thuong
    private final String diaChi;

    public GiaoDichNha(String maGD, int ngay, int thang, int nam, double donGia, double dienTich, String loaiNha,
            String diaChi) {
        super(maGD, ngay, thang, nam, donGia, dienTich);
        this.loaiNha = loaiNha.toLowerCase();
        this.diaChi = diaChi;
        tinhThanhTien();
    }

    @Override
    public void tinhThanhTien() {
        if (loaiNha.equals("cao cap")) {
            this.thanhTien = dienTich * donGia;
        } else {
            this.thanhTien = dienTich * donGia * 0.9;
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Giao dich nha:");
        super.displayInfo();
        System.out.println("Loai nha: " + loaiNha);
        System.out.println("Dia chi: " + diaChi);
        System.out.println("------------------------");
    }
}
