package edu.neumont.kinsey.candystore.controller;

import java.util.ArrayList;
import java.util.Random;

import edu.neumont.kinsey.candystore.model.CandyItem;
import edu.neumont.kinsey.candystore.model.Customer;
import edu.neumont.kinsey.candystore.model.Store;
import edu.neumont.kinsey.candystore.view.UserInteraction;

public class TestDriver {
	private UserInteraction userInteraction = new UserInteraction();
	private Store store = new Store();
	private Customer customer = new Customer();
	private Random rng = new Random();

	public void run() {
		init();
		boolean leftStore = false;
		do {
			int option = userInteraction.askForOptions();
			if (option == 1) {
				userInteraction.printCustomerBalance(customer.getMoney());
				boolean doneSelecting = false;
				do {
					selectCandy();
					doneSelecting = userInteraction.askIfDoneSelecting();
				} while (!doneSelecting);
				purchaseCandy();
				customer.clearInventory();
			} else if (option == 2) {
				boolean stockOverflow = store.stockStore(100);
				if (stockOverflow) {
					userInteraction.tellStoreOverStock();
				} else {
					userInteraction.tellStoreStocked();
				}
			} else {
				leftStore = true;
			}
		} while (!leftStore);
		printTransactionHistory();
	}

	private void showInventory() {
		ArrayList<CandyItem> inventory = customer.getInventory();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < CandyItem.values().length; i++) {
		int count = 0;
		for (int x = 0; x < inventory.size(); x++) {
			if (CandyItem.values()[i] == inventory.get(x)) {
				count++;
			}
		}
		if (count > 0) {
			sb.append(count + " " + CandyItem.values()[i].toString() + " candy\n");
		}
	}
		userInteraction.printInventory(sb.toString());
	}

	private void init() {
		customer.setName(userInteraction.getName());
		customer.setMoney(rng.nextInt(100 - 20) + 21);
	}

	private void selectCandy() {
		CandyItem[] candyEnums = CandyItem.values();
		String[] candy = new String[candyEnums.length];

		for (int i = 0; i < candyEnums.length; i++) {
			candy[i] = candyEnums[i].toString();
		}
		int selection = userInteraction.getCandySelection(candy);
		int quantity = userInteraction.getQuantity();
		addCandyToInventory(selection, quantity);
	}

	private void purchaseCandy() {
		showInventory();
		if (store.getTotalCandy() >= customer.inventoryTotal()) {
			double money = customer.getMoney();
			customer.setMoney(userInteraction.purchaseCandy(customer.inventoryTotal(), customer.getMoney()));
			if (customer.getMoney() != money) {
				saveTransaction();
			}
		} else {
			userInteraction.tellStoreOutOfStock(store.getTotalCandy());
		}
	}

	private void addCandyToInventory(int selection, int quantity) {
		for (int i = 0; i < quantity; i++) {
			customer.addToInventory(CandyItem.values()[selection - 1]);
		}
	}

	private void saveTransaction() {
		StringBuilder sb = new StringBuilder(customer.getName() + " purchased $");
		sb.append(customer.inventoryTotal() * .5f).append(" worth of candy.");
		store.saveTransaction(sb.toString());
	}

	private void printTransactionHistory() {
		userInteraction.printTransactionHistory(store.getTransactionHistory());
	}
}
