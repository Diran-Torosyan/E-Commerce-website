package com.example;

import javax.swing.*;
import java.awt.*;

public class ProductDetailsPage extends JFrame {
    public ProductDetailsPage(Product product, Cart cart) {
        setTitle("Product Details");
        
        // Set the frame to full-screen size
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        setLayout(new BorderLayout(10, 10));

        // Panel to hold the product image
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Create an ImageIcon using the product's image path
        ImageIcon productImage = new ImageIcon(getClass().getClassLoader().getResource(product.getImagePath()));
        
        // Resize the image to fit a larger size (e.g., 800x800)
        Image scaledImage = productImage.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        productImage = new ImageIcon(scaledImage);

        // Handle case where image might not be found
        if (productImage.getImageLoadStatus() == MediaTracker.ERRORED) {
            // Log error to the terminal
            System.err.println("Error: Unable to load image for product: " + product.getName() + " at path: " + product.getImagePath());
        }

        JLabel imageLabel = new JLabel(productImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imagePanel.add(imageLabel);

        // Panel to hold product details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(nameLabel);

        JLabel priceLabel = new JLabel("Price: $" + product.getPrice());
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(priceLabel);

        // Use a JTextArea for the description, properly centered with larger font
        JTextArea descriptionArea = new JTextArea(product.getDescription());
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setOpaque(false);
        descriptionArea.setEditable(false);
        descriptionArea.setFocusable(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 20)); // Larger font size for description
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the description
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Set the maximum size for the description so it doesn't stretch across the entire panel
        descriptionArea.setMaximumSize(new Dimension(600, 100)); // Adjust width and height as needed
        detailsPanel.add(descriptionArea);

        // Panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            // Add product to cart
            cart.addItem(product.getName(), product.getPrice());
            JOptionPane.showMessageDialog(this, product.getName() + " added to cart.");
        });
        buttonPanel.add(addToCartButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
        });
        buttonPanel.add(backButton);

        add(imagePanel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
