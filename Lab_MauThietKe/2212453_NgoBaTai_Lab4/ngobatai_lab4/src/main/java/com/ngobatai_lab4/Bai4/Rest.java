package com.ngobatai_lab4.Bai4;

public class Rest extends Symbol {
    public Rest(Duration d) {
        super(d);
    }

    @Override
    public boolean isRest() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("Dấu lặng (%s)", dur.label());
    }
}
