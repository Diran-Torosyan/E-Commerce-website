package com.example;

import javax.swing.*;
import java.awt.*;

/**
 * This class demonstrates the GUI design for the "product details" page with the product details and navigation buttons.
 * 
 * @author Jack Ray
 * @author Diran Torosyan
 */
public class ProductDetailsPage extends JFrame {
    /**
     * Constructs a new product page with instances of Product class and Cart class. It formats the page to show the product's 
     * details and image, and formats navigation buttons.
     * @param item Object of the Item class
     * @see Item
     * @param cart Object of the Cart class
     * @see Cart
     */
    public ProductDetailsPage(Item item, Cart cart) {
        setTitle("Product Details");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        setLayout(new BorderLayout(10, 10));

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ImageIcon productImage = new ImageIcon(getClass().getClassLoader().getResource(item.getImagePath()));
        Image scaledImage = productImage.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        productImage = new ImageIcon(scaledImage);

        if (productImage.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.err.println("Error: Unable to load image for product: " + item.getItemName() + " at path: " + item.getImagePath());
        }

        JLabel imageLabel = new JLabel(productImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imagePanel.add(imageLabel);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(item.getItemName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(nameLabel);

        JLabel priceLabel;
        if (item.getSale()) {
            priceLabel = new JLabel("Sale Price: $" + item.getSalePrice() + " (Original: $" + item.getPrice() + ")");
            priceLabel.setForeground(Color.RED); 
        } else {
            priceLabel = new JLabel("Price: $" + item.getPrice());
        }
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(priceLabel);

        JTextArea descriptionArea = new JTextArea(item.getDescription());
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setOpaque(false);
        descriptionArea.setEditable(false);
        descriptionArea.setFocusable(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 20)); 
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        descriptionArea.setMaximumSize(new Dimension(600, 100)); 
        detailsPanel.add(descriptionArea);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(e -> {
            cart.addItem(item.getItemName(), item.getSale() ? item.getSalePrice() : item.getPrice());
            JOptionPane.showMessageDialog(this, item.getItemName() + " added to cart.");
        });
        buttonPanel.add(addToCartButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> this.dispose());
        buttonPanel.add(backButton);

        add(imagePanel, BorderLayout.NORTH);
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
