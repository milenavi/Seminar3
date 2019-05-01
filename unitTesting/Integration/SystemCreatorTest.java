/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena
 */
public class SystemCreatorTest 
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetAccountingSystem() 
    {
        System.out.println("getAccountingSystem");
        SystemCreator instance = new SystemCreator();
        AccountingSystem expResult = null;
        AccountingSystem result = instance.getAccountingSystem();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetInventorySystem()
    {
        System.out.println("getInventorySystem");
        SystemCreator instance = new SystemCreator();
        InventorySystem expResult = null;
        InventorySystem result = instance.getInventorySystem();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
