// Java code implementing the View class:
package se.kth.ict.oodbook.design.casestudy.view;

import se.kth.ict.oodbook.design.casestudy.controller.Controller;
import se.kth.ict.oodbook.design.casestudy.model.Amount;

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
package se.kth.ict.oodbook.design.casestudy.model;

import se.kth.ict.oodbook.design.casestudy.integration.Printer;

/**
 * Represents one particular rental transaction, where one
 * particular car is rented by one particular customer.
 */
public class Sale
{
    private CashPayment payment;
    
    /**
     * This rental is paid using the specified payment.
     *
     * @param payment The payment used to pay this rental.
     */
    public void initiatePayment(CashPayment payment)
    {
        payment.calculateTotalPrice(this);
    }
    
    /**
     * Prints a receipt for the current sale on the
     * specified printer.
     */
    public void printReceipt(Printer printer)
    {
        Receipt receipt = new Receipt(this);
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

// The class Receipt, after implementing the initiatePayment system operation:
// Java code implementing the Receipt class:
package se.kth.ict.oodbook.design.casestudy.model;

/**
 * The receipt of a rental
 */
public class Receipt
{
    private final Sale sale;
    
    /**
     * Creates a new instance.
     *
     * @param rental The rental proved by this receipt.
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
package se.kth.ict.oodbook.design.casestudy.startup;

import se.kth.ict.oodbook.design.casestudy.controller.Controller;
import se.kth.ict.oodbook.design.casestudy.integration.Printer;
import se.kth.ict.oodbook.design.casestudy.integration.RegistryCreator;
import se.kth.ict.oodbook.design.casestudy.view.View;

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
        Printer printer = new Printer();
        Controller contr = new Controller(creator, printer);
        new View(contr).sampleExecution();
    }
}

//________________________________________________________________________________________

// Java code implementing the RegistryCreator class:
 package se.kth.ict.oodbook.design.casestudy.integration;

/**
 * This class is responsible for instantiating all registries.
 */
public class RegistryCreator
{
    private ItemRegistry itemRegistry = new ItemRegistry();
    private SaleRegistry saleRegistry = new SaleRegistry();
    private DiscountRegistry discountRegistry = new DiscountRegistry();
    
    /**
     * Get the value of rentalRegistry
     *
     * @return the value of rentalRegistry
     */
    public SaleRegistry getSaleRegistry()
    {
        return saleRegistry;
    }
    
    /**
     * Get the value of carRegistry
     *
     * @return the value of carRegistry
     */
    public ItemRegistry getItemRegistry()
    {
        return itemRegistry;
        
    }
    
    /**
     * Get the value of carRegistry
     *
     * @return the value of carRegistry
     */
    public DiscountRegistry getDiscountRegistry()
    {
        return discountRegistry;
        
    }
}

//________________________________________________________________________________________

// Java code implementing the Controller class:
package se.kth.ict.oodbook.design.casestudy.controller;
import se.kth.ict.oodbook.design.casestudy.integration.ItemRegistry;
import se.kth.ict.oodbook.design.casestudy.integration.Printer;
import se.kth.ict.oodbook.design.casestudy.integration.RegistryCreator;
import se.kth.ict.oodbook.design.casestudy.integration.SaleRegistry;
import se.kth.ict.oodbook.design.casestudy.integration.DiscountRegistry;
import se.kth.ict.oodbook.design.casestudy.model.CashRegister;
import se.kth.ict.oodbook.design.casestudy.model.Amount;
import se.kth.ict.oodbook.design.casestudy.model.CashPayment;
import se.kth.ict.oodbook.design.casestudy.model.Sale;
import se.kth.ict.oodbook.design.casestudy.model.Receipt;

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
    private Accounting accounting;
    private Inventory inventory;
    private Printer printer;
    private Sale sale;
    
    /**
     * Creates a new instance.
     *
     * @param regCreator Used to get all classes that handle database
     calls.
     * @param printer    Interface to printer.
     */
    public Controller(RegistryCreator regCreator, Printer printer)
    {
        this.itemRegistry = regCreator.getItemRegistry();
        this.saleRegistry = regCreator.getSaleRegistry();
        this.discountRegistry = regCreator.getDiscountRegistry();
        this.printer = printer;
        this.cashRegister = new CashRegister();
        this.accounting = new Accounting();
        this.inventory = new Inventory();
        this.sale = new sale();
    }
    
    /**
    * Makes a payment with the given {@link Amount}. Will be added to the balance of the cashRegister.
    * The external system will be updated, and a receipt will be created and printed by the printer.
    * Handles rental payment. Updates the balance of the cash
    * register where the payment was performed. Calculates
    * change. Prints the receipt.
    *
    * @param paidAmount The amount of money given by the customer.
    */
    public void initiatePayment(Amount paidAmount)
    {
        CashPayment payment = new CashPayment(paidAmount);
        sale.initiatePayment(payment);
        cashRegister.addPayment(payment);
        accountingSystem.updateAccounting();
        inventorySystem.updateInventory();
        Receipt receipt = sale.getReceipt();
        printer.printReceipt(receipt);
        System.out.println("Change after payment: " + payment.getChange().toString());
    }
}

//________________________________________________________________________________________

// Java code implementing the CashRegister class:
 package se.kth.ict.oodbook.design.casestudy.model;

/**
 * Represents a cash register. There shall be one instance of
 * this class for each register.
 */
public class CashRegister
{
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
         * @param payment The amount of money that will be added to the balance of the cash register.
         */
        public void addPayment(CashPayment payment)
        {
            balance = balance.plus(payment.getTotalPrice());
        }
    }
}

//________________________________________________________________________________________

// Java code implementing the CashPayment class:
package se.kth.ict.oodbook.design.processSale.model;

/**
 * Represents one specific payment for one specific rental. The
 * rental is payed with cash.
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
     * Calculates the total cost of the specified rental.
     *
     * @param paidRental The rental for which the customer is paying.
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
package se.kth.ict.oodbook.design.casestudy.integration;

import se.kth.ict.oodbook.design.casestudy.model.Receipt;

/**
 * The interface to the printer, used for all printouts initiated
 * by this program.
 */
public class Printer
{
    /**
     * Creates an instance, represented as a printer.
     */
    public Printer() {
        
    }
    
