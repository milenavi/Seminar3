/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import Model.CustomerDTO;
import Model.Discount;
import Model.ItemDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena
 */
public class DiscountRegistryTest 
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetPriceAfterDiscount() throws Exception 
    {
        System.out.println("getPriceAfterDiscount");
        CustomerDTO customer = null;
        DiscountRegistry instance = new DiscountRegistry();
        Discount expResult = null;
        Discount result = instance.getPriceAfterDiscount(customer);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculatePriceWithDiscount()
    {
        double price = 9.99;
        Amount amount = new Amount(2);
        int itemId = 1004021;
        ItemDTO item = new ItemDTO(itemID, amount, price);
        DiscountRegistry instance = new DiscountRegistry();
        Amount expectedResult = price.multiply(amount);
        Amount actualResult = instance.calculatePriceWithDiscount(item);
        assertEquals("Discount price is not the same.", expectedResult, actualResult);

    }
    
}
