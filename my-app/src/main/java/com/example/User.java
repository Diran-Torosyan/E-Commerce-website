package com.example;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Provides functionality for managing user information including creating new users, logging in, and handling admin details.
 * 
 * <p>This class handles reading and writing user data to text files, managing customer and admin information, and verifying login credentials.</p>
 * 
 * @author Brent Eusala
 */
public class User {

    private int custNum = 0;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String state;
    private int zipcode;
    private String custUser;
    private String custPass;

    public User() {}

    /**
     * Sets the first name of the user.
     * 
     * @param firstName the first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the user.
     * 
     * @param lastName the last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the email of the user.
     * 
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the address of the user.
     * 
     * @param address the address of the user
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the city of the user.
     * 
     * @param city the city of the user
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state of the user.
     * 
     * @param state the state of the user
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets the zipcode of the user.
     * 
     * @param zipcode the zipcode of the user
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    public int getCustNum() {
        return custNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipcode() {
        return zipcode;
    }
        /**
     * Loads the customer data from customer.txt based on the provided email.
     * 
     * @param email the email of the customer to look up
     */
    public void loadCustomerData(String email) {
        File customerFile = new File("src/main/resources/customer.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(customerFile)))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); 
                int customerId = Integer.parseInt(line);  
                
                String firstName = br.readLine().trim(); 
                String lastName = br.readLine().trim();   
                String customerEmail = br.readLine().trim();  
    
                if (customerEmail.equals(email)) {
                    this.custNum = customerId;
                    this.firstName = firstName;
                    this.lastName = lastName;
                    this.email = customerEmail;
                    this.address = br.readLine().trim();   
                    this.city = br.readLine().trim();      
                    this.state = br.readLine().trim();      
                    this.zipcode = Integer.parseInt(br.readLine().trim()); 
                    break; 
                }
                for (int i = 0; i < 4; i++) {
                    br.readLine(); 
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }
    }
    
    

    /**
     * Retrieves the last customer ID from the customer file.
     * 
     * <p>This method reads the customer file and determines the last used customer ID.
     * IDs are expected to be between 1 and 999, and zip codes are identified as numbers greater than 999.</p>
     * 
     * @return the last customer ID
     */
    private int getLastCustomerId() {
        int lastId = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/customer.txt")))) {
            String line;
            boolean expectingId = true;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        int number = Integer.parseInt(line);

                        if (expectingId && number >= 1 && number <= 999 && number > lastId) {
                            lastId = number;
                            expectingId = false;
                        } else if (number > 999) {
                            expectingId = false;
                        }

                    } catch (NumberFormatException e) {
                    }

                    expectingId = !expectingId;  
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file: " + e.getMessage());
        }
        return lastId;
    }

    /**
     * Collects customer information and writes it to the customer file.
     * 
     * <p>This method prompts the user for their information and writes the details to the customer file.</p>
     */
    public void customerInfo() {
        File customerFile = new File("src/main/resources/customer.txt");
        try {
            customerFile.getParentFile().mkdirs();
    
            boolean isFileEmpty = customerFile.length() == 0;
    
            BufferedWriter bw = new BufferedWriter(new FileWriter(customerFile, true));
    
            if (!isFileEmpty) {
                bw.newLine(); 
            }
    
            custNum = getLastCustomerId() + 1; 
            bw.write(custNum + "\n");
            bw.write(firstName + "\n");
            bw.write(lastName + "\n");
            bw.write(email + "\n");
            bw.write(address + "\n");
            bw.write(city + "\n");
            bw.write(state + "\n");
            bw.write(String.valueOf(zipcode));
    
            bw.close();
    
        } catch (IOException exc) {
            System.out.println("Error writing to customer file: " + exc.getMessage());
        }
    }
    
    /**
     * Collects login credentials and writes them to the login file.
     * 
     * <p>This method writes the user's email and password to login.txt.</p>
     * 
     * @param email the user's email
     * @param password the user's password
     */
    public void customerLogin(String email, String password) {
        File loginFile = new File("src/main/resources/login.txt");
        try {
            loginFile.getParentFile().mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(loginFile, true));

            bw.newLine();
            bw.write(email + "\n");
            bw.write(password + "\n");
            bw.close();
        } catch (IOException exc) {
            System.out.println("Error writing to login file: " + exc.getMessage());
        }
    }
    /**
     * checks for correct login
     */
    public boolean loginCheck(String inputUsername, String inputPassword) {
        File loginFile = new File("src/main/resources/login.txt");
        try (Scanner fileScanner = new Scanner(loginFile)) {
            while (fileScanner.hasNextLine()) {
                String name = fileScanner.nextLine().trim();
                if (name.isEmpty()) continue; 

                if (fileScanner.hasNextLine()) {
                    String pass = fileScanner.nextLine().trim();
                    if (pass.isEmpty()) continue; 

                    if (name.equals(inputUsername) && pass.equals(inputPassword)) {
                        return true;
                    }
                } else {
                    System.out.println("Login file is malformed or incomplete.");
                }
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }

        return false; 
    }
    /**
     * checks for admin login
     */
    public void adminInfo() {
        File adminFile = new File("src/main/resources/admin.txt");
        try {
           
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
    /**
     * for admin login to make sure it is correct
     */
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
                if (name.isEmpty()) continue;
                
                if (ad.hasNextLine()) {
                    String pass = ad.nextLine().trim();
                    if (pass.isEmpty()) continue; 
                    
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
