package vendingmachine.vending_machine;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import constantEnum.Coin;
import constantEnum.Product;
import services.VendingMachineStateClass;


public class VendingMachineTest {
    private VendingMachineStateClass vendingMachine;

    @Before
    public void setUp() {
        vendingMachine = new VendingMachineStateClass();

        // Initialize inventory with limited stock
        Map<Product, Integer> initialProducts = new HashMap<>();
        initialProducts.put(Product.COKE, 2);
        initialProducts.put(Product.PEPSI, 1);
        initialProducts.put(Product.SODA, 0);

        vendingMachine.initializeInventory(initialProducts);
    }

    @Test
    public void testInsertCoin() {
        vendingMachine.insertCoin(Coin.QUARTER);
        assertEquals(25, vendingMachine.getBalance(), "Balance should be updated after inserting a coin.");
    }

    @Test
    public void testSelectAndDispenseProduct() {
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.selectProduct(Product.COKE);
        vendingMachine.dispenseProduct();

        assertEquals(1, vendingMachine.isProductAvailable(Product.COKE) ? 1 : 0, "Coke inventory should decrease by 1.");
        assertEquals(0, vendingMachine.getBalance(), "Balance should reset to 0 after dispensing the product.");
    }

    @Test
    public void testOutOfStockException() {
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.selectProduct(Product.SODA);

        Exception exception = assertThrows(RuntimeException.class, vendingMachine::dispenseProduct);
        assertEquals("Product out of stock!", exception.getMessage(), "Should throw 'Product out of stock!' exception.");
    }

    @Test
    public void testRefund() {
        vendingMachine.insertCoin(Coin.QUARTER);
        vendingMachine.insertCoin(Coin.DIME);

        vendingMachine.cancelRequest();
        assertEquals(0, vendingMachine.getBalance(), "Balance should reset to 0 after refund.");
    }

    @Test
   public void testInsufficientBalance() {
        vendingMachine.insertCoin(Coin.DIME); // Insert 10 cents (insufficient for Coke)

        Exception exception = assertThrows(RuntimeException.class, () -> vendingMachine.selectProduct(Product.COKE));
        assertEquals("Insufficient balance. Insert more coins.", exception.getMessage(), "Should throw insufficient balance exception.");
    }
}
