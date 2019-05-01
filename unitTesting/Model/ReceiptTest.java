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
public class ReceiptTest
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateReceiptString() 
    {
        double price = 9.99;
        Amount amount = new Amount(2);
        int itemID = 1004021;
        ItemDTO itemInformation = new ItemDTO(itemID, amount, price);
        Sale sale = new Sale();
        Receipt instance = new Receipt(sale);
        LocalDateTime saleTime = LocalDateTime.now();
        String expectedResult = "Receipt: " + "\nSale time: " + saleTime.toLocalDate().toString() + "\nItems: " + "\nitem id: " + itemID + "\t" + "price: " + price + "\t" + "amount of items: " + amount + "\t" + "\n Running Total: " + price + "\n";
        String actualResult = instance.toString();
        assertEquals("Receipt string is presented.", expectedResult, actualResult);
    }
}
