package services;

import java.util.ArrayList;
import java.util.List;

import constantEnum.Coin;
import constantEnum.Product;

public class VendingMachine {

	private final Inventary<Coin> coinInventory = new Inventary<>();
	private final Inventary<Product> productInventory = new Inventary<>();
	private int currentBalance = 0;
	private Product selectedProduct;

	public VendingMachine() {
		initialize();
	}

	private void initialize() {
		// Add default inventory
		for (Coin coin : Coin.values()) {
			coinInventory.add(coin, 10); // Default 10 coins of each type
		}
		for (Product product : Product.values()) {
			productInventory.add(product, 10); // Default 10 of each product
		}
	}

	// Insert Coin
	public void insertCoin(Coin coin) {
		currentBalance += coin.getValue();
		coinInventory.add(coin, 1);
	}

	// Select Product
	public void selectProduct(Product product) {
		if (productInventory.hasItem(product)) {
			if (currentBalance >= product.getPrice()) {
				selectedProduct = product;
			} else {
				throw new IllegalStateException("Insufficient Balance");
			}
		} else {
			throw new IllegalStateException("product is Out of Stock");
		}
	}

	// Dispense Product
	public void dispenseProduct() {
		if (selectedProduct != null) {
			productInventory.deduct(selectedProduct);
			int change = currentBalance - selectedProduct.getPrice();
			List<Coin> changeCoins = getChange(change);
			dispenseChange(changeCoins);
			currentBalance = 0;
			selectedProduct = null;
		} else {
			throw new IllegalStateException("No product selected!");
		}
	}

	// Cancel the request and get the amount back
	public List<Coin> cancelRequest() {
		List<Coin> refund = getChange(currentBalance);
		currentBalance = 0;
		selectedProduct = null;
		return refund;
	}

	private void dispenseChange(List<Coin> change) {
		System.out.println("Dispensed change: " + change);
	}

	// Helper Method for get Change
	private List<Coin> getChange(int amount) {
		List<Coin> change = new ArrayList<>();
		while (amount > 0) {
			if (amount >= Coin.QUARTER.getValue() && coinInventory.hasItem(Coin.QUARTER)) {
				change.add(Coin.QUARTER);
				coinInventory.deduct(Coin.QUARTER);
				amount -= Coin.QUARTER.getValue();
			} else if (amount >= Coin.DIME.getValue() && coinInventory.hasItem(Coin.DIME)) {
				change.add(Coin.DIME);
				coinInventory.deduct(Coin.DIME);
				amount -= Coin.DIME.getValue();
			} else if (amount >= Coin.NICKEL.getValue() && coinInventory.hasItem(Coin.NICKEL)) {
				change.add(Coin.NICKEL);
				coinInventory.deduct(Coin.NICKEL);
				amount -= Coin.NICKEL.getValue();
			} else if (amount >= Coin.PENNY.getValue() && coinInventory.hasItem(Coin.PENNY)) {
				change.add(Coin.PENNY);
				coinInventory.deduct(Coin.PENNY);
				amount -= Coin.PENNY.getValue();
			} else {
				throw new IllegalStateException("Not enough change available!");
			}
		}
		return change;
	}

	public void reset() {
		coinInventory.clear();
		productInventory.clear();
		initialize();
		currentBalance = 0;
		selectedProduct = null;
	}
}
