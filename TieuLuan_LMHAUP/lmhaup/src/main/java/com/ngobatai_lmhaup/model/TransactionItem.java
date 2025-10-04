package com.ngobatai_lmhaup.model;

public class TransactionItem {
    public final int itemId;
    public final int qty;
    public final double util; // qty * profit

    public TransactionItem(int itemId, int qty, UtilityDatabase db) {
        this.itemId = itemId;
        this.qty = qty;
        Item item = db.items.get(itemId);
        this.util = qty * item.profit; // Tính U(P,T)
    }
}
