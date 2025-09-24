package com.ngobatai_lab4.Bai4;

public enum Duration {
    WHOLE(4.0, "Tròn"),
    HALF(2.0, "Trắng"),
    QUARTER(1.0, "Đen"),
    EIGHTH(0.5, "Móc đơn"),
    SIXTEENTH(0.25, "Móc đôi");

    private final double value;
    private final String label;

    Duration(double v, String l) {
        this.value = v;
        this.label = l;
    }

    public double value() {
        return value;
    }

    public String label() {
        return label;
    }
}
