package com.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * This class demonstrates the main GUI design for the e-commerce application with product showcase, filters, 
 * search, cart, checkout, and user login/signup functionalities.
 * 
 * <p>The Main class extends JFrame class in the Java Swing library to access its components. It formats an
 * interactive main page with the products' images. It also includes check boxes to apply filters, a search bar to 
 * filter and display products based on String input, and buttons to view cart, checkout, and login/signup. 
 * 
 * @author Jack Ray
 * @author Diran Torosyan
 * @since 1.0 (2024-07-17)
 */
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
    private JCheckBox saleFilter;  

    private String loggedInEmail;
    private JLabel loggedInLabel;
    /**
     * Constructor for Main. Initializes the cart, loads products, and displays
     * the home page of the application with product listings.
     */

    public Main() {
        cart = new Cart();
        initializeProducts();
        homeDisplay();
        itemDisplay(products);
    }
    /**
     * Initializes the product map by reading item data from files and populating
     * the product list. Each product is loaded based on its item ID.
     */
    private void initializeProducts() {
        products = new HashMap<>();
        try {
            for (int i = 101; i <= 130; i++) {
                Item item = new Item();
                System.out.println("Initializing product with ID: " + i); 
                if (item.readFile(i)) {
                    products.put(i, item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates and displays the main homepage. Sets up the GUI layout, including
     * the search bar, filters, and buttons for cart, checkout, login, and new user registration.
     */
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

        menFilter = new JCheckBox("Men's");
        womenFilter = new JCheckBox("Women's");

        topsFilter = new JCheckBox("Top's");
        bottomsFilter = new JCheckBox("Bottom's");
        shoesFilter = new JCheckBox("Shoe's");
        saleFilter = new JCheckBox("On Sale");  

        menFilter.addActionListener(e -> applyFilters());
        womenFilter.addActionListener(e -> applyFilters());
        topsFilter.addActionListener(e -> applyFilters());
        bottomsFilter.addActionListener(e -> applyFilters());
        shoesFilter.addActionListener(e -> applyFilters());
        saleFilter.addActionListener(e -> applyFilters());  

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

        loggedInLabel = new JLabel(""); 
        loggedInLabel.setForeground(Color.BLUE);

        add(bar);
        bar.add(home);
        bar.add(searchBar);
        bar.add(searchButton);
        bar.add(menFilter);
        bar.add(womenFilter);
        bar.add(topsFilter);
        bar.add(bottomsFilter);
        bar.add(shoesFilter);
        bar.add(saleFilter);  
        bar.add(cartButton);
        bar.add(checkoutButton);
        bar.add(loginButton);
        bar.add(NewUserButton);
        bar.add(loggedInLabel);

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
    /**
     * Updates the logged-in label with the email of the currently logged-in user.
     * @param email the email of the currently logged-in user
     */
    public void updateLoggedInLabel(String email) {
        loggedInEmail = email;
        loggedInLabel.setText("Logged in as: " + email);
    }
    /**
     * Displays the items based on the provided map of products.
     * @param productsToDisplay a map of products to display
     */
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
                addButton.addActionListener(e -> cart.addItem(item.getItemName(), item.getSale() ? item.getSalePrice() : item.getPrice()));

                productPanel.add(addButton);

                items.add(productPanel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        items.revalidate();
        items.repaint();
    }
    /**
     * Performs a search based on the text in the search bar and applies filters
     * to the search results.
     */
    private void search() {
        Map<Integer, Item> searchedProducts = searchProducts();
        filterProducts(searchedProducts);
    }
    /**
     * Searches for products whose names contain the query from the search bar.
     * @return a map of products that match the search query
     */
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
    /**
     * Filters the provided products based on selected filters and displays the result.
     * @param productsToFilter a map of products to be filtered
     */
    private void filterProducts(Map<Integer, Item> productsToFilter) {
        Map<Integer, Item> filteredProducts = new HashMap<>();

        if (!menFilter.isSelected() && !womenFilter.isSelected() && !topsFilter.isSelected() 
            && !bottomsFilter.isSelected() && !shoesFilter.isSelected() && !saleFilter.isSelected()) {
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
                boolean saleMatch = !saleFilter.isSelected() || (saleFilter.isSelected() && item.getSale());

                if (genderMatch && typeMatch && saleMatch) {
                    filteredProducts.put(item.getItemId(), item);
                }
            }
        }

        itemDisplay(filteredProducts);
    }
    /**
     * Applies selected filters and updates the displayed items.
     */
    private void applyFilters() {
        Map<Integer, Item> searchedProducts = searchProducts();
        filterProducts(searchedProducts);
    }
    /**
     * Displays the cart.
     */
    private void viewCart() {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

        for (Cart.Item item : cart.getItems()) {
            cartPanel.add(new JLabel(item.getName() + " - $" + String.format("%.2f", item.getPrice())));
        }

        JOptionPane.showMessageDialog(this, cartPanel, "Your Cart", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Handles the checkout process.
     */
    private void checkout() {
        if (!cart.getItems().isEmpty()) {
            User user = new User();
            user.loadCustomerData(loggedInEmail); 
            new CheckoutPage(cart, user);
        } else {
            JOptionPane.showMessageDialog(this, "Your cart is empty. Please add items before checkout.");
        }
    }
    /**
     * Opens the login page.
     */
    private void login() {
        loginPage frame = new loginPage(this); 
        frame.setVisible(true);
    }
    /**
     * Opens the new user registration page.
     */
    private void NewUser() {
        NewUserloginPage frame = new NewUserloginPage();
        frame.setVisible(true);
    }
    /**
     * Entry point of the application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
