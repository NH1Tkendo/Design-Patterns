package com.ngobatai_lmhaup.core;

// Transaction chứa nhiều item
import java.util.*;

public class Transaction {
    private int tid;
    private List<TransactionItem> items;

    public Transaction(int tid) {
        this.tid = tid;
        this.items = new ArrayList<>();
    }

    public int getTid() {
        return tid;
    }

    public List<TransactionItem> getItems() {
        return items;
    }

    public void addItem(TransactionItem ti) {
        items.add(ti);
    }

    public double getTotalUtility() {
        return items.stream().mapToDouble(TransactionItem::getUtility).sum();
    }
}
