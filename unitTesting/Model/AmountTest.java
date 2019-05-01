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
public class AmountTest 
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetAmount()
    {
        System.out.println("getAmount");
        Amount instance = null;
        int expResult = 0;
        int result = instance.getAmount();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToString()
    {
        System.out.println("toString");
        Amount instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testMinus() 
    {
        int amountOfItem1 = 10;
        int amountOfItem2 = 3;
        Amount item1 = new Amount(amountOfItem1);
        Amount item2 = new Amount(amountOfItem2);
        Amount expResult = new Amount(amountOfItem1 - amountOfItem2);
        Amount result = item1.minus(item2);
        assertEquals("Wrong subtraction result", expResult, result);
    }
    
    @Test
    public void testMinusNegResult()
    {
        int amountOfItem1 = 3;
        int amountOfItem2 = 10;
        Amount item1 = new Amount(amountOfItem1);
        Amount item2 = new Amount(amountOfItem2);
        Amount expResult = new Amount(amountOfItem1 - amountOfItem2);
        Amount result = item1.minus(item2);
        assertEquals("Wrong subtraction result", expResult, result);
    }
    
    @Test
    public void testMinusZeroResultNegItem()
    {
        int amountOfItem1 = -3;
        int amountOfItem2 = -3;
        Amount item1 = new Amount(amountOfItem1);
        Amount item2 = new Amount(amountOfItem2);
        Amount expResult = new Amount(amountOfItem1 - amountOfItem2);
        Amount result = item1.minus(item2);
        assertEquals("Wrong subtraction result", expResult, result);
    }

    @Test
    public void testPlus()
    {
        int amountOfItem1 = 10;
        int amountOfItem2 = 3;
        Amount item1 = new Amount(amountOfItem1);
        Amount item2 = new Amount(amountOfItem2);
        Amount expResult = new Amount(amountOfItem1 + amountOfItem2);
        Amount result = item1.plus(item2);
        assertEquals("Correct addition result", expResult, result);
    }
    
    @Test
    public void testPlusZeroResultNegItem()
    {
        int amountOfItem1 = -3;
        int amountOfItem2 = 3;
        Amount item1 = new Amount(amountOfItem1);
        Amount item2 = new Amount(amountOfItem2);
        Amount expResult = new Amount(amountOfItem1 + amountOfItem2);
        Amount result = item1.plus(item2);
        assertEquals("Wrong addition result", expResult, result);
    }
    
    @Test
    public void testPlusResultNegItem()
    {
        int amountOfItem1 = -3;
        int amountOfItem2 = -3;
        Amount item1 = new Amount(amountOfItem1);
        Amount item2 = new Amount(amountOfItem2);
        Amount expResult = new Amount(amountOfItem1 + amountOfItem2);
        Amount result = item1.plus(item2);
        assertEquals("Wrong addition result", expResult, result);
    }
    
    @Test
    public void testToStringPosAmt()
    {
        int representedAmt = 10;
        Amount amount = new Amount(representedAmt);
        String expResult = Integer.toString(representedAmt);
        String result = amount.toString();
        assertEquals("Wrong string returned by toString", expResult, result);
    }
    
    @Test
    public void testToStringNegAmt()
    {
        int representedAmt = -10;
        Amount amount = new Amount(representedAmt);
        String expResult = Integer.toString(representedAmt);
        String result = amount.toString();
        assertEquals("Wrong string returned by toString", expResult, result);
    }
    
    @Test
    public void testToStringZeroAmt()
    {
        int representedAmt = 0;
        Amount amount = new Amount(representedAmt);
        String expResult = Integer.toString(representedAmt);
        String result = amount.toString();
        assertEquals("Wrong string returned by toString", expResult, result);
    }
}
