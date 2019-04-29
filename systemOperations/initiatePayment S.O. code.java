// Java code implementing the View class:
package se.kth.iv1350.POSsys.view;

import se.kth.iv1350.POSsys.controller.Controller;
import se.kth.iv1350.POSsys.model.Amount;

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
    
    public void sampleExecution()
    {
        Amount paidAmount = new Amount(250);
        System.out.println("Cashier enters the paid amount from the customer inside the program. " + contr.initiatePayment(paidAmount));
    }

}

//________________________________________________________________________________________

// Java code implementing the Sale class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.integration.Receipt;
import se.kth.iv1350.POSsys.integration.CashPayment;
import se.kth.iv1350.POSsys.integration.Printer;

/**
 * Represents one particular sale transaction, where one
 * particular item is set to sale by a cashier.
 */
public class Sale
{
    private CashPayment payment;
    private Receipt receipt;
    
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

//________________________________________________________________________________________

// Java code implementing the Receipt class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.Sale;

/**
 * The receipt of a sale
 */
public class Receipt
{
    private final Sale sale;
    
    /**
     * Creates a new instance.
     *
     * @param sale The sale proved by this receipt.
     */
    public Receipt(Sale sale)
    {
        this.sale = sale;
    }
    
    /**
     * Creates a well-formatted string with the entire content
     * of the receipt.
     *
     * @return The well-formatted receipt string.
     */
    public String createReceiptString()
    {
        StringBuilder builder = new StringBuilder();
        appendLine(builder, "Item Sale");
        endSection(builder);
        
        LocalDateTime saleTime = LocalDateTime.now();
        builder.append("Sale time: ");
        appendLine(builder, saleTime.toString());
        endSection(builder);
        
        builder.append("Item for sale: ");
        appendLine(builder, item.getItemInformation().getID());
        builder.append("Amount of items: ");
        appendLine(builder, item.getItemInformation().getAmount());
        builder.append("Price for the item: ");
        appendLine(builder, item.getItemInformation().getPrice());
        builder.append("Total price: ");
        appendLine(builder, sale.getPayment().getTotalPrice().toString());
        builder.append("Change after payment: ");
        appendLine(builder, sale.getPayment().getChange().toString());
        endSection(builder);
        
        return builder.toString();
    }
    
    private void appendLine(StringBuilder builder, String line)
    {
        builder.append(line);
        builder.append("\n");
    }
    
    private void endSection(StringBuilder builder)
    {
        builder.append("\n");
    }
}

//________________________________________________________________________________________

// Java code implementing the Main class:
package se.kth.iv1350.POSsys.startup;

import se.kth.iv1350.POSsys.controller.Controller;
import se.kth.iv1350.POSsys.integration.Printer;
import se.kth.iv1350.POSsys.integration.RegistryCreator;
import se.kth.iv1350.POSsys.view.View;
import se.kth.iv1350.POSsys.integration.SystemCreator;

/**
 * Contains the <code>main</code> method. Performs all startup of the
 * application.
 */
public class Main
{
    /**
     * Starts the application.
     *
     * @param args The application does not take any command line parameters.
     *
     */
    public static void main(String[] args)
    {
        RegistryCreator creator = new RegistryCreator();
        SystemCreator sysCreator = new SystemCreator();
        Printer printer = new Printer();
        Controller contr = new Controller(regCreator, sysCreator, printer);
        new View(contr).sampleExecution();
    }
}

//________________________________________________________________________________________

// Java code implementing the RegistryCreator class:
package se.kth.iv1350.POSsys.integration;

import se.kth.iv1350.POSsys.integration.ItemRegistry;
import se.kth.iv1350.POSsys.integration.SaleRegistry;
import se.kth.iv1350.POSsys.integration.DiscountRegistry;

/**
 * This class is responsible for instantiating all registries.
 */
public class RegistryCreator
{
    private ItemRegistry itemRegistry;
    private SaleRegistry saleRegistry;
    private DiscountRegistry discountRegistry;
    
    /**
     * Creates new instances
     */
    public RegistryCreator()
    {
        itemRegistry = new ItemRegistry();
        saleRegistry = new SaleRegistry();
        discountRegistry = new DiscountRegistry();
    }
    
    /**
     * Get the value of itemRegistry
     *
     * @return the value of itemRegistry
     */
    public ItemRegistry getItemRegistry()
    {
        return itemRegistry;
    }
    
    /**
     * Get the value of saleRegistry
     *
     * @return the value of saleRegistry
     */
    public SaleRegistry getSaleRegistry()
    {
        return saleRegistry;
    }
    
    /**
     * Get the value of discountRegistry
     *
     * @return the value of discountRegistry
     */
    public DiscountRegistry getDiscountRegistry()
    {
        return discountRegistry;
    }
}


//________________________________________________________________________________________

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
import se.kth.iv1350.POSsys.model.Receipt;

/**
 * This is the application’s only controller class. All calls to the
 * model pass through here.
 */
public class Controller
{
    private ItemRegistry itemRegistry;
    private SaleRegistry saleRegistry;
    private DiscountRegistry discountRegistry;
    private CashRegister cashRegister;
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private Printer printer;
    private Sale sale;
    
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

//________________________________________________________________________________________

// Java code implementing the CashRegister class:
 package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.Amount;

/**
 * Represents a cash register. There shall be one instance of
 * this class for each register.
 */
public class CashRegister
{
    private Amount balance;
        
