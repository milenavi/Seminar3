package View;

import Controller1.Controller;
import Model.ItemDTO;
import Model.Amount;

/**
 * This program has no view, instead, this class is a placeholder
 * for the entire view.
 */
public class View
{
    private final Controller contr;
    private ItemDTO item;
    
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
        Amount amount = new Amount(2);
        double twoItems = amount.getAmount();
        System.out.println(contr.recordItem(1004021, new Amount(2), twoItems));
        
        ItemDTO invalidItem = new ItemDTO(2004022, new Amount(2), new Amount(0));

        ItemDTO validItem;
        validItem = new ItemDTO(3004024, new Amount(8), new Amount(10));

        ItemDTO recordedItem = contr.recordItem(invalidItem);
        System.out.println("Result of recording an invalid item: " + recordedItem);

        recordedItem = contr.recordItem(validItem);
        System.out.println("Result of recording a valid item: " + recordedItem);

        Amount paidAmount = new Amount(250);
        System.out.println("Cashier enters the paid amount from the customer inside the program. ");
        contr.initiatePayment(paidAmount);
    }
}


