package stateDesignPattern;

import constantEnum.Coin;
import constantEnum.Product;
import services.VendingMachineStateClass;

public class OutOfStockState implements VendingMachineState {

	@Override
	public void insertCoin(VendingMachineStateClass vendingMachine, Coin coin) {
		// TODO Auto-generated method stub
        System.out.println("Product out of stock. Cannot accept coins.");
        vendingMachine.setState(new IdleState());
	}

	@Override
	public void selectProduct(VendingMachineStateClass vendingMachine, Product product) {
		// TODO Auto-generated method stub
		System.out.println("Product is out of stock.");
		vendingMachine.setState(new IdleState());
	}

	@Override
	public void dispenseProduct(VendingMachineStateClass vendingMachine) {
		// TODO Auto-generated method stub
		System.out.println("Cannot dispense. Product is out of stock.");
		vendingMachine.setState(new IdleState());
	}

	@Override
	public void cancelRequest(VendingMachineStateClass vendingMachine) {
		// TODO Auto-generated method stub
		System.out.println("Nothing to cancel.");
		vendingMachine.setState(new IdleState());
	}

}
