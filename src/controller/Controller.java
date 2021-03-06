// Java code implementing the Controller class:
package se.kth.iv1350.POSsys.controller;

import se.kth.iv1350.POSsys.integration.ItemRegistry;
import se.kth.iv1350.POSsys.integration.Printer;
import se.kth.iv1350.POSsys.integration.RegistryCreator;
import se.kth.iv1350.POSsys.integration.SaleRegistry;
import se.kth.iv1350.POSsys.integration.DiscountRegistry;
import se.kth.iv1350.POSsys.integration.SystemCreator;
import se.kth.iv1350.POSsys.integration.InventorySystem;
import se.kth.iv1350.POSsys.integration.AccountingSystem;
import se.kth.iv1350.POSsys.model.CashRegister;
import se.kth.iv1350.POSsys.model.Amount;
import se.kth.iv1350.POSsys.model.CashPayment;
import se.kth.iv1350.POSsys.model.Sale;
import se.kth.iv1350.POSsys.model.Discount;
import se.kth.iv1350.POSsys.model.Receipt;
import se.kth.iv1350.POSsys.model.ItemDTO;

/**
 * This is the application’s only controller class. All calls to the
 * model pass through here.
 */
public class Controller
{
    private Sale sale;
    private DiscountRegistry discountRegistry;
    private ItemRegistry itemRegistry;
    private SaleRegistry saleRegistry;
    private Printer printer;
    private CashRegister cashRegister;
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private Discount discount;
    private ItemDTO item;
    
    /**
     * Creates a new instance.
     *
     * @param regCreator Used to get all classes that handle database calls.
     *
     * @param sysCreator Used to get all classes that handle external system calls.
     *
     * @param printer    Interface to printer.
     */
    public Controller(RegistryCreator regCreator, SystemCreator sysCreator, Printer printer)
    {
        this.itemRegistry = regCreator.getItemRegistry();
        this.saleRegistry = regCreator.getSaleRegistry();
        this.discountRegistry = regCreator.getDiscountRegistry();
        this.printer = printer;
        this.cashRegister = new CashRegister();
        this.accountingSystem = sysCreator.getAccountingSystem();
        this.inventorySystem = sysCreator.getInventorySystem();
        this.sale = sale;
        this.discount = discount;
        this.item = item;
    }
    
    /**
     *  Initiates a new sale.
     */
    public void startNewSale()
    {
        this.sale = new Sale();
    }
    
    /**
     * Get the information of the recorded item..
     *
     * @param item The item where the customer is buying and the cashier is recording.
     *
     * @return The information of the recorded item.
     */
    public ItemDTO recordItem(ItemDTO item)
    {
        return itemRegistry.getItemInformation(item);
    }
    
    /**
     * Increases amount of sold items. After calling this method, the amount of items
     * will increase. This method also
     * permanently saves information about the current sale.
     *
     * @param soldItem The sold item that will be increased.
     */
    public void increaseAmountSoldItem(ItemDTO soldItem)
    {
        sale.setItemForSale(soldItem);
        sale.addSalesLineItem(soldItem);
        saleRegistry.saveSale(sale);
    }
    
    /**
     * Records an item with requested discount from the customer.
     * After calling this method, the item
     * will be discounted. This method also
     * permanently saves information about the current discount.
     *
     * @param customer The specified customer that is buying an item with requested discount.
     */
    public void recordItemWithDiscount(CustomerDTO customer)
    {
        Discount discount = discountRegistry.getPriceAfterDiscount(customer);
        sale.saveDiscount(discount);
    }
    
    /**
     * Ends a long started new sale. After calling this method, the sale
     * will be ended by the cashier. This method also
     * updates the external systems such as <code>inventorySystem</code> and <code>accountingSystem</code>.
     */
    public void endSale()
    {
        saleRegistry.setSaleComplete(sale);
        accountingSystem.updateAccountingSystem();
        inventorySystem.updateInventorySystem();
    }
    
    /**
     * Initiates a payment with the paid amount.
     * Also, the external systems will be updated.
     * This method handles sale item payment and at the end it will calculate change and
     * print the receipt.
     *
     * @param paidAmount The amount of cash paid by the customer.
     */
    public void initiatePayment(Amount paidAmount)
    {
        CashPayment payment = new CashPayment(paidAmount);
        sale.initiatePayment(payment);
        cashRegister.addPayment(payment);
        inventorySystem.updateInventory();
        accountingSystem.updateAccounting();
        Receipt receipt = sale.getReceipt();
        printer.printReceipt(receipt);
        System.out.println("Change after payment: " + payment.getChange().toString());
    }
}
