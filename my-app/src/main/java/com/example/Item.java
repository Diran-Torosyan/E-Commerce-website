package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides functionality for handling individual items.
 * This class represents an item with details like name, description, price, stock, and sale status.
 * It includes methods to read item data from a file and update stock information.
 * 
 * @author Brent Eusala
 */
public class Item {

    // Instance variables
    private String itemName, genderCategory, typeCategory, imagePath, description;
    private double price = 0.0, salePrice;
    private int itemId, smallStock, mediumStock, largeStock, xlargeStock;
    private boolean sale = false, inStock = true;

    // Constructor
    public Item(String itemName, String description, double price, double salePrice, int smallStock, int mediumStock, int largeStock, int xlargeStock, boolean sale, boolean inStock, String imagePath, String genderCategory, String typeCategory) {
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
        this.genderCategory = genderCategory;
        this.typeCategory = typeCategory;
    }

    public Item() {
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
    public String getGenderCategory() { return genderCategory; }
    public String getTypeCategory() { return typeCategory; }
    public String getDescription() { return description; }
    public int getItemId() { return itemId; }

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
    public void setGenderCategory(String genderCategory) { this.genderCategory = genderCategory; }
    public void setTypeCategory(String typeCategory) { this.typeCategory = typeCategory; }
    public void setDescription(String description) { this.description = description; }

    // Method to read item details from file
    public boolean readFile(int itemId) {
        String filePath = "src/main/resources/itemstock.txt"; // Correct file path

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);

                if (fields.length != 13) { // Update to reflect the structure with 13 fields
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
                    this.genderCategory = fields[5];
                    this.typeCategory = fields[6];
                    this.smallStock = Integer.parseInt(fields[7]);
                    this.mediumStock = Integer.parseInt(fields[8]);
                    this.largeStock = Integer.parseInt(fields[9]);
                    this.xlargeStock = Integer.parseInt(fields[10]);
                    this.sale = Boolean.parseBoolean(fields[11]);
                    this.salePrice = Double.parseDouble(fields[12]);
                    this.inStock = (smallStock > 0 || mediumStock > 0 || largeStock > 0 || xlargeStock > 0);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Applies a sale to a specific item
     */
    public void applySale(double discountPercentage) {
        this.sale = true;
        this.salePrice = price - (price * discountPercentage / 100);
    }

    /**
     * Updates the stock in the itemstock.txt file after checkout
     */
    public void updateStock() {
        File itemFile = new File("src/main/resources/itemstock.txt");
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(itemFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);

                if (fields.length == 13 && Integer.parseInt(fields[0]) == itemId) {
                    fields[7] = String.valueOf(smallStock);    // Update small stock
                    fields[8] = String.valueOf(mediumStock);   // Update medium stock
                    fields[9] = String.valueOf(largeStock);    // Update large stock
                    fields[10] = String.valueOf(xlargeStock);  // Update extra-large stock
                    line = String.join(",", fields);
                }
                fileContent.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(itemFile))) {
            for (String updatedLine : fileContent) {
                bw.write(updatedLine);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
