package edu.neumont.kinsey.candystore.view;

import lib.ConsoleIO;

public class UserInteraction {

	public void printInventory(String inventory) {
		ConsoleIO.displayMessage("Your inventory has:\n" + inventory);
	}

	public String getName() {
		return ConsoleIO.promptForInput("What is your name?", false);
	}

	public int getCandySelection(String[] candy) {
		return ConsoleIO.promptForMenuSelection(candy, false);
	}

	public int getQuantity() {
		return ConsoleIO.promptForInt("How many pieces would you like? (up to 50 pieces)", 1, 50);
	}

	public boolean askIfDoneSelecting() {
		return ConsoleIO.promptForBool("Would you like to put any more candy items in your inventory? (yes/no)", "no",
				"yes");
	}

	public double purchaseCandy(int totalCandy, double totalMoney) {
		if (totalCandy * .5f <= totalMoney) {
			ConsoleIO.displayMessage("You have purchased $" + (totalCandy * .5f) + " worth of candy.");
			totalMoney -= totalCandy * .5f;
		} else {
			ConsoleIO.displayMessage("You don't have enough money to make this purchase.");
		}
		return totalMoney;
	}

	public boolean askIfDonePurchasing() {
		return ConsoleIO.promptForBool("Would you like to purchase more candy? (yes/no)", "no", "yes");
	}

	public void printCustomerBalance(double money) {
		ConsoleIO.displayMessage("You have $" + money + " left to spend.");
	}

	public void printTransactionHistory(String transactionHistory) {
		ConsoleIO.displayMessage("Transaction History:\n" + transactionHistory);
	}

	public void tellStoreOutOfStock(int stock) {
		ConsoleIO.displayMessage("The store only has " + stock
				+ " pieces of candy left in stock, please ask a manager to restock the store.");
	}

	public int askForOptions() {
		return ConsoleIO.promptForMenuSelection(
				new String[] { "Buy more candy", "Ask manager to stock store", "Leave store" }, false);
	}

	public void tellStoreOverStock() {
		ConsoleIO.displayMessage("The store is at maximum capacity of 500 pieces of candy.");
	}

	public void tellStoreStocked() {
		ConsoleIO.displayMessage("The store was stocked with 100 pieces of candy.");
	}
}
