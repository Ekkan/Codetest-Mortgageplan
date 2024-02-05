package com.github.ekkan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MortgageCalculatorTest {

    private static final double DELTA = 0.01;

    @Test
    public void testStandardScenario() {
        assertEquals(536.82, MortgageCalculator.calculateMonthlyPayment(100000, 5, 30), DELTA);
    }

    @Test
    public void testZeroInterest() {
        assertEquals(277.78, MortgageCalculator.calculateMonthlyPayment(100000, 0, 30), DELTA);
    }

    @Test
    public void testZeroYears() {
        try {
            MortgageCalculator.calculateMonthlyPayment(100000, 5, 0);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Term in years must be greater than 0", e.getMessage());
        }
    }

    @Test
    public void testHighInterest() {
        assertEquals(8333.33, MortgageCalculator.calculateMonthlyPayment(100000, 100, 30), DELTA);
    }


    @Test
    public void testHighPrincipal() {
        assertEquals(53682.16, MortgageCalculator.calculateMonthlyPayment(10000000, 5, 30), 0.5);
    }


    @Test
    public void testNegativeInterest() {
        try {
            MortgageCalculator.calculateMonthlyPayment(100000, -5, 30);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Interest rate must be non-negative", e.getMessage());
        }
    }

    @Test
    public void testNegativePrincipal() {
        try {
            MortgageCalculator.calculateMonthlyPayment(-100000, 5, 30);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Loan amount must be non-negative", e.getMessage());
        }
    }

    @Test
    public void testLargeTerm() {
        assertTrue(MortgageCalculator.calculateMonthlyPayment(100000, 5, 300) > 0);
    }
}
