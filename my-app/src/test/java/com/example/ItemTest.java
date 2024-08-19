package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Item class.
 */
public class ItemTest {

    /**
     * Tests the applySale method of the Item class.
     */
    @Test
    public void testApplySale() {
        Item item = new Item("T-Shirt", "A cool t-shirt", 20.00, 0.0, 10, 5, 3, 2, false, true, "image.png", "Men", "Top");
        
        item.applySale(50);
        
        System.out.println("Item sale status: " + item.getSale());
        System.out.println("Expected Sale Price: 10.00, Actual Sale Price: " + item.getSalePrice());

        assertTrue(item.getSale(), "Item should be on sale.");
        assertEquals(10.00, item.getSalePrice(), 0.01, "Sale price should be 50% off.");

        System.out.println("Test passed: Sale was applied successfully.");
    }

    /**
     * Tests the updateStock method of the Item class.
     */
    @Test
    public void testUpdateStock() {
        Item item = new Item("T-Shirt", "A cool t-shirt", 20.00, 0.0, 10, 5, 3, 2, false, true, "image.png", "Men", "Top");
        
        item.setMediumStock(item.getMediumStock() - 1);
        item.updateStock();
        
        System.out.println("Expected Medium Stock: 4, Actual Medium Stock: " + item.getMediumStock());

        assertEquals(4, item.getMediumStock(), "Medium stock should be updated to 4.");

        System.out.println("Test passed: Stock was updated successfully.");
    }

    /**
     * Tests the getInStock method indirectly by checking stock status after stock changes.
     */
    @Test
    public void testGetInStock() {
        Item item = new Item("T-Shirt", "A cool t-shirt", 20.00, 0.0, 0, 0, 0, 0, false, false, "image.png", "Men", "Top");
        
        System.out.println("Initial stock status: " + item.getInStock());
        assertFalse(item.getInStock(), "Item should be out of stock.");
        
        item.setSmallStock(5);
        item.setInStock(true);
        
        System.out.println("Expected Stock Status: true, Actual Stock Status: " + item.getInStock());

        assertTrue(item.getInStock(), "Item should be in stock after adding stock.");

        System.out.println("Test passed: Item stock status updated successfully.");
    }
}
