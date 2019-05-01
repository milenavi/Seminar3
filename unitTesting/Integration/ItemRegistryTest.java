/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import Model.ItemDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena
 */
public class ItemRegistryTest 
{
    private ItemRegistry instance;
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;
    
    @Before
    public void setUp()
    {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        instance = new ItemRegistry();
    }
    
    @After
    public void tearDown()
    {
        outContent = null;
        System.setOut(originalSysOut);
        instance = null;
    }

    @Test
    public void testGetItemInformation() 
    {
        System.out.println("getItemInformation");
        ItemDTO recordedItem = null;
        ItemRegistry instance = new ItemRegistry();
        ItemDTO expResult = null;
        ItemDTO result = instance.getItemInformation(recordedItem);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetIncreasedAmountOfSoldItem() 
    {
        System.out.println("setIncreasedAmountOfSoldItem");
        ItemDTO recordedItem = null;
        ItemRegistry instance = new ItemRegistry();
        instance.setIncreasedAmountOfSoldItem(recordedItem);
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testInvalidItem()
    {
        int itemID = 1004020;
        double price = 0;
        Amount amount = null;
        ItemDTO recordedItem = new ItemDTO(itemID, amount, price);
        instance.recordItem(recordedItem);
        List<ItemDTO> validItemID = instance.findItemByItemID(itemID);
        int expectedItemID = 1004021;
        assertEquals("The recorded item has invalid id.", expectedItemID, validItemID);
        String result = outContent.toString();
        assertTrue("Invalid item id ", result.contains(itemID));
    }
    
    @Test
    public void testValidItem()
    {
        int itemID = 1004021;
        double price = 0;
        Amount amount = null;
        ItemDTO recordedItem = new ItemDTO(itemID, amount, price);
        instance.recordItem(recordedItem);
        List<ItemDTO> validItemID = instance.findItemByItemID(itemID);
        int expectedItemID = 1004021;
        assertEquals("The recorded item has invalid id.", expectedItemID, validItemID);
        String result = outContent.toString();
        assertTrue("Invalid item id ", result.contains(itemID));
    }
}
