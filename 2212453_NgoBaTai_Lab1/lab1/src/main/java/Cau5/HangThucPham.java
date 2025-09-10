package Cau5;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class HangThucPham {
    private final String maHang;
    private String tenHang;
    private double donGia;
    private Date ngaySanXuat;
    private Date ngayHetHan;

    public HangThucPham(String maHang, String tenHang, double donGia, Date ngaySanXuat, Date ngayHetHan) {
        this.maHang = (maHang == null || maHang.isEmpty()) ? "DEFAULT" : maHang;
        setTenHang(tenHang);
        setDonGia(donGia);
        setNgaySanXuat(ngaySanXuat);
        setNgayHetHan(ngayHetHan);
    }

    public HangThucPham(String maHang) {
        this(maHang, "Unnamed", 1.0, new Date(), new Date(System.currentTimeMillis() + 86400000)); // Tomorrow
    }

    public String getMaHang() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = (tenHang == null || tenHang.isEmpty()) ? "Unnamed" : tenHang;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = (donGia > 0) ? donGia : 1.0;
    }

    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = (ngaySanXuat != null) ? ngaySanXuat : new Date();
        if (ngayHetHan != null && ngayHetHan.before(this.ngaySanXuat)) {
            ngayHetHan = new Date(this.ngaySanXuat.getTime() + 86400000);
        }
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        if (ngayHetHan != null && !ngayHetHan.before(ngaySanXuat)) {
            this.ngayHetHan = ngayHetHan;
        } else {
            this.ngayHetHan = new Date(ngaySanXuat.getTime() + 86400000);
        }
    }

    public boolean hetHan() {
        return ngayHetHan.before(new Date());
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getInstance(Locale.of("vi", "VN"));
        return "Ma hang: " + maHang +
                "\nTen hang: " + tenHang +
                "\nDon gia: " + nf.format(donGia) +
                "\nNgay san xuat: " + sdf.format(ngaySanXuat) +
                "\nNgay het han: " + sdf.format(ngayHetHan);
    }
}
