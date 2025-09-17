/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai7;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HangSanhSu extends HangHoa {
    private final String nhaSX;
    private final Date ngayNhapKho;

    public HangSanhSu(String maHang, String tenHang, int soLuongTon, double donGia, String nhaSX, Date ngayNhapKho) {
        super(maHang, tenHang, soLuongTon, donGia);
        this.nhaSX = nhaSX;
        this.ngayNhapKho = ngayNhapKho;
    }

    public long thoiGianLuuKho() {
        long diff = new Date().getTime() - ngayNhapKho.getTime();
        return diff / (24 * 60 * 60 * 1000); // days
    }

    @Override
    public String danhGiaMucDoBan() {
        if (soLuongTon > 50 && thoiGianLuuKho() > 10) {
            return "Ban cham";
        }
        return "Khong danh gia";
    }

    @Override
    public double tinhVAT() {
        return donGia * 0.1;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Nha SX: " + nhaSX);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ngay nhap kho: " + sdf.format(ngayNhapKho));
        System.out.println("------------------------");
    }
}
