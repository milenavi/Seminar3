/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import Model.Sale;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena
 */
public class SaleRegistryTest 
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSaveSale()
    {
        System.out.println("saveSale");
        Sale sale = null;
        SaleRegistry instance = new SaleRegistry();
        instance.saveSale(sale);
    }

    @Test
    public void testSetSaleComplete() 
    {
        System.out.println("setSaleComplete");
        Sale sale = null;
        SaleRegistry instance = new SaleRegistry();
        instance.setSaleComplete(sale);
    }

    @Test
    public void testCompleteSale()
    {
        System.out.println("completeSale");
        SaleRegistry instance = new SaleRegistry();
        boolean expResult = false;
        boolean result = instance.completeSale();
        assertEquals(expResult, result);
    }
    
}
