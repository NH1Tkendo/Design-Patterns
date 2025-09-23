package com.ngobatai_lab6.baitap;

public abstract class LibraryItem {
    private int copy;

    public abstract void display();

    public int getCopy(){
        return this.copy;
    }
}
