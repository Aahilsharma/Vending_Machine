package stateDesignPattern;

import constantEnum.Coin;
import constantEnum.Product;
import services.VendingMachineStateClass;

public class HasMoneyState implements VendingMachineState{

	@Override
	public void insertCoin(VendingMachineStateClass vendingMachine, Coin coin) {
		// TODO Auto-generated method stub
        vendingMachine.addBalance(coin.getValue());
        System.out.println("Inserted " + coin);
	}

	@Override
	public void selectProduct(VendingMachineStateClass vendingMachine, Product product) {
		// TODO Auto-generated method stub
		if(!vendingMachine.isProductAvailable(product)) {
            System.out.println("Product out of stock.");
            vendingMachine.setState(new OutOfStockState());
            return;
		}
		
		if(vendingMachine.getBalance() < product.getPrice()) {
            System.out.println("Insufficient balance. Insert more coins.");
            return;
		}
		vendingMachine.setSelectedProduct(product);
		vendingMachine.setState(new DispensingProductState());
	}

	@Override
	public void dispenseProduct(VendingMachineStateClass vendingMachine) {
		System.out.println("Select a product first.");
		
	}

	@Override
	public void cancelRequest(VendingMachineStateClass vendingMachine) {
		// TODO Auto-generated method stub
        vendingMachine.refund();
        vendingMachine.setState(new IdleState());
	}

}
