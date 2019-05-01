/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import Model.Receipt;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena
 */
public class PrinterTest 
{
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    
    @Before
    public void setUpStreams()
    {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    
    @After
    public void cleanUpStreams()
    {
        outContent = null;
        System.setOut(originalSysOut);
    }
    
    @Test
    public void testCreateReceiptString()
    {
        int id = 1004021;
        Amount amount = new Amount(2);
        double price = 9.99;
        ItemDTO recordedItem = new ItemDTO(id, amount, price);
        Amount paidAmt = new Amount(10);
        CashPayment payment = new CashPayment(paidAmt);
        Sale paidSale = new Sale(null, new RegistryCreator().getItemRegistry());
        paidSale.setItemForSale(recordedItem);
        paidSale.initiatePayment(payment);
        Receipt receipt = new Receipt(paidSale);
        Printer instance = new Printer();
        instance.printReceipt(receipt);
        LocalDateTime saleTime = LocalDateTime.now();
        String expResult = "\n\nRecorded item: " + id + "\nPrice: " + price + "\nChange: " + paidAmt.minus(price) + "\n\n\n";
        String result = outContent.toString();
        assertTrue("Wrong printout.", result.contains(expResult));
        assertTrue("Wrong sale year.", result.contains(Integer.toString(saleTime.getYear())));
        assertTrue("Wrong sale month.",result.contains(Integer.toString(saleTime.getMonthValue())));
        assertTrue("Wrong sale day.",result.contains(Integer.toString(saleTime.getDayOfMonth())));
        assertTrue("Wrong sale hour.", result.contains(Integer.toString(saleTime.getHour())));
        assertTrue("Wrong sale minute.", result.contains(Integer.toString(saleTime.getMinute())));
    }

    @Test
    public void testPrintReceipt()
    {
        System.out.println("printReceipt");
        Receipt receipt = null;
        Printer instance = new Printer();
        instance.printReceipt(receipt);
        fail("The test case is a prototype.");
    }
    
}
