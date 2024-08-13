package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Item {

    // Instance variables
    private String itemName, category, imagePath, description;
    private double price = 0.0, salePrice;
    private int itemId, smallStock, mediumStock, largeStock, xlargeStock, itemsInCart;
    private boolean sale = false, inStock = true;

    // Constructor
    public Item(String itemName, String description, double price, double salePrice, int smallStock, int mediumStock, int largeStock, int xlargeStock, boolean sale, boolean inStock, String imagePath, String category) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.salePrice = salePrice;
        this.smallStock = smallStock;
        this.mediumStock = mediumStock;
        this.largeStock = largeStock;
        this.xlargeStock = xlargeStock;
        this.sale = sale;
        this.inStock = inStock;
        this.imagePath = imagePath;
        this.category = category;
        this.itemsInCart = 0; 
    }

    public Item() {
        this.itemsInCart = 0;  
    }

    // Getters and Setters
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public double getSalePrice() { return salePrice; }

public int getSmallStock() { return smallStock; }
public int getMediumStock() { return mediumStock; }
public int getLargeStock() { return largeStock; }
public int getXlargeStock() { return xlargeStock; }
public boolean getSale() { return sale; }
public boolean getInStock() { return inStock; }
public String getImagePath() { return imagePath; }
public String getCategory() { return category; }
public String getDescription() { return description; }
public int getItemId() { return itemId; }
public int getItemsInCart() { return itemsInCart; }  // Getter for itemsInCart

public void setItemName(String itemName) { this.itemName = itemName; }
public void setPrice(double price) { this.price = price; }
public void setSalePrice(double salePrice) { this.salePrice = salePrice; }
public void setSmallStock(int smallStock) { this.smallStock = smallStock; }
public void setMediumStock(int mediumStock) { this.mediumStock = mediumStock; }
public void setLargeStock(int largeStock) { this.largeStock = largeStock; }
public void setXlargeStock(int xlargeStock) { this.xlargeStock = xlargeStock; }
public void setSale(boolean sale) { this.sale = sale; }
public void setInStock(boolean inStock) { this.inStock = inStock; }
public void setImagePath(String imagePath) { this.imagePath = imagePath; }
public void setCategory(String category) { this.category = category; }
public void setDescription(String description) { this.description = description; }
public void setItemsInCart(int itemsInCart) { this.itemsInCart = itemsInCart; }  // Setter for itemsInCart

// Method to read item details from file
public boolean readFile(int itemId) {
    String filePath = "E-Commerce-website/E-Commerce-website/mavenConversion/my-app/src/main/resources/itemstock.txt"; // Correct file path

    try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",", -1); 
            System.out.println("Parsed fields: " + Arrays.toString(fields)); 
            
            if (fields.length != 12) {
                System.out.println("Incorrect number of fields: " + fields.length);
                continue;
            }

            // Remove unnecessary quotes around each field
            for (int i = 0; i < fields.length; i++) {
                fields[i] = fields[i].replace("\"", "").trim();
            }
            
            if (Integer.parseInt(fields[0]) == itemId) {
                this.itemId = itemId;
                this.itemName = fields[1];
                this.description = fields[2];
                this.price = Double.parseDouble(fields[3]);
                this.imagePath = fields[4];
                this.category = fields[5];
                this.smallStock = Integer.parseInt(fields[6]);
                this.mediumStock = Integer.parseInt(fields[7]);
                this.largeStock = Integer.parseInt(fields[8]);
                this.xlargeStock = Integer.parseInt(fields[9]);
                this.sale = Boolean.parseBoolean(fields[10]);
                this.salePrice = Double.parseDouble(fields[11]);
                this.inStock = (smallStock > 0 || mediumStock > 0 || largeStock > 0 || xlargeStock > 0);
                System.out.println("Successfully parsed item: " + this.itemName);
                return true;
            }
        }
    } catch (IOException e) {
        System.out.println("File not found. Please ensure the file path is correct.");
    } catch (NumberFormatException e) {
        System.out.println("Error parsing numerical value: " + e.getMessage());
    }
    return false;
}

public void restock(int newSmallStock, int newMediumStock, int newLargeStock, int newXlargeStock) {
    this.smallStock = newSmallStock;
    this.mediumStock = newMediumStock;
    this.largeStock = newLargeStock;
    this.xlargeStock = newXlargeStock;
    this.inStock = (smallStock > 0 || mediumStock > 0 || largeStock > 0 || xlargeStock > 0);
}

public void applySale(double discountPercentage) {
    this.sale = true;
    this.salePrice = price - (price * discountPercentage / 100);
}

public int addItem(String size) {
    switch (size.toLowerCase()) {
        case "small":
            if (smallStock > 0) {
                smallStock--;
                itemsInCart++;
                System.out.println("Added small size to cart. Items in cart: " + itemsInCart);
            }
            break;
        case "medium":
            if (mediumStock > 0) {
                mediumStock--;
                itemsInCart++;
                System.out.println("Added medium size to cart. Items in cart: " + itemsInCart);
            }
            break;
        case "large":
            if (largeStock > 0) {
                largeStock--;
                itemsInCart++;
                System.out.println("Added large size to cart. Items in cart: " + itemsInCart);
            }
            break;
        case "xlarge":
            if (xlargeStock > 0) {
                xlargeStock--;
                itemsInCart++;
                System.out.println("Added extra-large size to cart. Items in cart: " + itemsInCart);
            }
            break;
        default:
            System.out.println("Invalid size.");
            break;
    }
    return itemsInCart;
}
}
