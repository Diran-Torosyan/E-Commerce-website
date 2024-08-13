package com.example;
/**
 * This class demonstartes a product with its details, getters for the details, and functionality to catagorize a product.
 * 
 * <p>The Product class contains a product's ID, name, description, price, image path, and catagory, and a getter for each.
 * It has the functionality catagorize an item between 'Men' or 'Women'
 * 
 * @author
 */
public class Product {
    /**
     * The product ID
     */
    private String id;
    /**
     * The product name
     */
    private String name;
    /**
     * Description of the product
     */
    private String description;
    /**
     * The product price
     */
    private double price;
    /**
     * Path to the product image
     */
    private String imagePath;
    /**
     * The product catagory
     */
    private String category; // New attribute for category

    /**
     * Constructs a new product with ID, name, description, price, image path, and catagory 
     * @param id ID of the product
     * @param name name of the product
     * @param description description of the product
     * @param price price of the product
     * @param imagePath path to the product image
     * @param category catagory of the product
     */
    public Product(String id, String name, String description, double price, String imagePath, String category) {
        this.id = id;
        // Remove quotes from the name and description at the time of object creation
        this.name = name.replace("\"", "");
        this.description = description.replace("\"", "");
        this.price = price;
        this.imagePath = imagePath;
        this.category = category; // Initialize category
    }
    /**
     * The product ID getter
     * @return The product ID
     */
    public String getId() {
        return id;
    }
    /**
     * The product name getter
     * @return The product name
     */
    public String getName() {
        return name;
    }
    /**
     * The product description getter
     * @return The product description
     */
    public String getDescription() {
        return description;
    }
    /**
     * The product price getter
     * @return The product price
     */
    public double getPrice() {
        return price;
    }
    /**
     * The product image-path getter
     * @return The product image-path
     */
    public String getImagePath() {
        return imagePath;
    }
    /**
     * The product catagory getter
     * @return The product catagory
     */
    public String getCategory() {
        return category; // Getter for category
    }
    /**
     * The product catagory checker--Men's
     * @return true if the product is catagorized as 'Men's' and false otherwise
     */
    public boolean meetsFilter1Criteria() {
        return this.category.equalsIgnoreCase("Men's"); // Filter for Men's category
    }
    /**
     * The product catagory checker--Women's
     * @return true if the product is catagorized as 'Women's' and false otherwise
     */
    public boolean meetsFilter2Criteria() {
        return this.category.equalsIgnoreCase("Women's"); // Filter for Women's category
    }
}
