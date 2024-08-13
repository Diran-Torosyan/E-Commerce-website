package com.example;

import javax.swing.*;
import java.awt.*;
/*Provide a complete summary for each module/class header: include
a) CheckoutPage
b) Aug 13,2024
c) @author Diran Torosyan
d) creates the checkout page and allows for the user to enter their card information and see the cart
f) calls on the data structures used by the cart and the text file information used
*/
public class CheckoutPage extends JFrame {
    /*
     * creates all buttons and fields for the checkout page
     */
    public CheckoutPage(Cart cart) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Checkout Page");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

        for (Cart.Item item : cart.getItems()) {
            cartPanel.add(new JLabel(item.getName() + " - $" + String.format("%.2f", item.getPrice())));
        }

        JPanel CheckoutBar = new JPanel();
        CheckoutBar.setPreferredSize(new Dimension(screenSize.width, 200));
        CheckoutBar.setBackground(Color.WHITE);
        CheckoutBar.setLayout(new BoxLayout(CheckoutBar,BoxLayout.Y_AXIS));

        barTextField cardNumBar = new barTextField("Enter Card Number");
        cardNumBar.setPreferredSize(new Dimension(300, 100));
        cardNumBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField cardCVBar = new barTextField("Enter Card CVV");
        cardCVBar.setPreferredSize(new Dimension(300, 100));
        cardCVBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField ExpirationBar = new barTextField("Enter Card Expiration Date");
        ExpirationBar.setPreferredSize(new Dimension(100, 100));
        ExpirationBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField cardNameBar = new barTextField("Enter Card Holder Name First and last");
        cardNameBar.setPreferredSize(new Dimension(300, 100));
        cardNameBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton EnterButton = new JButton("Enter");
        EnterButton.addActionListener(e -> {
            
            this.dispose();
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
        });

        add(cartPanel,BorderLayout.CENTER);
        CheckoutBar.add(backButton,BorderLayout.CENTER);
        CheckoutBar.add(cardNumBar);
        CheckoutBar.add(cardCVBar);
        CheckoutBar.add(cardNameBar);
        CheckoutBar.add(ExpirationBar);
        CheckoutBar.add(EnterButton);
        add(CheckoutBar,BorderLayout.SOUTH);



        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}