/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena
 */
public class CashRegisterTest
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetBalance()
    {
        System.out.println("getBalance");
        CashRegister instance = new CashRegister();
        Amount expResult = null;
        Amount result = instance.getBalance();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddPayment() 
    {
        CashRegister instance = new CashRegister();
        double price = 9.99;
        Amount amount = new Amount(2);
        int itemID = 1004021;
        ItemDTO itemInformation = new ItemDTO(itemID, amount, price);
        Amount paidAmount = new Amount(10);
        CashPayment payment = new CashPayment(paidAmount);
        instance.addPayment(payment);
        Amount expectedResult = new Amount(0).plus(payment.getTotalPrice());
        Amount actualResult = instance.getBalance();
        assertEquals("The balance is not the same as the total price for another instance.", expectedResult, actualResult);
    }
}
