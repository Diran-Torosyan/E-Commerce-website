/*
Main.java
7/17/24
Jack Ray and Diran Torosyan
d) Main.java is the central class of an e-commerce application. It creates
the landing page GUI including the functionality to compontents like the search
bar and the filters. 

e) Brief explanation of important functions in each class, including its input
values and output values
f) Any important data structure in class/methods
g) Briefly describe any algorithm that you may have used and why did you
select it upon other algorithms where more than one option exists.
*/

package com.example;

import java.io.FileNotFoundException;
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
import java.util.Properties;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {
    private JTextField searchBar;
    private JButton searchButton;
    private JPanel items;
    private JScrollPane scrollPane;
    private Cart cart;
    private Map<Integer, Item> products;

    private JCheckBox menFilter;
    private JCheckBox womenFilter;

    private JCheckBox topsFilter;
    private JCheckBox bottomsFilter;
    private JCheckBox shoesFilter;

    public Main() {
        cart = new Cart();
        initializeProducts();
        homeDisplay();
        itemDisplay(products);
    }

    private void initializeProducts() {
        products = new HashMap<>();
        try {
            for (int i = 101; i <= 130; i++) {
                Item item = new Item();
                System.out.println("Initializing product with ID: " + i); // Debug print
                if (item.readFile(i)) {
                    products.put(i, item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void homeDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("E-Commerce Website");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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

        // Initialize the filters
        menFilter = new JCheckBox("Men's");
        womenFilter = new JCheckBox("Women's");

        topsFilter = new JCheckBox("Top's");
        bottomsFilter = new JCheckBox("Bottom's");
        shoesFilter = new JCheckBox("Shoe's");

        // Add filter action listeners
        menFilter.addActionListener(e -> applyFilters());
        womenFilter.addActionListener(e -> applyFilters());
        topsFilter.addActionListener(e -> applyFilters());
        bottomsFilter.addActionListener(e -> applyFilters());
        shoesFilter.addActionListener(e -> applyFilters());

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

        JButton NewUserButton = new JButton("New User");
        NewUserButton.setForeground(Color.GRAY);
        NewUserButton.setPreferredSize(new Dimension(80, 30));
        NewUserButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        NewUserButton.setFocusable(true);
        NewUserButton.addActionListener(e -> NewUser());

        add(bar);
        bar.add(home);
        bar.add(searchBar);
        bar.add(searchButton);
        bar.add(menFilter);
        bar.add(womenFilter);
        bar.add(topsFilter);
        bar.add(bottomsFilter);
        bar.add(shoesFilter);
        bar.add(cartButton);
        bar.add(checkoutButton);
        bar.add(loginButton);
        bar.add(NewUserButton);

        add(bar, BorderLayout.NORTH);
        items = new JPanel();
        items.setLayout(new GridLayout(0, 4, 10, 10));

        scrollPane = new JScrollPane(items);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);


        add(scrollPane, BorderLayout.CENTER);

        setResizable(false);
        setVisible(true);
    }

    private void itemDisplay(Map<Integer, Item> productsToDisplay) {
        items.removeAll();

        for (Item item : productsToDisplay.values()) {
            try {
                BufferedImage img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(item.getImagePath()));

                if (img == null) {
                    continue;
                }

                JPanel productPanel = new JPanel();
                productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

                JLabel label = new JLabel();
                ImageIcon icon = new ImageIcon(img.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                label.setIcon(icon);
                label.setAlignmentX(Component.CENTER_ALIGNMENT);

                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        new ProductDetailsPage(item, cart);
                    }
                });

                productPanel.add(label);

                JButton addButton = new JButton("Add to Cart");
                addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                addButton.addActionListener(e -> cart.addItem(item.getItemName(), item.getPrice()));

                productPanel.add(addButton);

                items.add(productPanel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        items.revalidate();
        items.repaint();
    }

    private void search() {
        Map<Integer, Item> searchedProducts = searchProducts();
        filterProducts(searchedProducts);
    }

    private Map<Integer, Item> searchProducts() {
        String query = searchBar.getText().toLowerCase();
        Map<Integer, Item> searchedProducts = new HashMap<>();

        for (Item item : products.values()) {
            if (item.getItemName().toLowerCase().contains(query)) {
                searchedProducts.put(item.getItemId(), item);
            }
        }

        return searchedProducts;
    }

    private void filterProducts(Map<Integer, Item> productsToFilter) {
        Map<Integer, Item> filteredProducts = new HashMap<>();

        if (!menFilter.isSelected() && !womenFilter.isSelected() && !topsFilter.isSelected() && !bottomsFilter.isSelected() && !shoesFilter.isSelected()) {
            filteredProducts.putAll(productsToFilter);
        } else {
            for (Item item : productsToFilter.values()) {
                boolean genderMatch = (!menFilter.isSelected() && !womenFilter.isSelected()) || 
                                      (menFilter.isSelected() && item.getGenderCategory().equalsIgnoreCase("Men's")) ||
                                      (womenFilter.isSelected() && item.getGenderCategory().equalsIgnoreCase("Women's"));
                boolean typeMatch = (!topsFilter.isSelected() && !bottomsFilter.isSelected() && !shoesFilter.isSelected()) ||
                                    (topsFilter.isSelected() && item.getTypeCategory().equalsIgnoreCase("Top's")) ||
                                    (bottomsFilter.isSelected() && item.getTypeCategory().equalsIgnoreCase("Bottom's")) ||
                                    (shoesFilter.isSelected() && item.getTypeCategory().equalsIgnoreCase("Shoe's"));

                if (genderMatch && typeMatch) {
                    filteredProducts.put(item.getItemId(), item);
                }
            }
        }

        itemDisplay(filteredProducts);
    }

    private void applyFilters() {
        Map<Integer, Item> searchedProducts = searchProducts();
        filterProducts(searchedProducts);
    }

    private void viewCart() {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

        for (Cart.Item item : cart.getItems()) {
            cartPanel.add(new JLabel(item.getName() + " - $" + String.format("%.2f", item.getPrice())));
        }

        JOptionPane.showMessageDialog(this, cartPanel, "Your Cart", JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkout() {
        new CheckoutPage(cart);
        //sendEmailReceipt();
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

    private void NewUser() {
        NewUserloginPage frame = new NewUserloginPage();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
