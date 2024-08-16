

package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/** provide a complete summary for each module/class header: include
* a) CheckoutPage
* b) Aug 13,2024
* c) @author Diran Torosyan
* d) creates the admin page and allows for the admin to see it after login, and adjust the values of items as needed, such as price, 
* inventory, etc. also allows for the modification of items
*
*/
public class adminPage extends JFrame {
    /** 
     * creates instance of admin display
     */
    public adminPage() {
        adminDisplay();
    }
    /**
     * creates the buttons and fields on the page
     */
    void adminDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Admin Page");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel adminBar = new JPanel();
        adminBar.setPreferredSize(new Dimension(screenSize.width, 200));
        adminBar.setBackground(Color.WHITE);
        adminBar.setLayout(new BoxLayout(adminBar,BoxLayout.Y_AXIS));

        barTextField itemBar = new barTextField("Enter Item Name");
        itemBar.setPreferredSize(new Dimension(300, 100));
        itemBar.setAlignmentX(Component.CENTER_ALIGNMENT);


        barTextField inventoryBar = new barTextField("Enter new Inventory");
        inventoryBar.setPreferredSize(new Dimension(300, 100));
        inventoryBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField saleBar = new barTextField("enter if item on sale");
        saleBar.setPreferredSize(new Dimension(300, 100));
        saleBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField priceBar = new barTextField("price of item");
        priceBar.setPreferredSize(new Dimension(300, 100));
        priceBar.setAlignmentX(Component.CENTER_ALIGNMENT);
    
       

        JButton searchButton = new JButton("commit changes");
        searchButton.setPreferredSize(new Dimension(100, 100));
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(e -> change());

        adminBar.add(itemBar);
        adminBar.add(inventoryBar);
        adminBar.add(saleBar);
        adminBar.add(priceBar);
        adminBar.add(searchButton);
        add(adminBar,BorderLayout.CENTER);
    }
    /**
     * will implement the changes for the item
     */
    private void change() {
        
        JOptionPane.showMessageDialog(this, "Change functionality to be implemented");

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            adminPage frame = new adminPage();
            frame.setVisible(true);
        });
    }
}
/**
 * creates the standard for a bar text field that will take user input
 */
class barTextField extends JTextField implements FocusListener {
    private String placeHolder;
    private boolean showPlaceHolder;

    public barTextField(String placeHolder) {
        super(placeHolder);
        this.placeHolder = placeHolder;
        this.showPlaceHolder = true;
        addFocusListener(this);
        setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            setText("");
            setForeground(Color.BLACK);
            showPlaceHolder = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            setText(placeHolder);
            setForeground(Color.GRAY);
            showPlaceHolder = true;
        }
    }

    @Override
    public String getText() {
        return showPlaceHolder ? "" : super.getText();
    }
}
