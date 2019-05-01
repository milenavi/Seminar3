package Controller;

import Integration.ItemRegistry;
import Integration.Printer;
import Integration.RegistryCreator;
import Integration.SaleRegistry;
import Integration.DiscountRegistry;
import Integration.SystemCreator;
import Integration.InventorySystem;
import Integration.AccountingSystem;
import Model.CashRegister;
import Model.Amount;
import Model.CashPayment;
import Model.Sale;
import Model.Receipt;
import Model.ItemDTO;
import Model.CustomerDTO;

/**
 * This is the application’s only controller class. All calls to the
 * model pass through here.
 */
public class Controller
{
    private Sale sale;
    private final DiscountRegistry discountRegistry;
    private final ItemRegistry itemRegistry;
    private final SaleRegistry saleRegistry;
    private final Printer printer;
    private final CashRegister cashRegister;
    private final AccountingSystem accountingSystem;
    private final InventorySystem inventorySystem;
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
        this.item = item;
    }
    
    /**
     *  Initiates a new sale.
     */
    public void startNewSale()
    {
        this.sale = new Sale(item, itemRegistry);
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
     * @throws java.lang.ArgumentException
     */
    public void recordItemWithDiscount(CustomerDTO customer) 
            throws ArgumentException
    {
        double discount = discountRegistry.getPriceAfterDiscount(customer);
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
        //inventorySystem.updateInventory();
        //accountingSystem.updateAccounting();
        Receipt receipt = sale.getReceipt();
        printer.printReceipt(receipt);
        System.out.println("Change after payment: " + payment.getChange().toString());
    }

    public boolean recordItem(int i, Amount amount, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


