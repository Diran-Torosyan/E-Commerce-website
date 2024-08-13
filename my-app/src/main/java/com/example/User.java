package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class User {

    // instance variables
    private int custNum = 0;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String address;
    private String city;
    private String state;
    private int zipcode;
    private String custUser;
    private String custPass;

    public User() {}

    private int getLastCustomerId() {
        int lastId = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/customer.txt")))) {
            String line;
            boolean expectingId = true;  // Track when an ID is expected
    
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        int number = Integer.parseInt(line);
    
                        if (expectingId && number >= 1 && number <= 999 && number > lastId) {
                            lastId = number;
                            expectingId = false;  // After reading ID, don't expect another ID until 7 more lines
                        } else if (number > 999) {
                            // If the number is greater than 999, it's treated as a zip code or other data, not an ID
                            expectingId = false;  // Skip ID expectation if a zip code is found
                        }
    
                    } catch (NumberFormatException e) {
                        // Skip this line if it's not an integer
                    }
    
                    expectingId = !expectingId;  // Toggle between expecting an ID and other fields
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }
        return lastId;
    }
    

    public void customerInfo() {
        File customerFile = new File("src/main/resources/customer.txt");
        try {
            // Ensure the directory exists (if not, create it)
            customerFile.getParentFile().mkdirs();
            custNum = getLastCustomerId() + 1;

            BufferedWriter bw = new BufferedWriter(new FileWriter(customerFile, true));
            Scanner scan = new Scanner(System.in);

            bw.newLine();
            bw.write(custNum + "\n");

            System.out.println("First Name:");
            firstName = scan.nextLine();
            bw.write(firstName + "\n");

            System.out.println("Last Name: ");
            lastName = scan.nextLine();
            bw.write(lastName + "\n");

            System.out.println("Email: ");
            email = scan.nextLine();
            bw.write(email + "\n");

            try {
                System.out.println("Phone Number: ");
                phoneNumber = scan.nextInt();
                scan.nextLine(); // consume the newline character
                bw.write(Integer.toString(phoneNumber) + "\n");
            } catch (InputMismatchException e) {
                scan.nextLine(); // clear the invalid input
            }

            System.out.println("Address: ");
            address = scan.nextLine();
            bw.write(address + "\n");

            System.out.println("City: ");
            city = scan.nextLine();
            bw.write(city + "\n");

            System.out.println("State: ");
            state = scan.nextLine();
            bw.write(state + "\n");

            System.out.println("Zipcode: ");
            zipcode = scan.nextInt();
            scan.nextLine(); // consume the newline character
            bw.write(String.valueOf(zipcode));

            scan.close();
            bw.close();

        } catch (IOException exc) {
            System.out.println("Error writing to customer file: " + exc.getMessage());
        }
    }

    public void customerLogin() {
        File loginFile = new File("src/main/resources/login.txt");
        try {
            // Ensure the directory exists
            loginFile.getParentFile().mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(loginFile, true));
            Scanner sc = new Scanner(System.in);

            bw.newLine();
            System.out.println("Enter username:");
            custUser = sc.nextLine();
            bw.write(custUser + "\n");
            System.out.println("Enter password:");
            custPass = sc.nextLine();
            bw.write(custPass + "\n");

            sc.close();
            bw.close();
        } catch (IOException exc) {
            System.out.println("Error writing to login file: " + exc.getMessage());
        }
    }

    public void loginCheck() {
        File loginFile = new File("src/main/resources/login.txt");
        try (Scanner fileScanner = new Scanner(loginFile)) {
            boolean loginSuccessful = false;
    
            System.out.println("Please enter your username and password for login check:");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter username: ");
            String inputUsername = sc.nextLine();
            System.out.print("Enter password: ");
            String inputPassword = sc.nextLine();
    
            while (fileScanner.hasNextLine()) {
                String name = fileScanner.nextLine().trim();
                if (name.isEmpty()) continue; // Skip empty lines
    
                if (fileScanner.hasNextLine()) {
                    String pass = fileScanner.nextLine().trim();
                    if (pass.isEmpty()) continue; // Skip empty lines
    
                    // Display both the username and password being checked
                    System.out.println("Checking against username: " + name + ", password: " + pass);
    
                    if (name.equals(inputUsername) && pass.equals(inputPassword)) {
                        System.out.println("Login successful.");
                        loginSuccessful = true;
                        break;
                    }
                } else {
                    System.out.println("Login file is malformed or incomplete.");
                }
            }
    
            if (!loginSuccessful) {
                System.out.println("Incorrect login info provided.");
            }
    
            sc.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
    
    public void adminInfo() {
        File adminFile = new File("src/main/resources/admin.txt");
        try {
            // Ensure the directory exists (if not, create it)
            adminFile.getParentFile().mkdirs();

            BufferedWriter bw = new BufferedWriter(new FileWriter(adminFile, true));
            Scanner scan = new Scanner(System.in);

            bw.newLine();

            System.out.println("Enter Admin Username:");
            custUser = scan.nextLine();
            bw.write(custUser + "\n");

            System.out.println("Enter Admin Password:");
            custPass = scan.nextLine();
            bw.write(custPass + "\n");

            scan.close();
            bw.close();

            System.out.println("Admin credentials created successfully.");

        } catch (IOException exc) {
            System.out.println("Error writing to admin file: " + exc.getMessage());
        }
    }
    
    public void admin() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();
    
        File adminFile = new File("src/main/resources/admin.txt");
        boolean loginSuccessful = false;
    
        try (Scanner ad = new Scanner(adminFile)) {
            while (ad.hasNextLine()) {
                String name = ad.nextLine().trim();
                if (name.isEmpty()) continue; // Skip empty lines
                
                if (ad.hasNextLine()) {
                    String pass = ad.nextLine().trim();
                    if (pass.isEmpty()) continue; // Skip empty lines
                    
                    if (username.equals(name) && password.equals(pass)) {
                        System.out.println("Correct");
                        loginSuccessful = true;
                        break;
                    }
                } else {
                    System.out.println("File format error: missing password for the last username.");
                }
            }
        } catch (IOException e) {
            System.out.println("File not found or another error occurred: " + e.getMessage());
        } finally {
            if (!loginSuccessful) {
                System.out.println("Incorrect login info provided.");
            }
            sc.close();
        }
    }
    
}
