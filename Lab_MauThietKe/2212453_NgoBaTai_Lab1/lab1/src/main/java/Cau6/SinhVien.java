package Cau6;

public class SinhVien {
    private int maSV;
    private String hoTen;
    private String diaChi;
    private String sdt;

    public SinhVien() {
    }

    public SinhVien(int maSV, String hoTen, String diaChi, String sdt) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        setSdt(sdt);
    }

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        if (sdt != null && sdt.matches("\\d{7}")) {
            this.sdt = sdt;
        } else {
            this.sdt = "0000000";
        }
    }

    @Override
    public String toString() {
        return "Ma SV: " + maSV +
                "\nHo ten: " + hoTen +
                "\nDia chi: " + diaChi +
                "\nSDT: " + sdt;
    }
}
