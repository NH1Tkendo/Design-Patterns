package com.ngobatai_lmhaup.model;

public class TransactionItem {
    public final int itemId;
    public final int qty;
    public final double util; // qty * profit

    public TransactionItem(int itemId, int qty, double util) {
        this.itemId = itemId;
        this.qty = qty;
        this.util = util;
    }
}
