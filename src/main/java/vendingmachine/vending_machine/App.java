package vendingmachine.vending_machine;

import java.util.HashMap;
import java.util.Map;

import constantEnum.Coin;
import constantEnum.Product;
import services.VendingMachineStateClass;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
//        VendingMachine vendingMachine = new VendingMachine();
//
//        try {
//            vendingMachine.insertCoin(Coin.QUARTER);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.selectProduct(Product.COKE);
//            vendingMachine.dispenseProduct();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("Resetting the machine...");
//        vendingMachine.reset();
    	
    	
        // Create the vending machine
        VendingMachineStateClass vendingMachine = new VendingMachineStateClass();

        // Initialize inventory with limited stock
        Map<Product, Integer> initialProducts = new HashMap<>();
        initialProducts.put(Product.COKE, 2); // Only 2 Cokes available
        initialProducts.put(Product.PEPSI, 1); // Only 1 Pepsi available
        initialProducts.put(Product.SODA, 0); // Soda is out of stock

        vendingMachine.initializeInventory(initialProducts);

        try {
            // Insert coins and buy Coke
            vendingMachine.insertCoin(Coin.QUARTER);
            vendingMachine.selectProduct(Product.COKE);
            vendingMachine.dispenseProduct();

            // Insert coins and buy another Coke
            vendingMachine.insertCoin(Coin.QUARTER);
            vendingMachine.selectProduct(Product.COKE);
            vendingMachine.dispenseProduct();

            // Try to buy another Coke (Out of Stock)
            vendingMachine.insertCoin(Coin.QUARTER);
            vendingMachine.selectProduct(Product.COKE); // This should throw an exception
            vendingMachine.dispenseProduct();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            // Try to buy Soda (always Out of Stock)
            vendingMachine.insertCoin(Coin.QUARTER);
            vendingMachine.selectProduct(Product.SODA); // This should throw an exception
            vendingMachine.dispenseProduct();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
