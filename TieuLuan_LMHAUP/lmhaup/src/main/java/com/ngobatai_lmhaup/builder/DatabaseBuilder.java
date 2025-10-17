package com.ngobatai_lmhaup.builder;

import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Fluent Builder for UtilityDatabase
 * Provides a clean, readable API for building database with items and
 * transactions
 */
public class DatabaseBuilder {
    private final UtilityDatabase database;
    private final ItemBuilder itemBuilder;
    private final TransactionBuilder transactionBuilder;

    public DatabaseBuilder() {
        this.database = new UtilityDatabase();
        this.itemBuilder = new ItemBuilder(database, this);
        this.transactionBuilder = new TransactionBuilder(database, this);
    }

    /**
     * Start building items
     */
    public ItemBuilder items() {
        return itemBuilder;
    }

    /**
     * Start building transactions
     */
    public TransactionBuilder transactions() {
        return transactionBuilder;
    }

    /**
     * Get the built database
     */
    public UtilityDatabase build() {
        return database;
    }

    /**
     * Static factory method for fluent API
     */
    public static DatabaseBuilder create() {
        return new DatabaseBuilder();
    }
}
