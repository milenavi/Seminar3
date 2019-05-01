package View;

import Controller.Controller;
import Integration.ItemDTO;
import Model.Amount;

/**
 * This program has no view, instead, this class is a placeholder
 * for the entire view.
 */
public class View
{
    private final Controller contr;
    private final Amount twoAmountOfItems = new Amount(2);
    private final Amount oneAmountOfItems = new Amount(1);
    private final Amount eightAmountOfItems = new Amount(8);
    
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
     * Starts a new sale.
     */
    public void runFakeSale()
    {
        System.out.println("New sale is started.\n");
        contr.startNewSale();
    }
    
    /**
     * Simulates a user input that generates calls to all
     * system operations.
     */
    public void sampleExecution()
    {
        System.out.println("Item is recorded. \n");
        System.out.println(contr.recordItem(1004021, twoAmountOfItems, 9.99));
        
        ItemDTO invalidItem = new ItemDTO(2004022, oneAmountOfItems, 0.0);

        ItemDTO validItem;
        validItem = new ItemDTO(3004024, eightAmountOfItems, 14.99);

        ItemDTO recordedItem = contr.recordItem(invalidItem);
        System.out.println("Result of recording an invalid item: " + recordedItem);

        recordedItem = contr.recordItem(validItem);
        System.out.println("Result of recording a valid item: " + recordedItem);

        Amount paidAmount = new Amount(250);
        System.out.println("Cashier enters the paid amount from the customer inside the program. " + contr.initiatePayment(paidAmount));
    }
}


