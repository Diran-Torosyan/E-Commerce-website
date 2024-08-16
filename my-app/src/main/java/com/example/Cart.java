package com.example;
import java.util.ArrayList;
import java.util.List;
/**
 * This class demonstartes a product with its details, getters for the details, and functionality to catagorize a product.
 * 
 * <p>The cart class will take the item that is added to cart, and add it to the cart popup, as well as add it to the checkout cart because
 * the checkout cart has the exact items the cart does
 * 
 * @author Jack Ray
 */
public class Cart {
    private List<Item> items;
    /**
     * creates an instance of the cart
     */
    public Cart() {
        items = new ArrayList<>();
    }
    /**
     * adds items to the cart by name and price
     */
    public void addItem(String name, double price) {
        items.add(new Item(name, price));
    }
    /**
     * returns a list of the items
     */
    public List<Item> getItems() {
        return items;
    }
    /**
     * clears the cart
     */
    public void clearCart() {
        items.clear();
    }
    /**
     * takes in information about the item
     * @param name is the name
     * @param price is the price of the item
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
