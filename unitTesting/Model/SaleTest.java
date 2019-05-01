/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Integration.Printer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena
 */
public class SaleTest
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddSalesLineItem()
    {
        System.out.println("addSalesLineItem");
        ItemDTO salesLineItem = null;
        Sale instance = null;
        instance.addSalesLineItem(salesLineItem);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetItemForSale() 
    {
        System.out.println("setItemForSale");
        ItemDTO itemForSale = null;
        Sale instance = null;
        instance.setItemForSale(itemForSale);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetItemForSale()
    {
        System.out.println("getItemForSale");
        Sale instance = null;
        ItemDTO expResult = null;
        ItemDTO result = instance.getItemForSale();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSaveDiscount()
    {
        System.out.println("saveDiscount");
        Discount discount = null;
        Sale instance = null;
        instance.saveDiscount(discount);
        fail("The test case is a prototype.");
    }

    @Test
    public void testInitiatePayment() 
    {
        System.out.println("initiatePayment");
        CashPayment payment = null;
        Sale instance = null;
        instance.initiatePayment(payment);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrintReceipt()
    {
        System.out.println("printReceipt");
        Printer printer = null;
        Sale instance = null;
        instance.printReceipt(printer);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetReceipt() 
    {
        System.out.println("getReceipt");
        Sale instance = null;
        Receipt expResult = null;
        Receipt result = instance.getReceipt();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPayment() 
    {
        System.out.println("getPayment");
        Sale instance = null;
        CashPayment expResult = null;
        CashPayment result = instance.getPayment();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
