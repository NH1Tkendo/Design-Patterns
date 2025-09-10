package Cau3;

import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private long soTaiKhoan;
    private String tenTaiKhoan;
    private double soTienTrongTaiKhoan;
    private static final double LAI_SUAT = 0.035;
    private static final double PHI_RUT_TIEN = 0; // Gia su phi rut tien la 0

    public Account() {
        this.soTaiKhoan = 0;
        this.tenTaiKhoan = "";
        this.soTienTrongTaiKhoan = 0;
    }

    public Account(long soTaiKhoan, String tenTaiKhoan, double soTienTrongTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.soTienTrongTaiKhoan = soTienTrongTaiKhoan;
    }

    public Account(long soTaiKhoan, String tenTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.soTienTrongTaiKhoan = 50;
    }

    // Getters and Setters
    public long getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(long soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public double getSoTienTrongTaiKhoan() {
        return soTienTrongTaiKhoan;
    }

    public void setSoTienTrongTaiKhoan(double soTienTrongTaiKhoan) {
        this.soTienTrongTaiKhoan = soTienTrongTaiKhoan;
    }

    public void napTien(double soTien) {
        if (soTien > 0) {
            soTienTrongTaiKhoan += soTien;
        } else {
            System.out.println("So tien nap khong hop le!");
        }
    }

    public void rutTien(double soTien) {
        double tongRut = soTien + PHI_RUT_TIEN;
        if (soTien > 0 && tongRut <= soTienTrongTaiKhoan) {
            soTienTrongTaiKhoan -= tongRut;
        } else {
            System.out.println("So tien rut khong hop le hoac so du khong du!");
        }
    }

    public void daoHan() {
        soTienTrongTaiKhoan += soTienTrongTaiKhoan * LAI_SUAT;
    }

    public void chuyenKhoan(Account taiKhoanNhan, double soTien) {
        if (soTien > 0 && soTien <= soTienTrongTaiKhoan) {
            this.soTienTrongTaiKhoan -= soTien;
            taiKhoanNhan.soTienTrongTaiKhoan += soTien;
        } else {
            System.out.println("So tien chuyen khong hop le hoac so du khong du!");
        }
    }

    @Override
    public String toString() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.of("vi", "VN"));
        return "So tai khoan: " + soTaiKhoan +
                "\nTen tai khoan: " + tenTaiKhoan +
                "\nSo tien: " + currencyFormat.format(soTienTrongTaiKhoan);
    }
}
