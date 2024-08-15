package com.example;

import javax.swing.*;
import java.awt.*;

/**
 * This class demonstrates the GUI design for the login page with username and password bars
 * 
 * <p>The login page class extends JFrame class in the Java Swing library to access its components.
 * It formats an interactive login page with username and password bars and launches it.
 * 
 * @author
 */


public class loginPage extends JFrame {
    /**
     * Constructs a new login page and displays it 
     */
    public loginPage() {
        loginDisplay();
    }
    /**
     * Formats the login display; arranges the specification of the text bars
     * 
     * @param loginBar sets format details for login panel
     * @param usernameBar text bar where user inputs username
     * @param passwordBar text bar where user inputs password
     */
    void loginDisplay() {
        setTitle("Login Page");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel loginBar = new JPanel();
        loginBar.setPreferredSize(new Dimension(200, 200));
        loginBar.setBackground(Color.WHITE);
        loginBar.setLayout(new BoxLayout(loginBar,BoxLayout.Y_AXIS));

        barTextField usernameBar = new barTextField("Enter Username");
        usernameBar.setPreferredSize(new Dimension(50, 20));
        usernameBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField passwordBar = new barTextField("Enter Password");
        passwordBar.setPreferredSize(new Dimension(50, 20));
        passwordBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        //submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(10, 25));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBar.add(usernameBar);
        loginBar.add(passwordBar);
        loginBar.add(submitButton);
        add(loginBar,BorderLayout.CENTER);

        submitButton.addActionListener(e -> {
            String username = usernameBar.getText();
            String password = passwordBar.getText();
            boolean success = LoginController.login(username, password);
            if (success) {
                JOptionPane.showMessageDialog(this, "Login Successful.");
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });
    }
    /**
    * The 'main' method
    * 
    * <p>Entry point of the application.
    * 
    * @param args a string-array of command-line arguments passed to the program
    * @param frame instance of loginPage class
    */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            loginPage frame = new loginPage();
            frame.setVisible(true);
        });
    }
}
