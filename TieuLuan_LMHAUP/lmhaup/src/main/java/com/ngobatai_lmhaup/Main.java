package com.ngobatai_lmhaup;

import com.ngobatai_lmhaup.service.MiningOrchestrator;

import java.io.IOException;

public class Main {
	// Dataset paths
	private static final String PROFIT_FILE = "lmhaup/src/main/java/com/ngobatai_lmhaup/datasets/BangLoiNhuan.txt";
	private static final String TRANSACTION_FILE = "lmhaup/src/main/java/com/ngobatai_lmhaup/datasets/BangTransaction.txt";
	private static final double DELTA = 0.17;

	public static void main(String[] args) {
		try {
			MiningOrchestrator orchestrator = new MiningOrchestrator(
					PROFIT_FILE,
					TRANSACTION_FILE,
					DELTA);

			orchestrator.execute();

		} catch (IOException e) {
			System.err.println("Error reading dataset files: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error during mining: " + e.getMessage());
			e.printStackTrace();
		}
	}
}