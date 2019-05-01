/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Amount;
import Model.ItemDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena
 */
public class ControllerTest 
{
    private Controller instance;
    private RegistryCreator regCreator;
    private SystemCreator sysCreator;
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;
    
    @Before
    public void setUp()
    {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Printer printer = new Printer();
        regCreator = new RegistryCreator();
        sysCreator = new SystemCreator();
        instance = new Controller(regCreator, sysCreator, printer);
    }
    
    @After
    public void tearDown()
    {
        outContent = null;
        System.setOut(originalSysOut);
        instance = null;
        regCreator = null;
        sysCreator = null;
    }

    @Test
    public void testStartNewSale() 
    {
        System.out.println("startNewSale");
        Controller instance = null;
        instance.startNewSale();
        fail("The test case is a prototype.");
    }

    @Test
    public void testRecordItem() 
    {
        instance.startNewSale();
        int itemID = 1004021;
        double price = 9.99;
        Amount amount = new Amount(2);
        ItemDTO actualResult = instance.recordItem(itemID, amount, price);
        ItemDTO expectedResult = "item id: " + itemID + "\t" +
        "price: " + price + "\t" + "amount of items: " + amount + ", running total: " + price;
        assertEquals("ItemDTO from recordItem is not the same as ItemDTO with expected result.", expectedResult, actualResult);
    }

    @Test
    public void testIncreaseAmountSoldItem() 
    {
        instance.startNewSale();
        int itemID = 1004021;
        double price = 9.99;
        Amount amount = new Amount(2);
        ItemDTO soldItem = instance.recordItem(itemID, amount, price);
        ItemDTO actualResult = instance.increaseAmountSoldItem(soldItem);
        ItemDTO expectedResult = "item id: " + itemID + "\t" +
        "price: " + price + "\t" + "amount of items: " + amount + ", increased item: " + solditem;
        assertEquals("ItemDTO from soldItem is not the same as ItemDTO with expected result.", expectedResult, actualResult);
    }

    @Test
    public void testRecordItemWithDiscount()
    {
        int customerID = 198705126857;
        int id = 1004021;
        CustomerDTO requestingCustomer = new CustomerDTO(customerID);
        instance.recordItemWithDiscount(requestingCustomer);
        List<Discount> savedDiscounts = regCreator.getDiscountRegistry().findDiscountByCustomerID(customerID);
        int expectedNoOfStoredDiscounts = 1;
        int noOfStoredDiscounts = savedDiscounts.size();
        assertEquals("Wrong number of stored discounts.", expectedNoOfStoredDiscounts, noOfStoredDiscounts);
        Discount savedDiscount = savedDiscounts.get(0);
        Amount paidAmt = new Amount(10);
        CashPayment payment = new CashPayment(paidAmt);
        savedDiscount.initiatePayment(payment);
        savedDiscount.printReceipt(new Printer());
        String result = outContent.toString();
        assertTrue("Saved discount does not contain recorded item", result.contains(id));
    }

    @Test
    public void testEndSale() 
    {
        boolean expectedResult = true;
        Sale sale = null;
        boolean actualResult = sale.endSale();
        assertEquals("The sale is ended.", expectedResult, actualResult);
    }

    @Test
    public void testInitiatePayment()
    {
        instance.startNewSale();
        int itemID = 1004021;
        double price = 9.99;
        Amount amount = new Amount(2);
        instance.recordItem(itemID, amount, price);
        Amount paidAmount = new Amount(10);
        String expectedResult = "Change: " + paidAmount.minus(price);
        String actualResult = instance.initiatePayment(paidAmount);
        assertEquals("Change is not equal to the paid amount.", expectedResult, actualResult);
    }
    
}
