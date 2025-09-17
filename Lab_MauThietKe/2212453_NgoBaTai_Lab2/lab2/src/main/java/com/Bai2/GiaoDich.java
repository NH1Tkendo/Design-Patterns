package com.Bai2;

abstract class GiaoDich {
    protected String maGD;
    protected int ngay, thang, nam;
    protected double donGia;
    protected int soLuong;
    protected double thanhTien;

    public GiaoDich(String maGD, int ngay, int thang, int nam, double donGia, int soLuong) {
        this.maGD = maGD;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public abstract void tinhThanhTien();

    public String getMaGD() {
        return maGD;
    }

    public double getDonGia() {
        return donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void displayInfo() {
        System.out.println("Ma GD: " + maGD);
        System.out.println("Ngay GD: " + ngay + "/" + thang + "/" + nam);
        System.out.println("Don gia: " + donGia);
        System.out.println("So luong: " + soLuong);
        System.out.println("Thanh tien: " + thanhTien);
    }
}
