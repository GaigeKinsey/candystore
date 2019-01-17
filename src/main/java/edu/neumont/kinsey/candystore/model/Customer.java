package edu.neumont.kinsey.candystore.model;

import java.util.ArrayList;

public class Customer {
	private String name = "";
	private double money = 0;
	private ArrayList<CandyItem> inventory = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;			
		}
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public ArrayList<CandyItem> getInventory() {
		return inventory;
	}

	public void addToInventory(CandyItem candy) {
		inventory.add(candy);
	}

	public int inventoryTotal() {
		return inventory.size();
	}

	public void clearInventory() {
		inventory = new ArrayList<>();
	}
}
