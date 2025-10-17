package com.ngobatai_lmhaup.model;

public class Item {
    public final int id;
    public final String name; // Tên item (a, b, c, d, e, f)
    public final double profit; // external utility

    public Item(int id, String name, double profit) {
        this.id = id;
        this.name = name;
        this.profit = profit;
    }

    // Constructor cũ để tương thích ngược (nếu cần)
    public Item(int id, double profit) {
        this.id = id;
        this.name = String.valueOf(id); // Default name = id
        this.profit = profit;
    }
}