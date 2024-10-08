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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Provides the checkout page for the e-commerce application.
 * 
 * <p>Allows the user to review their cart, enter payment information, and complete the purchase.
 */
public class CheckoutPage extends JFrame {

    private Cart cart;
    private User user;

    public CheckoutPage() {

    }

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
        /**
         * Create the panel that will show the user's information and cart items
         */
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        /**
         * Display user's information
         */
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

        /**
         * Display cart items
         */
        JLabel cartItemsLabel = new JLabel("Items in Your Cart:");
        cartItemsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartPanel.add(cartItemsLabel);

        final double total = calculateTotal();  // Make 'total' effectively final
        for (Cart.Item item : cart.getItems()) {
            cartPanel.add(new JLabel(item.getName() + " - $" + String.format("%.2f", item.getPrice())));
        }

        /**
         * Display the total
         */
        JLabel totalLabel = new JLabel("Total: $" + String.format("%.2f", total));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartPanel.add(Box.createRigidArea(new Dimension(0, 20)));  // Add some spacing
        cartPanel.add(totalLabel);

        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        cartScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        cartScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(cartScrollPane, BorderLayout.CENTER);

        /**
         * Create the panel that will hold the payment information fields
         */
        JPanel paymentPanel = new JPanel();
        paymentPanel.setPreferredSize(new Dimension(screenSize.width, 200));
        paymentPanel.setBackground(Color.WHITE);
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));

        JTextField cardNumField = new JTextField("Enter Card Number");
        JTextField cardCVVField = new JTextField("Enter Card CVV");
        JTextField expirationField = new JTextField("Enter Card Expiration Date");
        JTextField cardNameField = new JTextField("Enter Card Holder Name");

        /**
         * Add FocusListeners to clear the placeholder text when the field is focused
         */
        addPlaceholderBehavior(cardNumField, "Enter Card Number");
        addPlaceholderBehavior(cardCVVField, "Enter Card CVV");
        addPlaceholderBehavior(expirationField, "Enter Card Expiration Date");
        addPlaceholderBehavior(cardNameField, "Enter Card Holder Name");

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> {
            if (isValidCreditCard(cardNumField.getText())) {
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

        paymentPanel.add(cardNumField);
        paymentPanel.add(cardCVVField);
        paymentPanel.add(cardNameField);
        paymentPanel.add(expirationField);
        paymentPanel.add(enterButton);
        paymentPanel.add(backButton);

        add(paymentPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Helper method to add focus behavior to clear placeholder text when the field is focused.
     * 
     * @param textField The text field to add the behavior to.
     * @param placeholder The placeholder text to display.
     */
    private void addPlaceholderBehavior(JTextField textField, String placeholder) {
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
        textField.setForeground(Color.GRAY); // Set initial placeholder color
    }

    /**
     * Validates if the credit card number is a valid 16-digit number.
     * 
     * @param cardNumber The card number to be validated.
     * @return true if the card number is valid, false otherwise.
     */
    protected boolean isValidCreditCard(String cardNumber) {
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
     * @exception IOException if purchase info data file cannot be accessed
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
     * 
     * @exception MessagingException prompts message to inform user if sending email receipt is failed
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
