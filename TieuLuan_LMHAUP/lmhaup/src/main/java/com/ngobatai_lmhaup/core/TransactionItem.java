package com.ngobatai_lmhaup.core;

public class TransactionItem {
    private Item item;
    private int quantity;

    public TransactionItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUtility() {
        return item.getProfit() * quantity;
    }
}
