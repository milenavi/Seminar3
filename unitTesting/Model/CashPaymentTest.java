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
public class CashPaymentTest
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCalculateTotalPrice()
    {
        System.out.println("calculateTotalPrice");
        Sale paidSale = null;
        CashPayment instance = null;
        instance.calculateTotalPrice(paidSale);
    }

    @Test
    public void testGetTotalPrice()
    {
        System.out.println("getTotalPrice");
        CashPayment instance = null;
        Amount expResult = null;
        Amount result = instance.getTotalPrice();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetChange() 
    {
        double price = 9.99;
        Amount amount = new Amount(2);
        int itemId = 1004021;
        ItemDTO itemInformation = new ItemDTO(itemID, amount, price);
        Amount paidAmount = new Amount(10);
        CashPayment instance = new CashPayment(paidAmount);
        Amount expectedResult = paidAmount.minus(price.multiply(amount));
        Amount actualResult = instance.getChange();
        assertEquals("Change is not the same.", expectedResult, actualResult);
    }
    
}
