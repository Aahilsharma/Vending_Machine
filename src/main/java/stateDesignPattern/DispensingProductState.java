package stateDesignPattern;

import constantEnum.Coin;
import constantEnum.Product;
import services.VendingMachineStateClass;

public class DispensingProductState implements VendingMachineState {

	@Override
	public void insertCoin(VendingMachineStateClass vendingMachine, Coin coin) {
		// TODO Auto-generated method stub
		System.out.println("Please wait, dispensing your product.");
	}

	@Override
	public void selectProduct(VendingMachineStateClass vendingMachine, Product product) {
		// TODO Auto-generated method stub
		System.out.println("Product is already being dispensed.");
	}

	@Override
	public void dispenseProduct(VendingMachineStateClass vendingMachine) {
		// TODO Auto-generated method stub
		Product product = vendingMachine.getSelectedProduct();
		if (product != null) {
			vendingMachine.reduceInventory(product);
			vendingMachine.reduceBalance(product.getPrice());

			System.out.println("Dispensed: " + product);

			if (vendingMachine.getBalance() > 0) {
				vendingMachine.refund();
			}

			vendingMachine.setState(new IdleState());
		}
	}

	@Override
	public void cancelRequest(VendingMachineStateClass vendingMachine) {
		// TODO Auto-generated method stub
		System.out.println("Cannot cancel while dispensing.");
	}

}