   /**
    * Creates a new instance of a cashregister with a balance of zero.
    */
    public CashRegister()
    {
        this.balance = new Amount(0);
    }
        
   /**
    * Gets the value of balance.
    *
    * @return The value of balance.
    */
    public Amount getBalance()
    {
        return balance;
    }
        
   /**
    * Updates the balance wth the specified payment.
    *
    * @param payment The amount of cash that will be added to the balance from the cash register.
    */
    public void addPayment(CashPayment payment)
    {
        balance = balance.plus(payment.getTotalPrice());
    }
}

//________________________________________________________________________________________

// Java code implementing the CashPayment class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.Amount;

/**
 * Represents one specific payment for one specific sale item. The
 * sale item is payed with cash.
 */
public class CashPayment
{
    private Amount paidAmt;
    private Amount totalPrice;
    
    /**
     * Creates a new instance. The customer handed over the
     * specified amount.
     *
     * @param paidAmt The amount of cash that was handed over by the customer.
     *
     */
    public CashPayment(Amount paidAmt)
    {
        this.paidAmt = paidAmt;
    }
    
    /**
     * Calculates the total price of the specified sale that the item is set to.
     *
     * @param paidSale The sale that the item is set to for which the customer is paying.
     *
     */
    void calculateTotalPrice(Sale paidSale)
    {
        totalPrice = paidSale.getItemForSale().getPrice();
    }
    
    /**
     * @return The total price of the saled item that was paid.
     */
    Amount getTotalPrice()
    {
        return totalPrice;
    }
    
    /**
     * @return The amount of change the customer shall have.
     */
    Amount getChange()
    {
        return paidAmt.minus(totalPrice);
    }
}

//________________________________________________________________________________________

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

//________________________________________________________________________________________

// Java code implementing the Amount class:
package se.kth.iv1350.POSsys.model;

/**
 * Represents an amount of cash or items
 */
public final class Amount
{
    private final int amount;
    
    /**
     * Creates an instance, representing the specified amount.
     *
     * @param amount The amount represented by the newly created instance.
     */
    public Amount(int amount)
    {
        this.amount = amount;
    }
    
    /**
     * Get the value of amount.
     *
     * @return The value of amount.
     */
    public int getAmount()
    {
        return amount;
    }
    
    /**
     * Sets the instance amount to become a string.
     *
     * @return amount as a string.
     */
    @Override
    public String toString()
    {
        return Integer.toString(amount);
    }
    
    /**
     * Subtracts the specified amount instance
     *
     * @param another The second specified amount
     *
     * @return The difference of the first amount and the second specified amount
     */
    public Amount minus(Amount another)
    {
        return new Amount(this.amount - another.amount);
    }
        
    /**
     * Adds the specified amount instance
     *
     * @param another The second specified amount
     *
     * @return The sum of the first amount and the second specified amount
     */
    public Amount plus(Amount another)
    {
        return new Amount(this.amount + another.amount);
    }
}

//________________________________________________________________________________________

// Java code implementing the RegistryCreator class:
package se.kth.iv1350.POSsys.integration;

import se.kth.iv1350.POSsys.integration.AccountingSystem;
import se.kth.iv1350.POSsys.integration.InventorySystem;

/**
 * This class is responsible for instantiating all external systems.
 */
public class SystemCreator
{
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    
    /**
     * Creates new instances
     */
    public SystemCreator()
    {
        accountingSystem = new AccountingSystem();
        inventorySystem = new InventorySystem();
    }
    
    /**
     * Get the value of accountingSystem
     *
     * @return the value of accountingSystem
     */
    public AccountingSystem getAccountingSystem()
    {
        return accountingSystem;
        
    }
    
    /**
     * Get the value of inventorySystem
     *
     * @return the value of inventorySystem
     */
    public InventorySystem getInventorySystem()
    {
        return inventorySystem;
    }
}

//________________________________________________________________________________________

// Java code implementing the InventorySystem class:
package se.kth.iv1350.POSsys.model;

/**
 * Contains all the sale information inside
 * the inventory system in the database.
 */
public class InventorySystem
{
    InventorySystem() {
        
    }
    
    /**
     * Updates the inventory system.
     */
    public void updateInventorySystem() {
        
    }
}

//________________________________________________________________________________________

// Java code implementing the AccountingSystem class:
package se.kth.iv1350.POSsys.model;

/**
 * Contains all the sale information inside the
 * accounting system (for accounting) in the database.
 */
public class AccountingSystem
{
    AccountingSystem() {
        
    }
    
    /**
     * Updates the accounting system.
     */
    public void updateAccountingSystem() {
        
    }
}
