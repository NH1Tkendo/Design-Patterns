package com.ngobatai_lmhaup.builder;

import com.ngobatai_lmhaup.model.Item;
import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Builder for creating Item definitions with fluent API
 */
public class ItemBuilder {
    private int id;
    private String name;
    private double profit;
    private UtilityDatabase database;
    private DatabaseBuilder parentBuilder;

    public ItemBuilder(UtilityDatabase database, DatabaseBuilder parentBuilder) {
        this.database = database;
        this.parentBuilder = parentBuilder;
    }

    public ItemBuilder id(int id) {
        this.id = id;
        return this;
    }

    public ItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder profit(double profit) {
        this.profit = profit;
        return this;
    }

    /**
     * Shorthand method to set all properties at once
     */
    public ItemBuilder item(int id, String name, double profit) {
        this.id = id;
        this.name = name;
        this.profit = profit;
        return this;
    }

    /**
     * Build and add the item to the database
     */
    public ItemBuilder add() {
        Item item = new Item(id, name, profit);
        database.addItemDef(item);
        // Reset for next item
        this.id = 0;
        this.name = null;
        this.profit = 0.0;
        return this;
    }

    /**
     * Build the item without adding to database
     */
    public Item build() {
        return new Item(id, name, profit);
    }

    /**
     * Switch to transaction builder
     */
    public TransactionBuilder transactions() {
        return parentBuilder.transactions();
    }
}
