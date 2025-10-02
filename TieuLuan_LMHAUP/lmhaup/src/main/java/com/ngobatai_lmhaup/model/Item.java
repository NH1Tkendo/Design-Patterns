package com.ngobatai_lmhaup.model;

public class Item {
    public final int id;
    public final double profit; // external utility

    public Item(int id, double profit) {
        this.id = id;
        this.profit = profit;
    }
}