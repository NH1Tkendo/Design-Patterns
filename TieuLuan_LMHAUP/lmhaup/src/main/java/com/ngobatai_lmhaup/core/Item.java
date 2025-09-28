package com.ngobatai_lmhaup.core;

public class Item {
    private String name;
    private double profit;

    public Item(String name, double profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public double getProfit() {
        return profit;
    }
}
