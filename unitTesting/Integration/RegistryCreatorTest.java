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
public class RegistryCreatorTest 
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetItemRegistry()
    {
        System.out.println("getItemRegistry");
        RegistryCreator instance = new RegistryCreator();
        ItemRegistry expResult = null;
        ItemRegistry result = instance.getItemRegistry();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSaleRegistry() 
    {
        System.out.println("getSaleRegistry");
        RegistryCreator instance = new RegistryCreator();
        SaleRegistry expResult = null;
        SaleRegistry result = instance.getSaleRegistry();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetDiscountRegistry() 
    {
        System.out.println("getDiscountRegistry");
        RegistryCreator instance = new RegistryCreator();
        DiscountRegistry expResult = null;
        DiscountRegistry result = instance.getDiscountRegistry();
        assertEquals(expResult, result);
    }
    
}
