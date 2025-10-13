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

		// Định nghĩa theo table-2 (name, profit)
		db.addItemDef(new Item(1, 4.0)); // a
		db.addItemDef(new Item(2, 6.0)); // b
		db.addItemDef(new Item(3, 10.0)); // c
		db.addItemDef(new Item(4, 2.0)); // d
		db.addItemDef(new Item(5, 8.0)); // e
		db.addItemDef(new Item(6, 3.0)); // f

		// Add transactions with quantities -> internal utilities
		// Transaction 1: {b:1, c:3, d:1, e:1}
		db.addTransaction(new Transaction(1, Arrays.asList(
				new TransactionItem(2, 1, db),
				new TransactionItem(3, 3, db),
				new TransactionItem(4, 1, db),
				new TransactionItem(5, 1, db))));

		// Transaction 2: {a:2, b:1, d:1, e:2, f:4}
		db.addTransaction(new Transaction(2, Arrays.asList(
				new TransactionItem(1, 2, db),
				new TransactionItem(2, 1, db),
				new TransactionItem(4, 1, db),
				new TransactionItem(5, 2, db),
				new TransactionItem(6, 4, db))));

		// Transaction 3: {a:2, c:2, e:3, f:4}
		db.addTransaction(new Transaction(3, Arrays.asList(
				new TransactionItem(1, 2, db),
				new TransactionItem(3, 2, db),
				new TransactionItem(5, 3, db),
				new TransactionItem(6, 4, db))));

		// Transaction 4: {a:3, c:1, e:2, f:5}
		db.addTransaction(new Transaction(4, Arrays.asList(
				new TransactionItem(1, 3, db),
				new TransactionItem(3, 1, db),
				new TransactionItem(5, 2, db),
				new TransactionItem(6, 5, db))));

		// Transaction 5: {b:1, e:3}
		db.addTransaction(new Transaction(5, Arrays.asList(
				new TransactionItem(2, 1, db),
				new TransactionItem(5, 3, db))));

		// Transaction 6: {a:2, c:3, f:3}
		db.addTransaction(new Transaction(6, Arrays.asList(
				new TransactionItem(1, 2, db),
				new TransactionItem(3, 3, db),
				new TransactionItem(6, 3, db))));

		// Transaction 7: {a:4, c:4, e:1, f:2}
		db.addTransaction(new Transaction(7, Arrays.asList(
				new TransactionItem(1, 4, db),
				new TransactionItem(3, 4, db),
				new TransactionItem(5, 1, db),
				new TransactionItem(6, 2, db))));

		System.out.println("Item profit table" + "\n" + db.items);
		double delta = 0.17;
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