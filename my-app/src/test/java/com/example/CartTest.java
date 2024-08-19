package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CartTest class to demonstrate core functionality testing.
 * 
 * <p>This test class includes basic tests for Cart and User functionality,
 * using assertions to verify expected outcomes.</p>
 */
public class CartTest {

    @Test
    public void testCartFunctionality() {
        Cart cart = new Cart();

        cart.addItem("Example Item", 49.99);

        System.out.println("Expected Cart Size: 1, Actual Cart Size: " + cart.getItems().size());
        System.out.println("Expected Item Name: Example Item, Actual Item Name: " + cart.getItems().get(0).getName());
        System.out.println("Expected Item Price: 49.99, Actual Item Price: " + cart.getItems().get(0).getPrice());

        assertEquals(1, cart.getItems().size(), "Cart should contain one item.");
        assertEquals("Example Item", cart.getItems().get(0).getName(), "Item name should match.");
        assertEquals(49.99, cart.getItems().get(0).getPrice(), 0.01, "Item price should match.");

        System.out.println("Test passed: Cart functionality works as expected.");
    }


}
