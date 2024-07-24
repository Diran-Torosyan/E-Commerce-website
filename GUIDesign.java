import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


class CreatePages extends JFrame{
    
    //Store store = new Store();


    JTextField searchBar;
    JButton searchButton;
    JPanel items;
    BufferedImage [] allImages;


    //constructor to set up home display
    public CreatePages() {
        homeDisplay();
        itemDisplay();
    }

    // creates everything that will be used in the homepage as well as names the homepage//
    //using java swing and awt to create the homepage//
    void homeDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("E-Commerce Website");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // bar at top of screen that holds searchbar, cart,checkout and logout button//
        JPanel bar = new JPanel();
        bar.setPreferredSize(new Dimension(screenSize.width,50));
        bar.setBackground(Color.WHITE);
        bar.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        JLabel home = new JLabel("Home");
        home.setForeground(Color.GRAY);
        home.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK, Color.GRAY));
        home.setFocusable(true);

        searchBar = new JTextField("search here");
        searchBar.setPreferredSize(new Dimension(300,30));
        searchBar.setFocusable(true);

        JButton searchButton = new JButton("search");
        searchButton.setPreferredSize(new Dimension(100,30));
        searchButton.addActionListener(e -> search());

        JLabel space = new JLabel();
        space.setPreferredSize(new Dimension(300,30));
        space.setFocusable(true);

        JButton cartButton = new JButton("Cart");
        cartButton.setForeground(Color.GRAY);
        cartButton.setPreferredSize(new Dimension(80, 30));
        cartButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK, Color.GRAY));
        cartButton.setFocusable(true);
        cartButton.addActionListener(e ->viewCart());

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setForeground(Color.GRAY);
        checkoutButton.setPreferredSize(new Dimension(100, 30));
        checkoutButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK, Color.GRAY));
        checkoutButton.setFocusable(true);
        checkoutButton.addActionListener(e -> checkout());

        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.GRAY);
        logoutButton.setPreferredSize(new Dimension(80, 30));
        logoutButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK, Color.GRAY));
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

        add(bar,BorderLayout.NORTH);


        
        
        items = new JPanel();
        items.setLayout(new GridLayout(3,3, 10, 10));
        items.setPreferredSize(new Dimension((int)screenSize.getWidth(),400));
        add(items, BorderLayout.CENTER);

        JPanel navPanel = new JPanel();
        add(navPanel,BorderLayout.SOUTH);

        JButton previous = new JButton("previous");
        previous.setForeground(Color.GRAY);
        previous.setPreferredSize(new Dimension(80, 30));
        previous.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK, Color.GRAY));
        previous.setFocusable(true);
        previous.addActionListener(e -> previousImage());
        navPanel.add(previous);

        JButton next = new JButton("next");
        next.setForeground(Color.GRAY);
        next.setPreferredSize(new Dimension(80, 30));
        next.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.BLACK, Color.GRAY));
        next.setFocusable(true);
        next.addActionListener(e -> nextImage());
        navPanel.add(next);
        
        setResizable(false);
        setVisible(true);
    }

    void itemDisplay() {
        File path = new File("Images");
        File[] allFiles = path.listFiles();
        
        if (allFiles == null) {
            System.out.println("No files found in the directory or directory does not exist.");
            return;
        }
    
        BufferedImage[] allImages = new BufferedImage[allFiles.length];
        JLabel[] label = new JLabel[allFiles.length];
    
        for (int i = 0; i < allFiles.length; i++) {
            try {
                allImages[i] = ImageIO.read(allFiles[i]);
                label[i] = new JLabel();
    
                ImageIcon icon = new ImageIcon(allImages[i]);
                label[i].setIcon(icon);
                items.add(label[i]);
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        items.revalidate();
        items.repaint();
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

    private void previousImage() {
        // Implement logout functionality here
        JOptionPane.showMessageDialog(this, "Logout functionality to be implemented");
    }

    private void nextImage() {
        // Implement logout functionality here
        JOptionPane.showMessageDialog(this, "Logout functionality to be implemented");
    }

    public static void main(String[] args) {
        CreatePages cp = new CreatePages();
        cp.homeDisplay();
    }
    
 }


    