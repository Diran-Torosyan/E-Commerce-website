package com.example;

import javax.swing.*;
import java.awt.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides the checkout page for the e-commerce application.
 * Allows the user to review their cart, enter payment information, and complete the purchase.
 */
public class CheckoutPage extends JFrame {

    private Cart cart;
    private User user;

    /**
     * Constructor for CheckoutPage.
     * 
     * @param cart The Cart object containing the items to be purchased.
     * @param user The User object containing the user's information.
     */
    public CheckoutPage(Cart cart, User user) {
        this.cart = cart;
        this.user = user;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Checkout Page");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the panel that will show the user's information and cart items
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

        // Display user's information
        JLabel firstNameLabel = new JLabel("First Name: " + user.getFirstName());
        JLabel lastNameLabel = new JLabel("Last Name: " + user.getLastName());
        JLabel addressLabel = new JLabel("Address: " + user.getAddress());
        JLabel cityLabel = new JLabel("City: " + user.getCity());
        JLabel stateLabel = new JLabel("State: " + user.getState());
        JLabel zipLabel = new JLabel("Zip Code: " + user.getZipcode());

        cartPanel.add(firstNameLabel);
        cartPanel.add(lastNameLabel);
        cartPanel.add(addressLabel);
        cartPanel.add(cityLabel);
        cartPanel.add(stateLabel);
        cartPanel.add(zipLabel);

        cartPanel.add(Box.createRigidArea(new Dimension(0, 20)));  // Add some spacing

        // Display cart items
        JLabel cartItemsLabel = new JLabel("Items in Your Cart:");
        cartItemsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartPanel.add(cartItemsLabel);

        final double total = calculateTotal();  // Make 'total' effectively final
        for (Cart.Item item : cart.getItems()) {
            cartPanel.add(new JLabel(item.getName() + " - $" + String.format("%.2f", item.getPrice())));
        }

        // Display the total
        JLabel totalLabel = new JLabel("Total: $" + String.format("%.2f", total));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartPanel.add(Box.createRigidArea(new Dimension(0, 20)));  // Add some spacing
        cartPanel.add(totalLabel);

        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        cartScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        cartScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(cartScrollPane, BorderLayout.CENTER);

        // Create the panel that will hold the payment information fields
        JPanel paymentPanel = new JPanel();
        paymentPanel.setPreferredSize(new Dimension(screenSize.width, 200));
        paymentPanel.setBackground(Color.WHITE);
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));

        barTextField cardNumBar = new barTextField("Enter Card Number");
        barTextField cardCVBar = new barTextField("Enter Card CVV");
        barTextField ExpirationBar = new barTextField("Enter Card Expiration Date");
        barTextField cardNameBar = new barTextField("Enter Card Holder Name First and Last");

        JButton EnterButton = new JButton("Enter");
        EnterButton.addActionListener(e -> {
            if (isValidCreditCard(cardNumBar.getText())) {
                storePurchaseDetails(user.getEmail(), total);  // Store purchase details
                sendEmailReceipt();  // Send the email receipt when the user completes the checkout
                JOptionPane.showMessageDialog(this, "Purchase Completed!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credit card number. Please enter a valid 16-digit card number.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
        });

        paymentPanel.add(cardNumBar);
        paymentPanel.add(cardCVBar);
        paymentPanel.add(cardNameBar);
        paymentPanel.add(ExpirationBar);
        paymentPanel.add(EnterButton);
        paymentPanel.add(backButton);

        add(paymentPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Validates if the credit card number is a valid 16-digit number.
     * 
     * @param cardNumber The card number to be validated.
     * @return true if the card number is valid, false otherwise.
     */
    private boolean isValidCreditCard(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    /**
     * Calculates the total amount of the cart items.
     * 
     * @return The total amount as a double.
     */
    private double calculateTotal() {
        double total = 0.0;
        for (Cart.Item item : cart.getItems()) {
            total += item.getPrice();
        }
        return total;
    }

    /**
     * Stores the purchase details, including the user's email, total amount, and items purchased, in a text file.
     * 
     * @param email The user's email address.
     * @param total The total amount of the purchase.
     */
    private void storePurchaseDetails(String email, double total) {
        String filePath = "src/main/resources/purchases.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("Purchase Time: " + currentTime + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Total: $" + String.format("%.2f", total) + "\n");
            writer.write("Items Purchased:\n");

            for (Cart.Item item : cart.getItems()) {
                writer.write("- " + item.getName() + " - $" + String.format("%.2f", item.getPrice()) + "\n");
            }

            writer.write("\n");  // Add a blank line for separation
            JOptionPane.showMessageDialog(this, "Purchase details stored successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to store purchase details: " + e.getMessage());
        }
    }

    /**
     * Sends an email receipt to the customer after purchase completion.
     */
    private void sendEmailReceipt() {
        String to = user.getEmail();  // Send the email to the user's email address
        String from = "generalclothingsupply@yahoo.com";
        String host = "sandbox.smtp.mailtrap.io";
        final String username = "3fd865f7e6364f";
        final String password = "ff57e41eb72590";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");  // Enable TLS

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Your Purchase Receipt");

            StringBuilder emailBody = new StringBuilder("Thank you for your purchase!\n\nItems in your cart:\n");
            for (Cart.Item item : cart.getItems()) {
                emailBody.append(item.getName()).append(" - $").append(String.format("%.2f", item.getPrice())).append("\n");
            }

            message.setText(emailBody.toString());

            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Email receipt sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to send email: " + mex.getMessage());
        }
    }
}
