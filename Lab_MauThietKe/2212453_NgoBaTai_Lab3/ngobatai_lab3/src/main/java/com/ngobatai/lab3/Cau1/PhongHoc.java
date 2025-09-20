package com.ngobatai.lab3.Cau1;

public abstract class PhongHoc {
    protected String maPhong;
    protected String dayNha;
    protected double dienTich;
    protected int soBongDen;

    public PhongHoc(String maPhong, String dayNha, double dienTich, int soBongDen) {
        this.maPhong = maPhong;
        this.dayNha = dayNha;
        this.dienTich = dienTich;
        this.soBongDen = soBongDen;
    }

    // Phương thức kiểm tra đủ ánh sáng (10m2 - 1 bóng đèn)
    public boolean duAnhSang() {
        return soBongDen >= dienTich / 10.0;
    }

    // Phương thức trừu tượng kiểm tra đạt chuẩn
    public abstract boolean datChuan();

    public String getMaPhong() {
        return maPhong;
    }

    public String getDayNha() {
        return dayNha;
    }

    public double getDienTich() {
        return dienTich;
    }

    public int getSoBongDen() {
        return soBongDen;
    }

    @Override
    public String toString() {
        return String.format("Mã phòng: %s, Dãy nhà: %s, Diện tích: %.1fm², Số bóng đèn: %d",
                maPhong, dayNha, dienTich, soBongDen);
    }
}
