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
 * 
 * <p>This class represents an item with details like name, description, price, stock, and sale status.
 * It includes methods to read item data from a file and update stock information.
 * 
 * @author Brent Eusala
 */
public class Item {

    /**
     * Instance variables
     */
    private String itemName, genderCategory, typeCategory, imagePath, description;
    private double price = 0.0, salePrice;
    private int itemId, smallStock, mediumStock, largeStock, xlargeStock;
    private boolean sale = false, inStock = true;

    /**
     * Constructs a new item object with the specified information
     * @param itemName the name to assign to this item
     * @param description the description to assign to this item
     * @param price the price to assign to this item
     * @param salePrice the price to assign to this item while on sale
     * @param smallStock the in-stock quantity to assign to this item in size small
     * @param mediumStock the in-stock quantity to assign to this item in size medium
     * @param largeStock the in-stock quantity to assign to this item in size large
     * @param xlargeStock the in-stock quantity to assign to this item in size xlarge
     * @param sale the true/false to assign to this item to indicate if it's on sale
     * @param inStock the true/false to assign to this item to indicate if it's in-stock
     * @param imagePath the image path to assign to this item
     * @param genderCategory the gender catagory to assign to this item
     * @param typeCategory the catagory type to assign to this item
     */
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

    /**
     * default constructor
     */
    public Item() {
    }

    /**
     * Getters
     * @return name
     */
    public String getItemName() { return itemName; }
    /**
     * @return price
     */
    public double getPrice() { return price; }
    /**
     * @return price on sale
     */
    public double getSalePrice() { return salePrice; }
    /**
     * @return number of small in-stock
     */
    public int getSmallStock() { return smallStock; }
    /**
     * @return number of medium in-stock
     */
    public int getMediumStock() { return mediumStock; }
    /**
     * @return number of large in-stock
     */
    public int getLargeStock() { return largeStock; }
    /**
     * @return number of xlarge in-stock
     */
    public int getXlargeStock() { return xlargeStock; }
    /**
     * @return on-sale status
     */
    public boolean getSale() { return sale; }
    /**
     * @return in-stock status
     */
    public boolean getInStock() { return inStock; }
    /**
     * @return path to item's image
     */
    public String getImagePath() { return imagePath; }
    /**
     * @return gender catagory of item
     */
    public String getGenderCategory() { return genderCategory; }
    /**
     * @return type catagory of item
     */
    public String getTypeCategory() { return typeCategory; }
    /**
     * @return description
     */
    public String getDescription() { return description; }
    /**
     * @return item ID number
     */
    public int getItemId() { return itemId; }

    /**
     * Setters
     * @param itemName name
     */
    public void setItemName(String itemName) { this.itemName = itemName; }
    /**
     * set
     * @param price price
     */
    public void setPrice(double price) { this.price = price; }
    /**
     * set
     * @param salePrice price on sale
     */
    public void setSalePrice(double salePrice) { this.salePrice = salePrice; }
    /**
     * set
     * @param smallStock in-stock quantity of samll
     */
    public void setSmallStock(int smallStock) { this.smallStock = smallStock; }
    /**
     * set
     * @param mediumStock in-stock quantity of medium
     */
    public void setMediumStock(int mediumStock) { this.mediumStock = mediumStock; }
    /**
     * set
     * @param largeStock in-stock quantity of large
     */
    public void setLargeStock(int largeStock) { this.largeStock = largeStock; }
    /**
     * set
     * @param xlargeStock in-stock quantity of xlarge
     */
    public void setXlargeStock(int xlargeStock) { this.xlargeStock = xlargeStock; }
    /**
     * set
     * @param sale on-sale status
     */
    public void setSale(boolean sale) { this.sale = sale; }
    /**
     * set
     * @param inStock in-stock status
     */
    public void setInStock(boolean inStock) { this.inStock = inStock; }
    /**
     * set
     * @param imagePath path to item's image
     */
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    /**
     * set
     * @param genderCategory item's gender catagory
     */
    public void setGenderCategory(String genderCategory) { this.genderCategory = genderCategory; }
    /**
     * set
     * @param typeCategory item's type catagory
     */
    public void setTypeCategory(String typeCategory) { this.typeCategory = typeCategory; }
    /**
     * set
     * @param description a description for item
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * reads item details from file given its ID number
     * @param itemId
     * @return false in default
     * @exception IOException if data file cannot be accessed
     * @exception NumberFormatException if ID number is invalid
     */
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
     * @param discountPercentage discount percentage for sale
     */
    public void applySale(double discountPercentage) {
        this.sale = true;
        this.salePrice = price - (price * discountPercentage / 100);
    }

    /**
     * Updates the stock in the itemstock.txt file after checkout
     * @exception IOException if item stock file cannot be accessed
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
