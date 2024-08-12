package com.example;

import javax.swing.*;
import java.awt.*;

/**
 * This class demonstrates the GUI design for the "product details" page with the product details and navigation buttons.
 * 
 * <p>The ProductDetailsPage class extends JFrame class in the Java Swing library to access its components. it formats an
 * interactive product page with the product's image, name, description, and price, and navigation buttons to go back and add 
 * to cart.
 * 
 * @author
 */
public class ProductDetailsPage extends JFrame {
    /**
     * Constructs a new product page with instances of Product class and Cart class. It formats the page to show the product's 
     * details and image, and formats navigation buttons.
     * @param product Object of the Product class
     * @see Product
     * @param cart Object of the Cart class
     * @see Cart
     */
    public ProductDetailsPage(Product product, Cart cart) {
        setTitle("Product Details");
        /**
         * Set the frame to full-screen size
         */ 
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        setLayout(new BorderLayout(10, 10));
        /**
         * Panel to hold the product image
         */
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        /**
         * Create an ImageIcon using the product's image path
         */
        ImageIcon productImage = new ImageIcon(getClass().getClassLoader().getResource(product.getImagePath()));
        /**
         * Resize the image to fit a larger size (e.g., 800x800)
         */
        Image scaledImage = productImage.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        productImage = new ImageIcon(scaledImage);
        /**
         * Handle case where image might not be found
         */
        if (productImage.getImageLoadStatus() == MediaTracker.ERRORED) {
            /**
             * Log error to the terminal
             */
            System.err.println("Error: Unable to load image for product: " + product.getName() + " at path: " + product.getImagePath());
        }
        /**
         * Panel to hold product image
         */
        JLabel imageLabel = new JLabel(productImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imagePanel.add(imageLabel);
        /**
         * Panel to hold product details
         */
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        /**
         * Panel to hold product name
         */
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(nameLabel);
        /**
         * Panel to hold product price
         */
        JLabel priceLabel = new JLabel("Price: $" + product.getPrice());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(priceLabel);
        /**
         * Use a JTextArea for the description, properly centered with larger font
         */
        JTextArea descriptionArea = new JTextArea(product.getDescription());
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setOpaque(false);
        descriptionArea.setEditable(false);
        descriptionArea.setFocusable(false);
        /**
         * Larger font size for description
         */
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 20));
        /**
         * Center the description
         */
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        /**
         * Set the maximum size for the description so it doesn't stretch across the entire panel
         * 
         * Adjust width and height as needed
         */
        descriptionArea.setMaximumSize(new Dimension(600, 100));
        detailsPanel.add(descriptionArea);
        /**
         * Panel to hold the buttons
         */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        /**
         * button to add to cart
         */
        JButton addToCartButton = new JButton("Add to Cart");
        /**
         * Event Handler to add item to cart when button is clicked
         */
        addToCartButton.addActionListener(e -> {
            // Add product to cart
            cart.addItem(product.getName(), product.getPrice());
            JOptionPane.showMessageDialog(this, product.getName() + " added to cart.");
        });
        /**
         * add 'add to cart' button to panel
         */
        buttonPanel.add(addToCartButton);
        /**
         * button to navigate back to the previous page
         */
        JButton backButton = new JButton("Back");
        /**
         * Event Handler to close this product page when button is clicked
         */
        backButton.addActionListener(e -> {
            this.dispose();
        });
        /**
         * add 'back' button to panel
         */
        buttonPanel.add(backButton);

        /**
         * design the page with product image and description and navigation panel
         */
        add(imagePanel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        /**
         * close product page with app
         */
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
