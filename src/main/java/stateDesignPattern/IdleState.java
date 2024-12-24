package stateDesignPattern;

import constantEnum.Coin;
import constantEnum.Product;
import services.VendingMachineStateClass;

public class IdleState implements VendingMachineState{

	 @Override
	    public void insertCoin(VendingMachineStateClass vendingMachine, Coin coin) {
	        vendingMachine.addBalance(coin.getValue());
	        System.out.println("Inserted " + coin);
	        vendingMachine.setState(new HasMoneyState());
	    }

	    @Override
	    public void selectProduct(VendingMachineStateClass vendingMachine, Product product) {
	        System.out.println("Insert coins first.");
	    }

	    @Override
	    public void dispenseProduct(VendingMachineStateClass vendingMachine) {
	        System.out.println("No money inserted.");
	    }

	    @Override
	    public void cancelRequest(VendingMachineStateClass vendingMachine) {
	        System.out.println("Nothing to cancel.");
	    }

}
