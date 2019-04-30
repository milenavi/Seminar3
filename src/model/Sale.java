// Java code implementing the Sale class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.Sale;
import se.kth.iv1350.POSsys.model.ItemDTO;
import se.kth.iv1350.POSsys.integration.ItemRegistry;
import se.kth.iv1350.POSsys.model.Discount;
import se.kth.iv1350.POSsys.integration.Receipt;
import se.kth.iv1350.POSsys.integration.CashPayment;
import se.kth.iv1350.POSsys.integration.Printer;

/**
 * Represents one particular sale transaction, where one
 * particular item is purchased by one particular customer.
 */
public class Sale
{
    private ItemDTO salesLineItem;
    private ItemDTO item;
    private ItemRegistry itemRegistry;
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
    public void getItemForSale()
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
     */
    public void printReceipt(Printer printer)
    {
        receipt = new Receipt(this);
        printer.printReceipt(receipt);
    }
    
    /**
     * Returns a receipt for the current sale item.
     */
    public Receipt getReceipt()
    {
        return new Receipt(this);
    }
    
    /**
     * Returns a payment for the current sale item.
     */
    public void getPayment()
    {
        return CashPayment payment;
    }
}
