/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.Bai7;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HangThucPham extends HangHoa {
    private final Date ngaySX;
    private final Date ngayHH;
    private final String nhaCungCap;

    public HangThucPham(String maHang, String tenHang, int soLuongTon, double donGia, Date ngaySX, Date ngayHH,
            String nhaCungCap) {
        super(maHang, tenHang, soLuongTon, donGia);
        this.ngaySX = ngaySX;
        this.ngayHH = (ngayHH.after(ngaySX) || ngayHH.equals(ngaySX)) ? ngayHH : new Date(ngaySX.getTime() + 86400000);
        this.nhaCungCap = nhaCungCap;
    }

    @Override
    public String danhGiaMucDoBan() {
        if (soLuongTon > 0 && ngayHH.before(new Date())) {
            return "Kho ban";
        }
        return "Khong danh gia";
    }

    @Override
    public double tinhVAT() {
        return donGia * 0.05;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ngay SX: " + sdf.format(ngaySX));
        System.out.println("Ngay HH: " + sdf.format(ngayHH));
        System.out.println("Nha cung cap: " + nhaCungCap);
        System.out.println("------------------------");
    }
}
