package com.example;

public class Product {
    private String id;
    private String name;
    private String description;
    private double price;
    private String imagePath;
    private String category; // New attribute for category

    public Product(String id, String name, String description, double price, String imagePath, String category) {
        this.id = id;
        // Remove quotes from the name and description at the time of object creation
        this.name = name.replace("\"", "");
        this.description = description.replace("\"", "");
        this.price = price;
        this.imagePath = imagePath;
        this.category = category; // Initialize category
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCategory() {
        return category; // Getter for category
    }

    public boolean meetsFilter1Criteria() {
        return this.category.equalsIgnoreCase("Men's"); // Filter for Men's category
    }

    public boolean meetsFilter2Criteria() {
        return this.category.equalsIgnoreCase("Women's"); // Filter for Women's category
    }
}
