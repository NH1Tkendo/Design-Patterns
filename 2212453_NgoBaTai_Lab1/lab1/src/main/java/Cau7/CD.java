package Cau7;

public class CD {
    private int maCD;
    private String tuaCD;
    private String caSy;
    private int soBaiHat;
    private double giaThanh;

    public CD() {
    }

    public CD(int maCD, String tuaCD, String caSy, int soBaiHat, double giaThanh) {
        this.maCD = maCD;
        this.tuaCD = tuaCD;
        this.caSy = caSy;
        setSoBaiHat(soBaiHat);
        setGiaThanh(giaThanh);
    }

    public int getMaCD() {
        return maCD;
    }

    public void setMaCD(int maCD) {
        this.maCD = maCD;
    }

    public String getTuaCD() {
        return tuaCD;
    }

    public void setTuaCD(String tuaCD) {
        this.tuaCD = tuaCD;
    }

    public String getCaSy() {
        return caSy;
    }

    public void setCaSy(String caSy) {
        this.caSy = caSy;
    }

    public int getSoBaiHat() {
        return soBaiHat;
    }

    public void setSoBaiHat(int soBaiHat) {
        this.soBaiHat = (soBaiHat > 0) ? soBaiHat : 1;
    }

    public double getGiaThanh() {
        return giaThanh;
    }

    public void setGiaThanh(double giaThanh) {
        this.giaThanh = (giaThanh > 0) ? giaThanh : 1.0;
    }

    @Override
    public String toString() {
        return "Ma CD: " + maCD +
                "\nTua CD: " + tuaCD +
                "\nCa sy: " + caSy +
                "\nSo bai hat: " + soBaiHat +
                "\nGia thanh: " + giaThanh;
    }
}
