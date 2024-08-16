package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates a product with its details, getters for the details, and functionality to categorize a product.
 * 
 * <p>The Cart class will take the item that is added to the cart and add it to the cart popup, as well as add it to the checkout cart because
 * the checkout cart has the exact items the cart does.</p>
 * 
 * @author Jack Ray
 */
public class Cart {
    private List<Item> items;

    /**
     * Creates an instance of the cart.
     */
    public Cart() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the cart by name and price.
     * 
     * @param name The name of the item.
     * @param price The price of the item.
     */
    public void addItem(String name, double price) {
        items.add(new Item(name, price));
    }

    /**
     * Returns a list of the items in the cart.
     * 
     * @return A list of Item objects representing the items in the cart.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Clears the cart.
     */
    public void clearCart() {
        items.clear();
    }

    /**
     * Represents an item in the cart with a name and price.
     */
    public static class Item {
        private String name;
        private double price;

        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }
}
