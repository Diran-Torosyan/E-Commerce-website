package com.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class checkoutTest {

    private CheckoutPage checkout;

    @Before
    public void setup() {
        checkout = new CheckoutPage();
    }

    @Test
    public void testValidCardNumberCheck () {
        assertTrue(checkout.isValidCreditCard("1234123412341234"));
    }

    @Test
    public void testInvalidCardNumberCheck1 () {
        assertFalse(checkout.isValidCreditCard("1234567890"));
    }

    @Test
    public void testInvalidCardNumberCheck2 () {
        assertFalse(checkout.isValidCreditCard("12345678901234567890"));
    }

    @Test
    public void testInvalidCardNumberCheck3 () {
        assertFalse(checkout.isValidCreditCard("abcdefghijklmnop"));
    }
}