    /**
     * Prints the specified receipt, this is a dummy printer and not a real one.
     * It prints to <code>System.out</code>, instead of a printer.
     *
     * @param receipt The specified receipt that will be printed.
     */
    public void printReceipt(Receipt receipt)
    {
        System.out.println(receipt.toString());
    }
}

//________________________________________________________________________________________

// Java code implementing the Amount class:
package se.kth.ict.oodbook.design.casestudy.model;

/**
 * Represents an amount of money or items
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
     * Makes the <code>Amount</code> into a <code>String</code> object.
     *
     * @return <code>Amount</code> as a <code>String</code>.
     */
    @Override
    public String toString()
    {
        return Integer.toString(amount);
    }
    
    /**
     * Checks if the specified amount is equal to this amount.
     *
     * @param obj The specified amount
     * @return <code>false</code> if it's not the same kind of object or class or
     * amount is not the same. <code>true</code> otherwise.
     */
    @Override
    public boolean equals(Object object)
    {
        if (object == null)
        {
            return false;
        }
        
        if (getClass() != object.getClass())
        {
            return false;
        }
        
        final Amount other = (Amount) object;
        
        if (!Objects.equals(this.amount, other.amount))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Will subtract with the specified <code>Amount</code>
     *
     * @param other The specified <code>Amount</code>
     * @return The difference of this <code>Amount</code>
     * with the other <code>Amount</code>
     */
    public Amount minus(Amount other)
    {
        return new Amount(this.amount - other.amount);
    }
        
    /**
     * Will add with the specified <code>Amount</code>
     *
     * @param other The specified <code>Amount</code>
     * @return The sum of this <code>Amount</code> with the other <code>Amount</code>
     */
    public Amount plus(Amount other)
    {
        return new Amount(this.amount + other.amount);
    }
}
