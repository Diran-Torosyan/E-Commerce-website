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
        products.put("101.jpeg", new Product("101", "Item 101", "Description of item 101", 13.89, "product pictures/101.jpeg"));
        products.put("102.jpeg", new Product("102", "Item 102", "Description of item 102", 17.88, "product pictures/102.jpeg"));
        products.put("103.jpeg", new Product("103", "Item 103", "Description of item 103", 22.45, "product pictures/103.jpeg"));
        products.put("104.jpeg", new Product("104", "Item 104", "Description of item 104", 30.99, "product pictures/104.jpeg"));
        products.put("105.jpeg", new Product("105", "Item 105", "Description of item 105", 25.50, "product pictures/105.jpeg"));
        products.put("106.jpeg", new Product("106", "Item 106", "Description of item 106", 15.75, "product pictures/106.jpeg"));
        products.put("107.jpeg", new Product("107", "Item 107", "Description of item 107", 29.99, "product pictures/107.jpeg"));
        products.put("108.jpeg", new Product("108", "Item 108", "Description of item 108", 19.45, "product pictures/108.jpeg"));
        products.put("109.jpeg", new Product("109", "Item 109", "Description of item 109", 33.33, "product pictures/109.jpeg"));
        products.put("110.jpeg", new Product("110", "Item 110", "Description of item 110", 27.89, "product pictures/110.jpeg"));
        products.put("111.jpeg", new Product("111", "Item 111", "Description of item 111", 40.00, "product pictures/111.jpeg"));
        products.put("112.jpeg", new Product("112", "Item 112", "Description of item 112", 49.99, "product pictures/112.jpeg"));
        products.put("113.jpeg", new Product("113", "Item 113", "Description of item 113", 45.25, "product pictures/113.jpeg"));
        products.put("114.jpeg", new Product("114", "Item 114", "Description of item 114", 38.50, "product pictures/114.jpeg"));
        products.put("115.jpeg", new Product("115", "Item 115", "Description of item 115", 42.10, "product pictures/115.jpeg"));
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

        searchBar = new JTextField("search");
        searchBar.setPreferredSize(new Dimension(300, 30));
        searchBar.setFocusable(true);

        JButton searchButton = new JButton("search");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addActionListener(e -> search());

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
        // Implement search functionality here
        JOptionPane.showMessageDialog(this, "Search functionality to be implemented");
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
