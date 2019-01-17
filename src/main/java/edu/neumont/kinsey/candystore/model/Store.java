package edu.neumont.kinsey.candystore.model;

import java.util.ArrayList;

public class Store {
	private ArrayList<String> transactionHistory = new ArrayList<>();
	private int totalCandy = 150;

	public int getTotalCandy() {
		return totalCandy;
	}

	public boolean stockStore(int extra) {
		boolean stockOverflow = false;
		totalCandy += extra;
		// If the candy in the store is below set values, then return a true boolean to
		// signify the state is different than intended
		if (totalCandy > 500) {
			stockOverflow = true;
			totalCandy = 500;
		}
		if (totalCandy < 0) {
			stockOverflow = true;
			totalCandy = 100;
		}
		return stockOverflow;
	}

	public void setTotalCandy(int totalCandy) {
		if (totalCandy >= 0) {
			this.totalCandy = totalCandy;			
		}
	}

	public void saveTransaction(String transaction) {
		transactionHistory.add(transaction);
	}

	public String getTransactionHistory() {
		StringBuilder sb = new StringBuilder();
		for (String transaction : transactionHistory) {
			sb.append(transaction).append("\n");
		}
		return sb.toString();
	}
}
