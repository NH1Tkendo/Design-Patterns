package com.ngobatai_lmhaup.model;

import java.util.List;

public class Transaction {
    public final int tid;
    public final List<TransactionItem> items;

    public Transaction(int tid, List<TransactionItem> items) {
        this.tid = tid;
        this.items = items;
    }

    public double totalUtility() {
        double s = 0.0;
        for (TransactionItem io : items)
            s += io.util;
        return s;
    }
}
