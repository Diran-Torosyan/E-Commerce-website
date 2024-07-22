import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class CreatePages extends JFrame {

    JTextField searchBar;
    JButton searchButton;
    JPanel items;

    // Constructor to set up the home display
    public CreatePages() {
        homeDisplay();
    }

    // Method to display the home page
    void homeDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("E-Commerce Website");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Bar at the top of the screen
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

        searchButton = new JButton("Search Here");
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

        bar.add(home);
        bar.add(searchBar);
        bar.add(searchButton);
        bar.add(space);
        bar.add(cartButton);
        bar.add(checkoutButton);
        bar.add(logoutButton);

        add(bar, BorderLayout.NORTH);

        items = new JPanel();
        items.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(items, BorderLayout.CENTER);

        setResizable(false);
        setVisible(true);
    }

    private void search() {
        JOptionPane.showMessageDialog(this, "Search functionality to be implemented");
    }

    private void viewCart() {
        JOptionPane.showMessageDialog(this, "View Cart functionality to be implemented");
    }

    private void checkout() {
        JOptionPane.showMessageDialog(this, "Checkout functionality to be implemented");
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Logout functionality to be implemented");
    }
}
