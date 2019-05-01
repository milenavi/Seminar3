package Model;

import Model.Sale;
import Model.ItemDTO;
import Integration.ItemRegistry;
import Model.Discount;
import Model.Receipt;
import Model.CashPayment;
import Integration.Printer;

/**
 * Represents one particular sale transaction, where one
 * particular item is purchased by one particular customer.
 */
public class Sale
{
    private ItemDTO salesLineItem;
    private final ItemDTO item;
    private final ItemRegistry itemRegistry;
    private Sale sale;
    private ItemDTO itemForSale;
    private Discount discount;
    private CashPayment payment;
    private Receipt receipt;
    
    /**
     * Creates a new instance, representing a sale made by
     * the cashier in order to set the item for sale.
     *
     * @param item The item that will be set for sale.
     *
     * @param itemRegistry Represents an item database.
     */
    public Sale(ItemDTO item, ItemRegistry itemRegistry)
    {
        this.item = item;
        this.itemRegistry = itemRegistry;
    }
    
    /**
     * Adds a line item that is set to sale.
     *
     * @param salesLineItem The line item that is set for sale.
     */
    public void addSalesLineItem(ItemDTO salesLineItem)
    {
        this.salesLineItem = salesLineItem;
        itemRegistry.setIncreasedAmountOfSoldItem(salesLineItem);
    }
    
    /**
     * Sets the current item for sale.
     *
     * @param itemForSale The item that is currently for sale.
     */
    public void setItemForSale(ItemDTO itemForSale)
    {
        this.itemForSale = itemForSale;
    }
    
    /**
     * @return The item that is currently for sale.
     */
    public ItemDTO getItemForSale()
    {
        return itemForSale;
    }
    
    /**
     * Saves the specified discount permanently.
     *
     * @param discount The discount that will be saved.
     */
    public void saveDiscount(Discount discount)
    {
        this.discount = discount;
    }
    
    /**
     * This sale item is paid using the specified payment.
     *
     * @param payment The payment used to pay this sale item.
     */
    public void initiatePayment(CashPayment payment)
    {
        payment.calculateTotalPrice(this);
    }
    
    /**
     * Prints a receipt for the current sale item on the
     * specified printer.
     * @param printer The printer that prints the receipt.
     */
    public void printReceipt(Printer printer)
    {
        receipt = new Receipt(this);
        printer.printReceipt(receipt);
    }
    
    /**
     * Returns a receipt for the current sale item.
     * @return receipt for the purchased item.
     */
    public Receipt getReceipt()
    {
        return new Receipt(this);
    }
    
    /**
     * Returns a payment for the current sale item
     * 
     * @return payment for the current sale item
     */
    public CashPayment getPayment()
    {
        return payment;
    }

    public void saveDiscount(double discount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
