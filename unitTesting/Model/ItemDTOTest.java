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
public class ItemDTOTest 
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testMatches() 
    {
        System.out.println("matches");
        ItemDTO recorded = null;
        ItemDTO instance = null;
        boolean expResult = false;
        boolean result = instance.matches(recorded);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToString() 
    {
        Amount amount = new Amount(2);
        double price = 9.99;
        int itemID = 1004021;
        ItemDTO item = new ItemDTO(itemID, amount, price);
        String expectedString = "item id: " + itemID + "\t" + "price: " + price + "\t" + "amount of items: " + amont + "\t";
        String resultString = item.toString();
        assertEquals("item String is not the same as other String with same content.", expectedString, resultString);
    }

    @Test
    public void testGetID() 
    {
        System.out.println("getID");
        ItemDTO instance = null;
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAmount() 
    {
        System.out.println("getAmount");
        ItemDTO instance = null;
        Amount expResult = null;
        Amount result = instance.getAmount();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPrice() 
    {
        System.out.println("getPrice");
        ItemDTO instance = null;
        double expResult = 0.0;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }
    
}
