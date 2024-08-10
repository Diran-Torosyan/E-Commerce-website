package com.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CreatePages extends JFrame {
    private JTextField searchBar;
    private JButton searchButton;
    private JPanel items;
    private JScrollPane scrollPane; 
    private Cart cart;
    private Map<String, Product> products;

    private JCheckBox menFilter; 
    private JCheckBox womenFilter; 

    public CreatePages() {
        cart = new Cart();
        initializeProducts();
        homeDisplay();
        itemDisplay(products);
    }

    // Initialize the products
    private void initializeProducts() {
        products = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E-Commerce-website/E-Commerce-website/mavenConversion/my-app/src/main/resources/itemstock.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                //HOW THIS WORKS.ONLY DIFFERENCE IS WE USE SPACES AND NOT COMMAS:https://stackabuse.com/regex-splitting-by-character-unless-in-quotes/
                String[] productDetails = line.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", 6);
                if (productDetails.length == 6) {
                    String id = productDetails[0];
                    String name = productDetails[1];
                    String description = productDetails[2].replace("\"", "");
                    double price = Double.parseDouble(productDetails[3]);
                    String imagePath = productDetails[4];
                    String category = productDetails[5];
                    products.put(imagePath, new Product(id, name, description, price, imagePath, category));
                } else {
                    System.out.println("Skipping improperly formatted line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing price: " + e.getMessage());
        }
    }

    // Creates everything that will be used in the homepage as well as names the homepage
    void homeDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("E-Commerce Website");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Bar at top of screen that holds search bar, cart, checkout, and logout button
        JPanel bar = new JPanel();
        bar.setPreferredSize(new Dimension(screenSize.width, 50));
        bar.setBackground(Color.WHITE);
        bar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel home = new JLabel("Home");
        home.setForeground(Color.GRAY);
        home.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        home.setFocusable(true);

        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(300, 30));
        searchBar.setFocusable(true);

        searchButton = new JButton("search");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addActionListener(e -> search());

        menFilter = new JCheckBox("Men's");
        womenFilter = new JCheckBox("Women's");
        menFilter.addActionListener(e -> applyFilters());
        womenFilter.addActionListener(e -> applyFilters());

        JLabel space = new JLabel();
        space.setPreferredSize(new Dimension(300, 30));
        space.setFocusable(true);

        JButton cartButton = new JButton("Cart");
        cartButton.setForeground(Color.GRAY);
        cartButton.setPreferredSize(new Dimension(80, 30));
        cartButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        cartButton.setFocusable(true);
        cartButton.addActionListener(e -> viewCart());

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setForeground(Color.GRAY);
        checkoutButton.setPreferredSize(new Dimension(100, 30));
        checkoutButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        checkoutButton.setFocusable(true);
        checkoutButton.addActionListener(e -> checkout());

        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.GRAY);
        loginButton.setPreferredSize(new Dimension(80, 30));
        loginButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        loginButton.setFocusable(true);
        loginButton.addActionListener(e -> login());

        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.GRAY);
        logoutButton.setPreferredSize(new Dimension(80, 30));
        logoutButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        logoutButton.setFocusable(true);
        logoutButton.addActionListener(e -> logout());

        add(bar);
        bar.add(home);
        bar.add(searchBar);
        bar.add(searchButton);
        bar.add(menFilter);
        bar.add(womenFilter);
        bar.add(space);
        bar.add(cartButton);
        bar.add(checkoutButton);
        bar.add(loginButton);
        bar.add(logoutButton);

        add(bar, BorderLayout.NORTH);
        items = new JPanel();
        items.setLayout(new GridLayout(0, 4, 10, 10));

        scrollPane = new JScrollPane(items);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);

        setResizable(false);
        setVisible(true);
    }

    // Display the items on the homepage
    private void itemDisplay(Map<String, Product> productsToDisplay) {
        items.removeAll();

        for (Product product : productsToDisplay.values()) {
            try {
                System.out.println("Attempting to load image: " + product.getImagePath());
                BufferedImage img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(product.getImagePath()));

                if (img == null) {
                    System.out.println("Failed to load image: " + product.getImagePath());
                    continue;
                }

                JPanel productPanel = new JPanel();
                productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS)); 

                JLabel label = new JLabel();
                ImageIcon icon = new ImageIcon(img.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                label.setIcon(icon);
                label.setAlignmentX(Component.CENTER_ALIGNMENT); 

                // Make image clickable
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        new ProductDetailsPage(product, cart);
                    }
                });

                productPanel.add(label);

                JButton addButton = new JButton("Add to Cart");
                addButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
                addButton.addActionListener(e -> cart.addItem(product.getName(), product.getPrice()));

                productPanel.add(addButton);

                items.add(productPanel);

                System.out.println("Successfully loaded image: " + product.getImagePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        items.revalidate();
        items.repaint();
        System.out.println("Total images: " + productsToDisplay.size());
    }

    private void search() {
        Map<String, Product> searchedProducts = searchProducts();
        filterProducts(searchedProducts);
    }

    private Map<String, Product> searchProducts() {
        String query = searchBar.getText().toLowerCase();
        Map<String, Product> searchedProducts = new HashMap<>();

        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(query)) {
                searchedProducts.put(product.getImagePath(), product);
            }
        }

        return searchedProducts;
    }

    private void filterProducts(Map<String, Product> productsToFilter) {
        if (menFilter.isSelected()) {
            productsToFilter.values().removeIf(product -> !product.getCategory().equalsIgnoreCase("Men's"));
        }

        if (womenFilter.isSelected()) {
            productsToFilter.values().removeIf(product -> !product.getCategory().equalsIgnoreCase("Women's"));
        }

        itemDisplay(productsToFilter);
    }

    private void applyFilters() {
        Map<String, Product> searchedProducts = searchProducts();
        filterProducts(searchedProducts);
    }

    // View cart items
    private void viewCart() {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

        for (Cart.Item item : cart.getItems()) {
            cartPanel.add(new JLabel(item.getName() + " - $" + String.format("%.2f", item.getPrice())));
        }

        JOptionPane.showMessageDialog(this, cartPanel, "Your Cart", JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkout() {
        JOptionPane.showMessageDialog(this, "Checkout functionality started. Please wait");
        sendEmailReceipt();
    }

    private void sendEmailReceipt() {
        String to = "jackray3111@gmail.com";
        String from = "generalclothingsupply@yahoo.com";
        String host = "sandbox.smtp.mailtrap.io";
        final String username = "3fd865f7e6364f";
        final String password = "ff57e41eb72590";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");

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

    private void login() {
        loginPage frame = new loginPage();
        frame.setVisible(true);
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Logout functionality to be implemented");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CreatePages::new);
    }
}