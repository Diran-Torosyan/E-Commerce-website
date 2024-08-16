package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/** 
 * Admin Page that allows the admin to log in and modify item details.
 * 
 * <p>The admin page allows the admin to apply discounts and modify item inventory.</p>
 * 
 * a) CheckoutPage
 * b) Aug 13, 2024
 * c) @author Diran Torosyan
 * d) Creates the admin page and allows the admin to log in, adjust item prices, inventory, 
 *    and apply discounts as needed.
 */
public class adminPage extends JFrame {
    private barTextField itemIdBar;
    private barTextField discountBar;

    /** 
     * Creates an instance of the admin display.
     */
    public adminPage() {
        adminDisplay();
    }

    /**
     * Creates the buttons and fields on the admin page.
     */
    void adminDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Admin Page");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel adminBar = new JPanel();
        adminBar.setPreferredSize(new Dimension(screenSize.width, 200));
        adminBar.setBackground(Color.WHITE);
        adminBar.setLayout(new BoxLayout(adminBar, BoxLayout.Y_AXIS));

        itemIdBar = new barTextField("Enter Item ID");
        itemIdBar.setPreferredSize(new Dimension(300, 100));
        itemIdBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        discountBar = new barTextField("Enter Discount Percentage");
        discountBar.setPreferredSize(new Dimension(300, 100));
        discountBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton applyDiscountButton = new JButton("Apply Discount");
        applyDiscountButton.setPreferredSize(new Dimension(100, 100));
        applyDiscountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        applyDiscountButton.addActionListener(e -> applyDiscount());

        adminBar.add(itemIdBar);
        adminBar.add(discountBar);
        adminBar.add(applyDiscountButton);

        add(adminBar, BorderLayout.CENTER);
    }

    /**
     * Applies the discount to the specified item by updating the itemstock.txt file.
     */
    private void applyDiscount() {
        String itemId = itemIdBar.getText().trim();
        String discountPercentageStr = discountBar.getText().trim();
        double discountPercentage;
    
        try {
            discountPercentage = Double.parseDouble(discountPercentageStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid discount percentage.");
            return;
        }
    
        File itemFile = new File("src/main/resources/itemstock.txt");
        List<String> fileContent = new ArrayList<>();
        boolean itemFound = false;
    
        try (BufferedReader br = new BufferedReader(new FileReader(itemFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = parseFile(line);
    
                // Debugging output: print the fields before modification
                System.out.println("Before modification: " + String.join(",", fields));
    
                if (fields[0].trim().equals(itemId)) {
                    itemFound = true;
    
                    double originalPrice = Double.parseDouble(fields[3].trim());
    
                    if (discountPercentage == 0) {
                        fields[11] = "false";  // Set the sale field to false
                        fields[12] = String.format("%.2f", 0.0);  // Reset the sale price to 0.0
                    } else {
                        double salePrice = originalPrice - (originalPrice * (discountPercentage / 100));
                        fields[11] = "true";  // Set the sale field to true
                        fields[12] = String.format("%.2f", salePrice);  // Update the sale price
                    }
    
                    // Debugging output: print the fields after modification
                    System.out.println("After modification: " + String.join(",", fields));
    
                    // Reconstruct the line with updated values
                    line = String.join(",", fields);
    
                    // Debugging output: ensure the correct structure
                    System.out.println("Modified line: " + line);
                }
                fileContent.add(line);
            }
    
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading the itemstock file: " + e.getMessage());
            return;
        }
    
        if (!itemFound) {
            JOptionPane.showMessageDialog(this, "Item ID not found.");
            return;
        }
    
        // Write the updated content back to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(itemFile))) {
            for (String updatedLine : fileContent) {
                bw.write(updatedLine);
                bw.newLine();
            }
            if (discountPercentage == 0) {
                JOptionPane.showMessageDialog(this, "Sale ended for Item ID: " + itemId);
            } else {
                JOptionPane.showMessageDialog(this, "Discount of " + discountPercentageStr + "% applied to Item ID: " + itemId);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to the itemstock file: " + e.getMessage());
        }
    }
    
     
    /**
     * @param line The line to parse.
     * @return An array of fields.
     */
    private String[] parseFile(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean insideQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                insideQuotes = !insideQuotes; // Toggle the insideQuotes flag
            } else if (c == ',' && !insideQuotes) {
                fields.add(currentField.toString().trim());
                currentField.setLength(0); // Clear the current field
            } else {
                currentField.append(c);
            }
        }
        fields.add(currentField.toString().trim()); // Add the last field

        return fields.toArray(new String[0]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            adminPage frame = new adminPage();
            frame.setVisible(true);
        });
    }
}

/**
 * Creates a standard text field that will take user input with placeholder text.
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
        if (this.getText().isEmpty()) {
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
