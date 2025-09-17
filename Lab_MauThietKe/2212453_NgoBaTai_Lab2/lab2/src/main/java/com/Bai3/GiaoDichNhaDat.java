package com.Bai3;

abstract class GiaoDichNhaDat {
    protected String maGD;
    protected int ngay, thang, nam;
    protected double donGia;
    protected double dienTich;
    protected double thanhTien;

    public GiaoDichNhaDat(String maGD, int ngay, int thang, int nam, double donGia, double dienTich) {
        this.maGD = maGD;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.donGia = donGia;
        this.dienTich = dienTich;
    }

    public abstract void tinhThanhTien();

    public int getThang() {
        return thang;
    }

    public int getNam() {
        return nam;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void displayInfo() {
        System.out.println("Ma GD: " + maGD);
        System.out.println("Ngay GD: " + ngay + "/" + thang + "/" + nam);
        System.out.println("Don gia: " + donGia);
        System.out.println("Dien tich: " + dienTich);
        System.out.println("Thanh tien: " + thanhTien);
    }
}
