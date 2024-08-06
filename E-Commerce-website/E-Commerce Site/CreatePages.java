import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreatePages extends JFrame {
    private JTextField searchBar;
    private JButton searchButton;
    private JPanel items;
    private BufferedImage[] allImages;
    private Cart cart;
    private Map<String, Product> products;

    // Add filter checkboxes
    private JCheckBox menFilter; // Updated filter option for Men's category
    private JCheckBox womenFilter; // Updated filter option for Women's category

    // Constructor to set up home display
    public CreatePages() {
        cart = new Cart();  // Initialize the cart
        initializeProducts(); // Initialize the products
        homeDisplay();
        itemDisplay();
    }

    // Initialize the products
    private void initializeProducts() {
        products = new HashMap<>();
        products.put("101.jpeg", new Product("101", "Black T-Shirt", "Description of item 101", 13.89, "product pictures/101.jpeg", "Men's"));
        products.put("102.jpeg", new Product("102", "Tie-Die Shirt", "Description of item 102", 17.88, "product pictures/102.jpeg", "Women's"));
        products.put("103.jpeg", new Product("103", "White T-Shirt", "Description of item 103", 22.45, "product pictures/103.jpeg", "Men's"));
        products.put("104.jpeg", new Product("104", "Green Hoodie", "Description of item 104", 30.99, "product pictures/104.jpeg", "Men's"));
        products.put("105.jpeg", new Product("105", "Green Jacket", "Description of item 105", 25.50, "product pictures/105.jpeg", "Men's"));
        products.put("106.jpeg", new Product("106", "Green Jacket", "Description of item 106", 15.75, "product pictures/106.jpeg", "Women's"));
        products.put("107.jpeg", new Product("107", "Red Skirt", "Description of item 107", 29.99, "product pictures/107.jpeg", "Women's"));
        products.put("108.jpeg", new Product("108", "Brown Dress", "Description of item 108", 19.45, "product pictures/108.jpeg", "Women's"));
        products.put("109.jpeg", new Product("109", "Red Swim Trunks", "Description of item 109", 33.33, "product pictures/109.jpeg", "Men's"));
        products.put("110.jpeg", new Product("110", "Blue Jeans", "Description of item 110", 27.89, "product pictures/110.jpeg", "Women's"));
        products.put("111.jpeg", new Product("111", "Black Jeans", "Description of item 111", 40.00, "product pictures/111.jpeg", "Men's"));
        products.put("112.jpeg", new Product("112", "Black Slacks", "Description of item 112", 49.99, "product pictures/112.jpeg", "Men's"));
        products.put("113.jpeg", new Product("113", "Black Slacks", "Description of item 113", 45.25, "product pictures/113.jpeg", "Men's"));
        products.put("114.jpeg", new Product("114", "Black Pants", "Description of item 114", 38.50, "product pictures/114.jpeg", "Men's"));
        products.put("115.jpeg", new Product("115", "Pink Shirt", "Description of item 115", 42.10, "product pictures/115.jpeg", "Women's"));

        // Print products to verify initialization
        for (Product product : products.values()) {
            System.out.println(product.getName() + " - " + product.getCategory());
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

        searchButton = new JButton("search"); // Ensure this matches the private JButton searchButton field
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addActionListener(e -> search()); // Update to call the new search method

        // Add space for filter options in the bar
        menFilter = new JCheckBox("Men's"); // Adding filter option for Men's category
        womenFilter = new JCheckBox("Women's"); // Adding filter option for Women's category
        menFilter.addActionListener(e -> applyFilters()); // Add action listener to Men's filter
        womenFilter.addActionListener(e -> applyFilters()); // Add action listener to Women's filter

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
        bar.add(menFilter); // Adding Men's filter to the bar
        bar.add(womenFilter); // Adding Women's filter to the bar
        bar.add(space);
        bar.add(cartButton);
        bar.add(checkoutButton);
        bar.add(logoutButton);

        add(bar, BorderLayout.NORTH);
        items = new JPanel();
        items.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(items, BorderLayout.CENTER);

        JPanel navPanel = new JPanel();
        add(navPanel, BorderLayout.SOUTH);

        JButton previous = new JButton("previous");
        previous.setForeground(Color.GRAY);
        previous.setPreferredSize(new Dimension(80, 30));
        previous.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        previous.setFocusable(true);
        previous.addActionListener(e -> previousImage());
        navPanel.add(previous);

        JButton next = new JButton("next");
        next.setForeground(Color.GRAY);
        next.setPreferredSize(new Dimension(80, 30));
        next.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY));
        next.setFocusable(true);
        next.addActionListener(e -> nextImage());
        navPanel.add(next);

        setResizable(false);
        setVisible(true);
    }

    void itemDisplay() {
        items.removeAll();  // Clear any existing items

        for (Product product : products.values()) {
            try {
                BufferedImage img = ImageIO.read(new File(product.getImagePath()));
                if (img == null) {
                    System.out.println("Skipping file: " + product.getImagePath() + " - Not a valid image");
                    continue;
                }

                JLabel label = new JLabel();
                ImageIcon icon = new ImageIcon(img.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                label.setIcon(icon);
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        new ProductDetailsPage(product, cart);
                    }
                });
                items.add(label);

                JButton addButton = new JButton("Add to Cart");
                addButton.addActionListener(e -> cart.addItem(product.getName(), product.getPrice()));
                items.add(addButton);

                System.out.println("Added image: " + product.getImagePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        items.revalidate();
        items.repaint();
        System.out.println("Total images: " + products.size());
    }

    private void search() {
        Map<String, Product> searchedProducts = searchProducts();
        filterProducts(searchedProducts); // Apply filters to the searched products
    }

    private Map<String, Product> searchProducts() {
        String query = searchBar.getText().toLowerCase(); // Get search query
        Map<String, Product> searchedProducts = new HashMap<>(); // Create a new map for searched products

        // Filter products based on search query
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(query)) {
                searchedProducts.put(product.getImagePath(), product);
            }
        }

        // Print the initial searched products
        System.out.println("Products after search filter:");
        for (Product product : searchedProducts.values()) {
            System.out.println(product.getName() + " - " + product.getCategory());
        }

        return searchedProducts;
    }

    private void filterProducts(Map<String, Product> productsToFilter) {
        // Apply filters based on checkboxes
        if (menFilter.isSelected()) {
            productsToFilter.values().removeIf(product -> !product.getCategory().equalsIgnoreCase("Men's"));
        }

        if (womenFilter.isSelected()) {
            productsToFilter.values().removeIf(product -> !product.getCategory().equalsIgnoreCase("Women's"));
        }

        // Print the products after applying the filters
        System.out.println("Products after applying filters:");
        for (Product product : productsToFilter.values()) {
            System.out.println(product.getName() + " - " + product.getCategory());
        }

        // Display the filtered products
        displayFilteredProducts(productsToFilter);
    }

    private void applyFilters() {
        Map<String, Product> searchedProducts = searchProducts(); // Apply search first
        filterProducts(searchedProducts); // Then apply filters
    }

    private void displayFilteredProducts(Map<String, Product> filteredProducts) {
        items.removeAll(); // Clear the current display

        // Print the products being displayed
        System.out.println("Displaying products:");
        for (Product product : filteredProducts.values()) {
            System.out.println(product.getName() + " - " + product.getCategory());

            try {
                BufferedImage img = ImageIO.read(new File(product.getImagePath()));
                if (img == null) {
                    System.out.println("Skipping file: " + product.getImagePath() + " - Not a valid image");
                    continue;
                }

                JLabel label = new JLabel();
                ImageIcon icon = new ImageIcon(img.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                label.setIcon(icon);
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        new ProductDetailsPage(product, cart);
                    }
                });
                items.add(label);

                JButton addButton = new JButton("Add to Cart");
                addButton.addActionListener(e -> cart.addItem(product.getName(), product.getPrice()));
                items.add(addButton);

                System.out.println("Added image: " + product.getImagePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        items.revalidate();
        items.repaint();
    }

    private void viewCart() {
        // Display the items in the cart
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

        for (Cart.Item item : cart.getItems()) {
            cartPanel.add(new JLabel(item.getName() + " - $" + String.format("%.2f", item.getPrice())));
        }

        JOptionPane.showMessageDialog(this, cartPanel, "Your Cart", JOptionPane.INFORMATION_MESSAGE);
    }

    private void checkout() {
        // Implement checkout functionality here
        JOptionPane.showMessageDialog(this, "Checkout functionality to be implemented");
    }

    private void logout() {
        // Implement logout functionality here
        JOptionPane.showMessageDialog(this, "Logout functionality to be implemented");
    }

    private void previousImage() {
        // Implement previous image functionality here
        JOptionPane.showMessageDialog(this, "Previous image functionality to be implemented");
    }

    private void nextImage() {
        // Implement next image functionality here
        JOptionPane.showMessageDialog(this, "Next image functionality to be implemented");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CreatePages::new);
    }
}
