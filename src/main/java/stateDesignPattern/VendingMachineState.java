package stateDesignPattern;

import constantEnum.Coin;
import constantEnum.Product;
import services.VendingMachineStateClass;

public interface VendingMachineState {

    void insertCoin(VendingMachineStateClass vendingMachine, Coin coin);
    void selectProduct(VendingMachineStateClass vendingMachine, Product product);
    void dispenseProduct(VendingMachineStateClass vendingMachine);
    void cancelRequest(VendingMachineStateClass vendingMachine);
}
