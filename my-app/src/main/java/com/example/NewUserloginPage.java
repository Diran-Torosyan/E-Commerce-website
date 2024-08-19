package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class demonstrates the GUI design for the new user login page with various fields for user information.
 * 
 * <p>The NewUserloginPage class extends JFrame to access its components and formats an interactive form for new user setup.</p>
 * 
 * @author Diran Torosyan
 */
public class NewUserloginPage extends JFrame {
    private barTextField firstnameBar;
    private barTextField lastnameBar;
    private barTextField emailBar;
    private barTextField addressBar;
    private barTextField cityBar;
    private barTextField stateBar;
    private barTextField zipBar;
    private barTextField passwordBar; 

    /**
     * Constructs a new new user login page and displays it.
     */
    public NewUserloginPage() {
        NewUserloginDisplay();
    }

    /**
     * Formats the new user login display; arranges the specification of the text bars.
     */
    void NewUserloginDisplay() {
        setTitle("Account Setup");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel loginBar = new JPanel();
        loginBar.setPreferredSize(new Dimension(200, 200));
        loginBar.setBackground(Color.WHITE);
        loginBar.setLayout(new BoxLayout(loginBar, BoxLayout.Y_AXIS));

        firstnameBar = new barTextField("Enter Firstname");
        lastnameBar = new barTextField("Enter Lastname");
        emailBar = new barTextField("Enter Email");
        addressBar = new barTextField("Enter Address");
        cityBar = new barTextField("Enter City");
        stateBar = new barTextField("Enter State");
        zipBar = new barTextField("Enter Zip");
        passwordBar = new barTextField("Enter Password"); 

        loginBar.add(firstnameBar);
        loginBar.add(lastnameBar);
        loginBar.add(emailBar);
        loginBar.add(addressBar);
        loginBar.add(cityBar);
        loginBar.add(stateBar);
        loginBar.add(zipBar);
        loginBar.add(passwordBar);

        JButton enterButton = new JButton("Enter");
        enterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewUser();
            }
        });

        loginBar.add(enterButton);

        add(loginBar, BorderLayout.CENTER);
    }

    /**
     * Gathers the data from the input fields and creates a new user, saving their information.
     * Sends email and password to login.txt, and other information to customer.txt.
     */
    private void createNewUser() {
        String firstName = firstnameBar.getText();
        String lastName = lastnameBar.getText();
        String email = emailBar.getText();
        String address = addressBar.getText();
        String city = cityBar.getText();
        String state = stateBar.getText();
        int zipCode = Integer.parseInt(zipBar.getText());
        String password = passwordBar.getText(); 

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAddress(address);
        user.setCity(city);
        user.setState(state);
        user.setZipcode(zipCode);

        user.customerInfo(); 
        user.customerLogin(email, password);

        JOptionPane.showMessageDialog(this, "User Created Successfully");
        dispose();  
    }

    /**
     * The 'main' method
     * 
     * <p>Entry point of the application.</p>
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewUserloginPage::new);
    }
}
