package com.ngobatai_lab4.Bai4;

public abstract class Symbol {
    protected Duration dur;

    public Symbol(Duration d) {
        this.dur = d;
    }

    public Duration getDuration() {
        return dur;
    }

    public abstract boolean isRest();

    public abstract String toString();
}
