package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E-Commerce-website/E-Commerce-website/mavenConversion/my-app/src/main/resources/customer.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    lastId = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    // This line is not a number, continue reading
                }
                // Skip the remaining lines of the current customer entry
                for (int i = 0; i < 8; i++) {
                    if (br.readLine() == null) break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }
        return lastId;
    }

    public void customerInfo() {
        File customerFile = new File("E-Commerce-website/E-Commerce-website/mavenConversion/my-app/src/main/resources/customer.txt");
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
        File loginFile = new File("E-Commerce-website/E-Commerce-website/mavenConversion/my-app/src/main/resources/login.txt");
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
        File loginFile = new File("E-Commerce-website/E-Commerce-website/mavenConversion/my-app/src/main/resources/login.txt");
        try {
            Scanner cu = new Scanner(loginFile);
            while (cu.hasNext()) {
                String name = cu.nextLine();
                String pass = cu.nextLine();

                if ((name.equals(custUser)) && (pass.equals(custPass))) {
                    System.out.println("Login successful.");
                } else {
                    System.out.println("Incorrect login info provided.");
                }
            }
            cu.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public void admin() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();

        File adminFile = new File("E-Commerce-website/E-Commerce-website/mavenConversion/my-app/src/main/resources/admin.txt");
        try {
            Scanner ad = new Scanner(adminFile);
            while (ad.hasNext()) {
                String name = ad.nextLine();
                String pass = ad.nextLine();

                if ((username.equals(name)) && (password.equals(pass))) {
                    System.out.println("Correct");
                } else {
                    System.out.println("Incorrect login info provided.");
                }
            }
            sc.close();
            ad.close();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
}
