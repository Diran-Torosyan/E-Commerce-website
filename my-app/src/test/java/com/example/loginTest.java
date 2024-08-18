package com.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class loginTest {

    private User user;
    private loginPage login;

    @Before
    public void setup() {
        user = new User();
        login = new loginPage(null);
    }

    @Test
    public void testValidUserAuthentication() {
        assertTrue(user.loginCheck("ww@gmail.com", "BlueSky"));
    }

    @Test
    public void testInvalidUserAuthentication() {
        assertFalse(user.loginCheck("vw@gmail.com", "Beetle"));
    }

    @Test
    public void testSuccessfulLogin() {
        assertEquals("Login Successful", login.handleLogin());
    }

    @Test
    public void testFailedLogin() {
        assertEquals("Invalid Credentials", login.handleLogin());
    }

    @Test
    public void testValidAdminAuthentication() {
        
    }

    @Test
    public void testInvalidAdminAuthentication() {

    }

    @Test
    public void testSuccessfulAdminLogin() {
        assertEquals("Admin Login Successful", login.handleAdminLogin());
    }

    @Test
    public void testFailedAdminLogin() {
        assertEquals("Invalid Admin Credentials", login.handleAdminLogin());
    }



}
