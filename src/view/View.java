// Java code implementing the View class:
package se.kth.iv1350.POSsys.view;

import se.kth.iv1350.POSsys.controller.Controller;
import se.kth.iv1350.POSsys.integration.ItemDTO;

/**
 * This program has no view, instead, this class is a placeholder
 * for the entire view.
 */
public class View
{
    private Controller contr;
    
    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     *
     */
    public View(Controller contr)
    {
        this.contr = contr;
    }
    
    /**
     * Simulates a user input that generates calls to all
     * system operations.
     */
    public void sampleExecution()
    {
        System.out.println("New sale is started.\n");
        contr.startNewSale();
        System.out.println("Item is recorded. \n");
        System.out.println(contr.recordItem(99123837432, 3, 9.99));
        
        ItemDTO invalidItem = new ItemDTO(991228371023220000, 0, 0.0);

        ItemDTO validItem = new ItemDTO(99122837131, 2, 14.99);

        ItemDTO recordedItem = contr.recordItem(invalidItem);
        System.out.println("Result of recording an invalid item: " + recordedItem);

        recordedItem = contr.recordItem(validItem);
        System.out.println("Result of recording a valid item: " + recordedItem);

        Amount paidAmount = new Amount(250);
        System.out.println("Cashier enters the paid amount from the customer inside the program. " + contr.initiatePayment(paidAmount));
    }
}
