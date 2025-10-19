package com.ngobatai_lmhaup.service;

import com.ngobatai_lmhaup.miner.LmhaupMiner;
import com.ngobatai_lmhaup.model.UtilityDatabase;

import java.io.IOException;

public class MiningOrchestrator {
    private final String profitFilePath;
    private final String transactionFilePath;
    private final double delta;

    private DatabaseLoader databaseLoader;
    private ResultPrinter resultPrinter;

    public MiningOrchestrator(String profitFilePath, String transactionFilePath, double delta) {
        this.profitFilePath = profitFilePath;
        this.transactionFilePath = transactionFilePath;
        this.delta = delta;
        this.databaseLoader = new DatabaseLoader(profitFilePath, transactionFilePath);
        this.resultPrinter = new ResultPrinter();
    }

    public MiningOrchestrator withDatabaseLoader(DatabaseLoader loader) {
        this.databaseLoader = loader;
        return this;
    }

    public MiningOrchestrator withResultPrinter(ResultPrinter printer) {
        this.resultPrinter = printer;
        return this;
    }

    public void execute() throws IOException, Exception {
        // Print header
        resultPrinter.printHeader();

        // Step 1: Load database from files
        UtilityDatabase database = databaseLoader.loadDatabase();

        // Step 2: Run mining algorithm
        System.out.println("Running LMHAUP mining with delta = " + delta);
        System.out.println("-".repeat(60));

        LmhaupMiner miner = new LmhaupMiner(database, delta);
        miner.mine();

        // Step 3: Print results
        resultPrinter.printDetailedResults(miner, database, delta);
    }

    public MiningResult executeAndReturn() throws IOException, Exception {
        // Load database
        UtilityDatabase database = databaseLoader.loadDatabase();

        // Run mining
        LmhaupMiner miner = new LmhaupMiner(database, delta);
        miner.mine();

        return new MiningResult(miner, database);
    }

    public static class MiningResult {
        private final LmhaupMiner miner;
        private final UtilityDatabase database;

        public MiningResult(LmhaupMiner miner, UtilityDatabase database) {
            this.miner = miner;
            this.database = database;
        }

        public LmhaupMiner getMiner() {
            return miner;
        }

        public UtilityDatabase getDatabase() {
            return database;
        }

        public int getPatternCount() {
            return miner.haupItemsets.size();
        }
    }
}
