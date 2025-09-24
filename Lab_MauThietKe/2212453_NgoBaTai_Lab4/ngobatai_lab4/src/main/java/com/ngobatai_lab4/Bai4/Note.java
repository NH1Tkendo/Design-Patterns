package com.ngobatai_lab4.Bai4;

public class Note extends Symbol {
    private Pitch pitch;

    public Note(Pitch p, Duration d) {
        super(d);
        this.pitch = p;
    }

    public Pitch getPitch() {
        return pitch;
    }

    @Override
    public boolean isRest() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Nốt %s (%s)", pitch, dur.label());
    }
}
