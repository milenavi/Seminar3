/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import Model.Receipt;

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
