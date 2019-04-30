// Java code implementing the Printer class:
package se.kth.iv1350.POSsys.integration;

import se.kth.iv1350.POSsys.model.Receipt;

/**
 * The printer is used for all printouts of receipts.
 */
public class Printer
{
    /**
     * Creates an instance represented as a printer.
     */
    public Printer() {
        
    }
    
    /**
     * Prints the specified receipt.
     *
     * @param receipt The specified receipt that shall be printed.
     */
    public void printReceipt(Receipt receipt)
    {
        System.out.println(receipt.toString());
    }
}
