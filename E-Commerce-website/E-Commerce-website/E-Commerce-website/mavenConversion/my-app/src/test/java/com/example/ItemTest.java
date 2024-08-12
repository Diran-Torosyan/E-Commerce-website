package com.example;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ItemTest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
 
        // Create an Item object
        Item item = new Item();

        // Prompt user to enter an item ID to load
        System.out.print("Enter item ID to load: ");
        int itemId = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Read item details from file based on item ID
        if (item.readFile(itemId)) {
            // Display item details
            System.out.println("Item loaded successfully:");
            System.out.println("Item ID: " + item.getItemId());
            System.out.println("Name: " + item.getItemName());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Price: " + item.getPrice());
            System.out.println("Category: " + item.getCategory());
            System.out.println("Stock (Small): " + item.getSmallStock());
            System.out.println("Stock (Medium): " + item.getMediumStock());
            System.out.println("Stock (Large): " + item.getLargeStock());
            System.out.println("Stock (XLarge): " + item.getXlargeStock());
            System.out.println("On Sale: " + item.getSale());
            System.out.println("Sale Price: " + item.getSalePrice());
        } else {
            System.out.println("Item with ID " + itemId + " not found.");
        }

        // Apply sale to item
        System.out.print("Enter discount percentage to apply to item: ");
        double discount = sc.nextDouble();
        item.applySale(discount);
        System.out.println("Discount applied. New Sale Price: " + item.getSalePrice());

        // Test adding item to cart
        System.out.print("Enter size to add to cart (small, medium, large, xlarge): ");
        String size = sc.next();
        item.addItem(size);

        // Display updated stock and items in cart
        System.out.println("Updated Stock (Small): " + item.getSmallStock());
        System.out.println("Updated Stock (Medium): " + item.getMediumStock());
        System.out.println("Updated Stock (Large): " + item.getLargeStock());
        System.out.println("Updated Stock (XLarge): " + item.getXlargeStock());
        System.out.println("Items in Cart: " + item.getItemsInCart());


        // Display inventory information
        // item.inventory();


        // Simulate user/customer interaction
        // customer.customerInfo();
        // customer.customerLogin();
        // customer.loginCheck();

        // Simulate admin interaction
        // admin.admin();

        sc.close();
    }
}
