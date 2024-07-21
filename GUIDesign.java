import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;

//this will be the complete window that contains all of the elements of the page//

class CreatePages extends JFrame{
    
    //Store store = new Store();


    JTextField searchBar;
    JButton searchButton;
    JPanel items;


    // creates everything that will be used in the homepage as well as names the homepage//
    //using java swing and awt to create the homepage//
    void homeDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("E-Commerce Website");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(0,0,0));


        // bar at top of screen that holds searchbar, cart,checkout and logout button//
        JPanel bar = new JPanel();
        bar.setPreferredSize(new Dimension(screenSize.width,50));
        bar.setBackground(Color.WHITE);
        bar.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        JLabel home = new JLabel("Home");
        home.setForeground(Color.GRAY);
        home.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK, getForeground()));
        home.setFocusable(true);

        searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(300,30));
        searchBar.setFocusable(true);

        JButton searchButton = new JButton("search here");
        searchButton.setPreferredSize(new Dimension(100,30));
        searchButton.addActionListener(e -> search());

        JLabel space = new JLabel();
        space.setPreferredSize(new Dimension(300,30));
        space.setFocusable(true);

        JButton cart = new JButton();
        cart.setForeground(Color.GRAY);
        cart.setPreferredSize(new Dimension(80, 30));
        cart.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK, getForeground()));
        cart.setFocusable(true);
        cart.addActionListener(e ->viewCart());

        JButton checkout = new JButton();
        checkout.setForeground(Color.GRAY);
        checkout.setPreferredSize(new Dimension(100, 30));
        checkout.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK, getForeground()));
        checkout.setFocusable(true);
        checkout.addActionListener(e -> checkout());

        JButton logout = new JButton();
        logout.setForeground(Color.GRAY);
        logout.setPreferredSize(new Dimension(80, 30));
        logout.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK, getForeground()));
        logout.setFocusable(true);
        logout.addActionListener(e -> logout());


        add(bar);
        bar.add(home);
        bar.add(searchBar);
        bar.add(searchButton);
        bar.add(space);
        bar.add(cart);
        bar.add(checkout);
        bar.add(logout);

        add(bar,BorderLayout.NORTH);
        setResizable(false);
        setVisible(true);
    }
    private void search() {
        // Implement search functionality here
        JOptionPane.showMessageDialog(this, "Search functionality to be implemented");
    }

    private void viewCart() {
        // Implement view cart functionality here
        JOptionPane.showMessageDialog(this, "View Cart functionality to be implemented");
    }

    private void checkout() {
        // Implement checkout functionality here
        JOptionPane.showMessageDialog(this, "Checkout functionality to be implemented");
    }

    private void logout() {
        // Implement logout functionality here
        JOptionPane.showMessageDialog(this, "Logout functionality to be implemented");
    }

    public static void main(String[] args) {
        CreatePages cp = new CreatePages();
        cp.homeDisplay();
    }
    

    
 }


    