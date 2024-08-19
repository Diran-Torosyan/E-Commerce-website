package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * UserTest class to test the functionality of the User class.
 * 
 * <p>This class includes tests for user login, admin login, and customer info.</p>
 */
public class UserTest {

    @Test
    public void testCustomerLogin() {
        User user = new User();
        
        user.loadCustomerData("jackray3111@gmail.com");

        System.out.println("Expected First name: Jack, Actual: " + user.getFirstName());
        System.out.println("Expected Last name: Ray, Actual: " + user.getLastName());

        assertEquals("Jack", user.getFirstName(), "First name should match");
        assertEquals("Ray", user.getLastName(), "Last name should match");

        System.out.println("Test passed: Customer data was loaded successfully.");
    }

    @Test
    public void testAdminLogin() {
        User user = new User();
        
        boolean loginSuccess = user.adminLoginCheck("testadmin", "hope");

        System.out.println("Admin login success value: " + loginSuccess);

        assertTrue(loginSuccess, "Admin login should be successful");

        if (loginSuccess) {
            System.out.println("Test passed: Admin login was successful.");
        }
    }

    @Test
    public void testUserFunctionality() {
        User user = new User();
        user.setFirstName("Tim");
        user.setLastName("Allen");
        user.setEmail("shaggydog@gmail.com");
        user.setAddress("123 Tim's Brewhouse");
        user.setCity("Brea");
        user.setState("CA");
        user.setZipcode(92832);

        System.out.println("Expected First Name: Tim, Actual First Name: " + user.getFirstName());
        System.out.println("Expected Last Name: Allen, Actual Last Name: " + user.getLastName());
        System.out.println("Expected Email: shaggydog@gmail.com, Actual Email: " + user.getEmail());
        System.out.println("Expected Address: 123 Tim's Brewhouse, Actual Address: " + user.getAddress());
        System.out.println("Expected City: Brea, Actual City: " + user.getCity());
        System.out.println("Expected State: CA, Actual State: " + user.getState());
        System.out.println("Expected Zip Code: 92832, Actual Zip Code: " + user.getZipcode());

        assertEquals("Tim", user.getFirstName(), "First name should match.");
        assertEquals("Allen", user.getLastName(), "Last name should match.");
        assertEquals("shaggydog@gmail.com", user.getEmail(), "Email should match.");
        assertEquals("123 Tim's Brewhouse", user.getAddress(), "Address should match.");
        assertEquals("Brea", user.getCity(), "City should match.");
        assertEquals("CA", user.getState(), "State should match.");
        assertEquals(92832, user.getZipcode(), "Zip code should match.");

        System.out.println("Test passed: User functionality works as expected.");
    }

    @Test
    public void testLoadCustomerData() {
        User user = new User();
        
        // Simulate loading customer data
        user.loadCustomerData("jackray3111@gmail.com");

        System.out.println("Expected First Name: Jack, Actual First Name: " + user.getFirstName());
        System.out.println("Expected Last Name: Ray, Actual Last Name: " + user.getLastName());
        System.out.println("Expected Email: jackray3111@gmail.com, Actual Email: " + user.getEmail());
        System.out.println("Expected Address: 814 West Fern Drive, Actual Address: " + user.getAddress());
        System.out.println("Expected City: Fullerton, Actual City: " + user.getCity());
        System.out.println("Expected State: CA, Actual State: " + user.getState());
        System.out.println("Expected Zip Code: 92832, Actual Zip Code: " + user.getZipcode());

        // Verify that the correct data was loaded
        assertEquals("Jack", user.getFirstName(), "First name should match");
        assertEquals("Ray", user.getLastName(), "Last name should match");
        assertEquals("jackray3111@gmail.com", user.getEmail(), "Email should match");
        assertEquals("814 West Fern Drive", user.getAddress(), "Address should match");
        assertEquals("Fullerton", user.getCity(), "City should match");
        assertEquals("CA", user.getState(), "State should match");
        assertEquals(92832, user.getZipcode(), "Zip code should match");

        System.out.println("Test passed: Customer data was loaded successfully.");
    }

    @Test
    public void testLoadCustomerDataEmailNotFound() {
        User user = new User();

        // Simulate loading customer data with a non-existent email
        user.loadCustomerData("nonexistentemail@example.com");

        // Verify that no data was loaded
        assertEquals(0, user.getCustNum(), "Customer ID should be 0 for non-existing email");
        assertNull(user.getFirstName(), "First name should be null for non-existing email");
        assertNull(user.getLastName(), "Last name should be null for non-existing email");
        assertNull(user.getEmail(), "Email should be null for non-existing email");
        assertNull(user.getAddress(), "Address should be null for non-existing email");
        assertNull(user.getCity(), "City should be null for non-existing email");
        assertNull(user.getState(), "State should be null for non-existing email");
        assertEquals(0, user.getZipcode(), "Zipcode should be 0 for non-existing email");

        System.out.println("Test passed: No customer data was loaded for non-existent email.");
    }
}
