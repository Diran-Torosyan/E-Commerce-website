/**
 * Main.java is the central class of an e-commerce application. It creates
 * the landing page GUI, including functionality for components like the search
 * bar and the filters. It connects all other pages and files, serving as the focal
 * point for the application's operations. This class is responsible for handling
 * user interactions, product displays, and navigation to other parts of the application.
* 7/17/24
 * Jack Ray and Diran Torosyan
 */
package com.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
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

    private JLabel loggedInLabel;
    private String loggedInEmail;

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

        JButton adminButton = new JButton("Admin");
        adminButton.setForeground(Color.GRAY);
        adminButton.setPreferredSize(new Dimension(80, 30));
        adminButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        adminButton.setFocusable(true);
        adminButton.addActionListener(e -> admin());

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
     * 
     * @param email The email of the logged-in user.
     */
    public void updateLoggedInLabel(String email) {
        loggedInEmail = email;
        loggedInLabel.setText("Logged in as: " + email);
    }

    /**
     * Displays the items on the homepage in a grid layout. Each product is displayed
     * with an image, and users can click on the image to view details or add the item to the cart.
     * 
     * @param productsToDisplay A map of products to be displayed.
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

    /**
     * Initiates a search based on the input in the search bar, filters the products accordingly,
     * and updates the display with the filtered products.
     */
    private void search() {
        Map<Integer, Item> searchedProducts = searchProducts();
        filterProducts(searchedProducts);
    }

    /**
     * Searches for products whose names contain the query string entered in the search bar.
     * 
     * @return A map of products matching the search query.
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
     * Filters the products based on the selected filters
     * and updates the display with the filtered products.
     * @param productsToFilter A map of products to filter based on the selected filters.
     */
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

    /**
     * Applies the selected filters to the search results and updates the display.
     */
    private void applyFilters() {
        Map<Integer, Item> searchedProducts = searchProducts();
        filterProducts(searchedProducts);
    }

    /**
     * Shows the user's cart in a dialog box, showing all items added to the cart.
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
     * Initiates the checkout process. If the user is logged in, it loads the user's information
     * and proceeds to the checkout page. If the user is not logged in, it prompts the user to log in.
     */
    private void checkout() {
        if (loggedInEmail != null) {
            User user = new User();
            user.loadCustomerData(loggedInEmail); 
            new CheckoutPage(cart, user); 
        } else {
            JOptionPane.showMessageDialog(this, "Please log in before proceeding to checkout.");
        }
    }

    /**
     * Opens the login page for user authentication.
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

    private void admin() {
        adminPage frame = new adminPage();
        frame.setVisible(true);
        
    }

    /**
     * The main method that starts the application by invoking the Main class.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
