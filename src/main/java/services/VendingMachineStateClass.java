package services;

import java.util.HashMap;
import java.util.Map;

import constantEnum.Coin;
import constantEnum.Product;
import stateDesignPattern.IdleState;
import stateDesignPattern.VendingMachineState;

public class VendingMachineStateClass {

    private VendingMachineState currentState;
    private final Map<Product, Integer> productInventory = new HashMap<>();
    private int balance = 0;
    private Product selectedProduct;
    
    public VendingMachineStateClass() {
        this.currentState = new IdleState();
        // No inventory is initialized by default
    }

    public void initializeInventory(Map<Product, Integer> products) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productInventory.put(entry.getKey(), entry.getValue());
        }
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoin(this, coin);
    }

    public void selectProduct(Product product) {
        currentState.selectProduct(this, product);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct(this);
    }

    public void cancelRequest() {
        currentState.cancelRequest(this);
    }

    public void addBalance(int amount) {
        this.balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public void reduceBalance(int amount) {
        this.balance -= amount;
    }

    public boolean isProductAvailable(Product product) {
        return productInventory.getOrDefault(product, 0) > 0;
    }

    public void reduceInventory(Product product) {
        if (isProductAvailable(product)) {
            productInventory.put(product, productInventory.get(product) - 1);
        }
    }

    public void refund() {
        System.out.println("Refunded balance: " + balance);
        balance = 0;
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }
    
    
}
