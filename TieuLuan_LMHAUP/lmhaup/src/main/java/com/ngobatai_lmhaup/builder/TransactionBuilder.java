package com.ngobatai_lmhaup.builder;

import java.util.ArrayList;
import java.util.List;

import com.ngobatai_lmhaup.model.Transaction;
import com.ngobatai_lmhaup.model.TransactionItem;
import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Builder for creating Transactions with fluent API
 */
public class TransactionBuilder {
    private int transactionId;
    private List<TransactionItem> items;
    private UtilityDatabase database;
    private DatabaseBuilder parentBuilder;

    public TransactionBuilder(UtilityDatabase database, DatabaseBuilder parentBuilder) {
        this.database = database;
        this.parentBuilder = parentBuilder;
        this.items = new ArrayList<>();
    }

    /**
     * Set transaction ID
     */
    public TransactionBuilder id(int transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    /**
     * Add an item to the transaction
     * 
     * @param itemId   The item ID
     * @param quantity The quantity of the item
     */
    public TransactionBuilder addItem(int itemId, int quantity) {
        items.add(new TransactionItem(itemId, quantity, database));
        return this;
    }

    /**
     * Add multiple items at once using item ID and quantity pairs
     * Example: addItems(1, 2, 3, 4) means item 1 with quantity 2, item 3 with
     * quantity 4
     */
    public TransactionBuilder addItems(int... itemIdQuantityPairs) {
        if (itemIdQuantityPairs.length % 2 != 0) {
            throw new IllegalArgumentException("Must provide pairs of itemId and quantity");
        }

        for (int i = 0; i < itemIdQuantityPairs.length; i += 2) {
            int itemId = itemIdQuantityPairs[i];
            int quantity = itemIdQuantityPairs[i + 1];
            items.add(new TransactionItem(itemId, quantity, database));
        }
        return this;
    }

    /**
     * Build and add the transaction to the database
     */
    public TransactionBuilder add() {
        Transaction transaction = new Transaction(transactionId, new ArrayList<>(items));
        database.addTransaction(transaction);
        // Reset for next transaction
        this.transactionId = 0;
        this.items.clear();
        return this;
    }

    /**
     * Build the transaction without adding to database
     */
    public Transaction build() {
        return new Transaction(transactionId, items);
    }

    /**
     * Finalize and get the database
     */
    public UtilityDatabase buildDatabase() {
        return parentBuilder.build();
    }
}
