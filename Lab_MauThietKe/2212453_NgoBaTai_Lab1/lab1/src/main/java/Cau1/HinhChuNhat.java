package Cau1;

public class HinhChuNhat {
    // constructor
    public HinhChuNhat(double dai, double rong) {
        this.dai = dai;
        this.rong = rong;
    }

    private double dai;
    private double rong;

    public double getChieuDai() {
        return dai;
    }

    public void setChieuDai(double cd) {
        this.dai = cd;
    }

    public double getChieuRong() {
        return rong;
    }

    public void setChieuRong(double cr) {
        this.rong = cr;
    }

    double tinhDienTich() {
        return dai * rong;
    }

    double tinhChuVi() {
        return (dai + rong) * 2;
    }

    @Override
    public String toString() {
        return "Chieu dai: " + dai + "\nChieu rong: " + rong
                + "\nDien tich: " + tinhDienTich() + "\nChu vi: " + tinhChuVi();
    }
}
