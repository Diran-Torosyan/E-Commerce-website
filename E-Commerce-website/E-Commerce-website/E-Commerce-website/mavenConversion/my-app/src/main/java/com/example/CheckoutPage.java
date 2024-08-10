package com.example;


import javax.swing.*;
import java.awt.*;


public class CheckoutPage extends JFrame {
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
        CheckoutBar.add(EnterButton);
        add(CheckoutBar,BorderLayout.SOUTH);



        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
}
