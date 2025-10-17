package com.ngobatai_lmhaup;

import com.ngobatai_lmhaup.builder.DatabaseBuilder;
import com.ngobatai_lmhaup.miner.LmhaupMiner;
import com.ngobatai_lmhaup.model.UtilityDatabase;

/**
 * Main class - Now using Builder Pattern for cleaner code
 */
public class Main {
	public static void main(String[] args) {
		// Build database using Builder Pattern
		UtilityDatabase db = buildDatabase();

		// Run mining
		double delta = 0.17;
		LmhaupMiner miner = new LmhaupMiner(db, delta);
		miner.mine();

		// Print results
		printResults(miner, db);
	}

	private static UtilityDatabase buildDatabase() {
		DatabaseBuilder builder = DatabaseBuilder.create();

		builder.items()
				.item(1, "a", 4.0)
				.add() // a
				.item(2, "b", 6.0)
				.add() // b
				.item(3, "c", 10.0)
				.add() // c
				.item(4, "d", 2.0)
				.add() // d
				.item(5, "e", 8.0)
				.add() // e
				.item(6, "f", 3.0)
				.add(); // f

		// Transaction 1: {b:1, c:3, d:1, e:1}
		builder.transactions()
				.id(1)
				.addItem(2, 1) // b:1
				.addItem(3, 3) // c:3
				.addItem(4, 1) // d:1
				.addItem(5, 1) // e:1
				.add();

		// Transaction 2: {a:2, b:1, d:1, e:2, f:4}
		builder.transactions()
				.id(2)
				.addItem(1, 2) // a:2
				.addItem(2, 1) // b:1
				.addItem(4, 1) // d:1
				.addItem(5, 2) // e:2
				.addItem(6, 4) // f:4
				.add();

		// Transaction 3: {a:2, c:2, e:3, f:4}
		builder.transactions()
				.id(3)
				.addItem(1, 2) // a:2
				.addItem(3, 2) // c:2
				.addItem(5, 3) // e:3
				.addItem(6, 4) // f:4
				.add();

		// Transaction 4: {a:3, c:1, e:2, f:5}
		builder.transactions()
				.id(4)
				.addItem(1, 3) // a:3
				.addItem(3, 1) // c:1
				.addItem(5, 2) // e:2
				.addItem(6, 5) // f:5
				.add();

		// Transaction 5: {b:1, e:3}
		builder.transactions()
				.id(5)
				.addItem(2, 1) // b:1
				.addItem(5, 3) // e:3
				.add();

		// Transaction 6: {a:2, c:3, f:3}
		builder.transactions()
				.id(6)
				.addItem(1, 2) // a:2
				.addItem(3, 3) // c:3
				.addItem(6, 3) // f:3
				.add();

		// Transaction 7: {a:4, c:4, e:1, f:2}
		builder.transactions()
				.id(7)
				.addItem(1, 4) // a:4
				.addItem(3, 4) // c:4
				.addItem(5, 1) // e:1
				.addItem(6, 2) // f:2
				.add();

		return builder.build();
	}

	/**
	 * Print mining results in a formatted table
	 */
	private static void printResults(LmhaupMiner miner, UtilityDatabase db) {
		if (miner.haupItemsets.isEmpty()) {
			System.out.println("Không tìm thấy pattern nào thỏa mãn ngưỡng minutil.");
		} else {
			System.out.println(miner.haupItemsets.size() + " HAUP");
			System.out.println("┌─────────────┬──────────────────┐");
			System.out.println("│   Pattern   │ Average Utility  │");
			System.out.println("├─────────────┼──────────────────┤");

			for (int k = 0; k < miner.haupItemsets.size(); k++) {
				int[] itemset = miner.haupItemsets.get(k);
				double au = miner.haupAU.get(k);
				String patternStr = db.itemsetToString(itemset);
				System.out.printf("│ %-11s │ %16.4f │%n", patternStr, au);
			}

			System.out.println("└─────────────┴──────────────────┘");
		}

		System.out.println("\n" + "═".repeat(60));
	}
}