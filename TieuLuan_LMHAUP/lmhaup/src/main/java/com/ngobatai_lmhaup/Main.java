package com.ngobatai_lmhaup;

import java.util.Arrays;

import com.ngobatai_lmhaup.miner.LmhaupMiner;
import com.ngobatai_lmhaup.model.Item;
import com.ngobatai_lmhaup.model.Transaction;
import com.ngobatai_lmhaup.model.TransactionItem;
import com.ngobatai_lmhaup.model.UtilityDatabase;

public class Main {
    public static void main(String[] args) {
        // Example: build a small DB
        UtilityDatabase db = new UtilityDatabase();

        // Define items with external utilities (profits)
        db.addItemDef(new Item(1, 4.0)); // a
        db.addItemDef(new Item(2, 10.0)); // c
        db.addItemDef(new Item(3, 6.0)); // f
        db.addItemDef(new Item(4, 8.0)); // e

        // Add transactions with quantities -> internal utilities
        // Note: In practice, compute util = qty * profit(itemId) when loading
        db.addTransaction(new Transaction(1, Arrays.asList(
                new TransactionItem(2, 3, 30.0), // c
                new TransactionItem(4, 1, 8.0) // e
        )));
        // ... add more transactions matching the paper's example

        double delta = 0.06; // for example, set according to paper
        LmhaupMiner miner = new LmhaupMiner(db, delta);
        miner.mine();

        // Print results
        for (int k = 0; k < miner.haupItemsets.size(); k++) {
            int[] is = miner.haupItemsets.get(k);
            double au = miner.haupAU.get(k);
            System.out.println(Arrays.toString(is) + " AU=" + au);
        }
    }
}