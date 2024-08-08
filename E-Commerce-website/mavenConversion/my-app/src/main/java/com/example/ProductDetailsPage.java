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
        imagePanel.setLayout(new BorderLayout());
        JLabel imageLabel = new JLabel(new ImageIcon(product.getImagePath()));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

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

        JTextArea descriptionArea = new JTextArea(product.getDescription());
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setOpaque(false);
        descriptionArea.setEditable(false);
        descriptionArea.setFocusable(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
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
