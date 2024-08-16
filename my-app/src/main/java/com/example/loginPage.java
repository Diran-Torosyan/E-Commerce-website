package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class demonstrates the GUI design for the login page with username and password fields.
 *
 * <p>The login page class extends JFrame to create an interactive login form, allowing users to input their credentials for authentication.</p>
 *
 * @author Diran Torosyan
 */
public class loginPage extends JFrame {
    private barTextField usernameField;  // Declaring the username field
    private barTextField passwordField;  // Declaring the password field
    private User user; // Reference to the User class to handle login logic
    private Main mainFrame; // Reference to Main to update logged-in info
    private boolean isUsernameFieldFirstFocus = true; // Flag to track initial focus on the username field
    private boolean isPasswordFieldFirstFocus = true;

    /**
     * Constructs a new login page and displays it.
     *
     * @param mainFrame reference to the Main class to update the logged-in label
     */
    public loginPage(Main mainFrame) {
        this.mainFrame = mainFrame; // Initialize Main reference
        user = new User(); // Initialize User class
        loginDisplay();
    }

    /**
     * Formats the login display; arranges the specification of the text fields and buttons.
     */
    void loginDisplay() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel loginBar = new JPanel();
        loginBar.setPreferredSize(new Dimension(200, 200));
        loginBar.setBackground(Color.WHITE);
        loginBar.setLayout(new BoxLayout(loginBar, BoxLayout.Y_AXIS));

        usernameField = new barTextField("Enter Username (Email)");
        usernameField.setPreferredSize(new Dimension(150, 30));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add FocusListener to clear placeholder text
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!isUsernameFieldFirstFocus && usernameField.getText().equals("Enter Username (Email)")) {
                    usernameField.setText("");
                }
                isUsernameFieldFirstFocus = false;
            }
        });

        passwordField = new barTextField("Enter Password");
        passwordField.setPreferredSize(new Dimension(150, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add FocusListener to clear placeholder text
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!isPasswordFieldFirstFocus && passwordField.getText().equals("Enter Password")) {
                    passwordField.setText("");
                }
                isPasswordFieldFirstFocus = false;
            }
        });

        // Update the existing button to "User Login"
        JButton userLoginButton = new JButton("User Login");
        userLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin(); // Retain existing functionality
            }
        });

        // Add a new "Admin Login" button
        JButton adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAdminLogin(); // Implementing admin login functionality
            }
        });

        loginBar.add(usernameField);
        loginBar.add(passwordField);
        loginBar.add(userLoginButton); // Add the user login button
        loginBar.add(adminLoginButton); // Add the admin login button

        add(loginBar, BorderLayout.CENTER);
    }

    /**
     * Handles the login process by collecting input from the username and password fields
     * and passing them to the loginCheck function in the User class for validation.
     * If the login is successful, the main frame is updated with the logged-in user information.
     */
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (user.loginCheck(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            mainFrame.updateLoggedInLabel(username); // Update the main frame with the logged-in email
            dispose(); // Close the login page after successful login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials. Please Try Again.");
        }
    }

    /**
     * Handles the admin login process by using the admin method in the User class.
     */
    private void handleAdminLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (adminLoginCheck(username, password)) {
            JOptionPane.showMessageDialog(this, "Admin Login Successful");
            mainFrame.updateLoggedInLabel(username); // Update the main frame with the admin's email
            dispose(); // Close the login page after successful login

            // Open the admin page
            new adminPage().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Admin Credentials. Please Try Again.");
        }
    }

    /**
     * Checks if the provided username and password match any credentials in the admin.txt file.
     *
     * @param username The admin's email (username)
     * @param password The admin's password
     * @return true if the credentials match, false otherwise
     */
    private boolean adminLoginCheck(String username, String password) {
        File adminFile = new File("src/main/resources/admin.txt");
        try (Scanner ad = new Scanner(adminFile)) {
            while (ad.hasNextLine()) {
                String name = ad.nextLine().trim();
                if (name.isEmpty()) continue;

                if (ad.hasNextLine()) {
                    String pass = ad.nextLine().trim();
                    if (pass.isEmpty()) continue;

                    if (username.equals(name) && password.equals(pass)) {
                        return true;
                    }
                } else {
                    System.out.println("File format error: missing password for the last username.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Admin file not found: " + e.getMessage());
        }

        return false;
    }

    /**
     * The 'main' method
     *
     * <p>Entry point of the application.</p>
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new loginPage(null).setVisible(true)); // No mainFrame reference here
    }
}
