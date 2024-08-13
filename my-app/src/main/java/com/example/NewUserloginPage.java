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


public class NewUserloginPage extends JFrame {
    /**
     * Constructs a new login page and displays it 
     */
    public NewUserloginPage() {
        NewUserloginDisplay();
    }
    /**
     * Formats the login display; arranges the specification of the text bars
     * 
     * @param loginBar sets format details for login panel
     * @param usernameBar text bar where user inputs username
     * @param passwordBar text bar where user inputs password
     */
    void NewUserloginDisplay() {
        setTitle("Account setup");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel loginBar = new JPanel();
        loginBar.setPreferredSize(new Dimension(200, 200));
        loginBar.setBackground(Color.WHITE);
        loginBar.setLayout(new BoxLayout(loginBar,BoxLayout.Y_AXIS));

        barTextField firstnameBar = new barTextField("Enter Firstname");
        firstnameBar.setPreferredSize(new Dimension(50, 10));
        firstnameBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField lastnameBar = new barTextField("Enter Lastname");
        lastnameBar.setPreferredSize(new Dimension(50, 10));
        lastnameBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField emailBar = new barTextField("Enter Email");
        emailBar.setPreferredSize(new Dimension(50, 10));
        emailBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField addressBar = new barTextField("Enter address");
        addressBar.setPreferredSize(new Dimension(50, 10));
        addressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField cityBar = new barTextField("Enter city");
        cityBar.setPreferredSize(new Dimension(50, 10));
        cityBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField stateBar = new barTextField("Enter state");
        stateBar.setPreferredSize(new Dimension(50, 10));
        stateBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        barTextField zipBar = new barTextField("Enter zip");
        zipBar.setPreferredSize(new Dimension(50, 10));
        zipBar.setAlignmentX(Component.CENTER_ALIGNMENT);



        loginBar.add(firstnameBar);
        loginBar.add(lastnameBar);
        loginBar.add(emailBar);
        loginBar.add(addressBar);
        loginBar.add(cityBar);
        loginBar.add(stateBar);
        loginBar.add(zipBar);
        add(loginBar,BorderLayout.CENTER);
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
