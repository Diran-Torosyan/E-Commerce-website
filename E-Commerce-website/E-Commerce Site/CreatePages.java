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
    private Map<String, Double> itemPrices;

    // Constructor to set up home display
    public CreatePages() {
        cart = new Cart();  // Initialize the cart
        initializeItemPrices(); // Initialize the item prices
        homeDisplay();
        itemDisplay();
    }

    // Initialize the item prices
    private void initializeItemPrices() {
        itemPrices = new HashMap<>();
        itemPrices.put("101.jpeg", 13.89);
        itemPrices.put("102.jpeg", 17.88);
        itemPrices.put("103.jpeg", 22.45);
        itemPrices.put("104.jpeg", 30.99);
        itemPrices.put("105.jpeg", 25.50);
        itemPrices.put("106.jpeg", 15.75);
        itemPrices.put("107.jpeg", 29.99);
        itemPrices.put("108.jpeg", 19.45);
        itemPrices.put("109.jpeg", 33.33);
        itemPrices.put("110.jpeg", 27.89);
        itemPrices.put("111.jpeg", 40.00);
        itemPrices.put("112.jpeg", 49.99);
        itemPrices.put("113.jpeg", 45.25);
        itemPrices.put("114.jpeg", 38.50);
        itemPrices.put("115.jpeg", 42.10);
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

        searchBar = new JTextField("search here");
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
        File path = new File("product pictures");
        File[] allFiles = path.listFiles();

        if (allFiles == null) {
            System.out.println("No files found in the directory or directory does not exist.");
            return;
        }

        allImages = new BufferedImage[allFiles.length];
        JLabel[] label = new JLabel[allFiles.length];

        for (int i = 0; i < allFiles.length; i++) {
            try {
                allImages[i] = ImageIO.read(allFiles[i]);
                if (allImages[i] == null) {
                    System.out.println("skipping file" + allFiles[i].getName() + "not valid image");
                    continue;
                }
                label[i] = new JLabel();

                ImageIcon icon = new ImageIcon(allImages[i].getScaledInstance(300, 300, Image.SCALE_SMOOTH));
                label[i].setIcon(icon);
                items.add(label[i]);

                String itemName = allFiles[i].getName();  // Example: using file name as item name
                double itemPrice = itemPrices.getOrDefault(itemName, 0.0); // Get the price from the map or default to 0.0
                JButton addButton = new JButton("Add to Cart");
                addButton.addActionListener(e -> cart.addItem(itemName, itemPrice));
                items.add(addButton);

                System.out.println("added image" + allFiles[i].getName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        items.revalidate();
        items.repaint();
        System.out.println("total images images" + allFiles.length);
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
