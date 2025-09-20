package com.ngobatai.lab3.Cau2;

public class KhachHang {
    private String soCMND;
    private String tenKhachHang;
    private String gaDen;
    private double giaTien;

    public KhachHang(String soCMND, String tenKhachHang, String gaDen, double giaTien) {
        this.soCMND = soCMND;
        this.tenKhachHang = tenKhachHang;
        this.gaDen = gaDen;
        this.giaTien = giaTien;
    }

    // Getters
    public String getSoCMND() {
        return soCMND;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getGaDen() {
        return gaDen;
    }

    public double getGiaTien() {
        return giaTien;
    }

    // Setters
    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setGaDen(String gaDen) {
        this.gaDen = gaDen;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    @Override
    public String toString() {
        return String.format("CMND: %s | Tên: %-20s | Ga đến: %-15s | Giá vé: %,.0f VND",
                soCMND, tenKhachHang, gaDen, giaTien);
    }

    // Phương thức để lưu vào file
    public String toFileString() {
        return soCMND + ";" + tenKhachHang + ";" + gaDen + ";" + giaTien;
    }

    // Phương thức tạo đối tượng từ chuỗi file
    public static KhachHang fromFileString(String line) {
        String[] parts = line.split(";");
        if (parts.length == 4) {
            return new KhachHang(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
        }
        return null;
    }
}
