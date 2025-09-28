package com.ngobatai_lmhaup.core;

public class Pattern {
    private String items; // ví dụ "{a,c}"
    private double au; // average utility
    private double tmaub;
    private double mrau;

    public Pattern(String items) {
        this.items = items;
    }

    public String getItems() {
        return items;
    }

    public double getAu() {
        return au;
    }

    public void setAu(double au) {
        this.au = au;
    }

    public double getTmaub() {
        return tmaub;
    }

    public void setTmaub(double tmaub) {
        this.tmaub = tmaub;
    }

    public double getMrau() {
        return mrau;
    }

    public void setMrau(double mrau) {
        this.mrau = mrau;
    }
}
