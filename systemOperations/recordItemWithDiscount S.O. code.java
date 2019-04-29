// Java code implementing the Discount class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.Sale;
import se.kth.iv1350.POSsys.model.CustomerDTO;

public class Discount
{
    private Sale sale;
    private CustomerDTO customer;
    
    /**
     * Creates a new instance, representing a discount requested by
     * the specified customer.
     *
     * @param customer The requesting/purchasing customer.
     */
    public Discount(Sale sale, CustomerDTO customer)
    {
        this.sale = sale;
        this.customer = customer;
    }
    
    /**
     * Sets the item to disocunt.
     *
     * @param discountedItem The item that was discounted.
     *
    public void setDiscountItem(ItemDTO discountedItem) {
    }
     */
}

//________________________________________________________________________________________

//  Java code implementing the View class:
package se.kth.iv1350.POSsys.view;

import se.kth.iv1350.POSsys.controller.Controller;

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
}

//________________________________________________________________________________________

// Java code implementing the CustomerDTO class:
package se.kth.iv1350.POSsys.model;

/**
 * Contains information about one particular customer.
 */
public class CustomerDTO
{
    private int id;
    
    /**
     * Creates a new instance representing a particular customer.
     *
     * @param id The ID given to make a discount possible for the customer.
     */
    public CustomerDTO(int id)
    {
        this.id = id;
    }
    
    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getID()
    {
        return id;
    }
}

//________________________________________________________________________________________

// Java code implementing the Controller class:
package se.kth.iv1350.POSsys.controller;

import se.kth.iv1350.POSsys.integration.SaleRegistry;
import se.kth.iv1350.POSsys.integration.ItemRegistry;
import se.kth.iv1350.POSsys.integration.DiscountRegistry;
import se.kth.iv1350.POSsys.model.Sale;
import se.kth.iv1350.POSsys.model.Discount;
import se.kth.iv1350.POSsys.integration.RegistryCreator;

/**
 * This is the application’s only controller class. All calls to
 * the model pass through here.
 */
public class Controller
{
    private Sale sale;
    private Discount discount;
    private RegistryCreator regCreator;
    private DiscountRegistry discountRegistry;
    private ItemRegistry itemRegistry;
    private SaleRegistry saleRegistry;
    
    /**
     * Creates a new instance.
     *
     * @param regCreator Used to get all classes that handle database calls.
     *
     */
    public Controller(RegistryCreator regCreator) {
        
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
}

//________________________________________________________________________________________

// Java code implementing the Sale class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.ItemDTO;
import se.kth.iv1350.POSsys.model.Discount;

/**
 * Represents one particular sale transaction, where one
 * particular item is set to sale by a cashier.
 */
public class Sale
{
    private Discount discount;
    private ItemDTO item;
    
    /**
     * Creates a new instance, representing a sale made by
     * the cashier in order to set the item for sale.
     *
     * @param item The item that will be set for sale.
     */
    public Sale(ItemDTO item)
    {
        this.item = item;
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
}

//________________________________________________________________________________________

// Java code implementing the DiscountRegistry class:
package se.kth.iv1350.POSsys.integration;

import se.kth.iv1350.POSsys.model.ItemDTO;

/**
 * Contains all calls to the data store with performed discounts.
 */
public class DiscountRegistry
{
    private ItemDTO item;
    
    /**
     * Gives the price of an sale item with a requested discount calculation.
     *
     * @param customer The specified customer that is buying an item with requested discount.
     *
     * @return The price after the calculation of sales item price with requested discount.
     */
    public Discount getPriceAfterDiscount(CustomerDTO customer)
                throws java.lang.ArgumentException
    {
        if (customer.getID() < 0)
                throw new ArgumentException("Given customer ID not found." + " Customer ID: " + customer.getID());
        
        // Testing price calculation and calling calculatePriceWithDiscount method.
        return calculatePriceWithDiscount(this.item);
    }
    
    /**
     * Calculates the sales item price with three different discount categories.
     *
     * @param item The item that the customer is buying and the cashier is recording.
     */
    public void calculatePriceWithDiscount(ItemDTO item)
    {
        final double TEN_PERCENT_DISCOUNT = 0.10;
        final double TWENTY_PERCENT_DISCOUNT = 0.20;
        final double THIRTY_PERCENT_DISCOUNT = 0.30;
        
        double totalPrice;
        double discountPrice;
        Amount amountofItems = item.getAmount();
        double itemPrice = item.getPrice();
        
        int discountPercentIndex = 3;
        switch(discountPercentIndex)
        {
            case 1:
                if(itemPrice < 49.99);
                    discountPrice = (itemPrice * TEN_PERCENT_DISCOUNT);
                    break;
                
            case 2:
                if(itemPrice < 149.99);
                    discountPrice = (itemPrice * TWENTY_PERCENT_DISCOUNT);
                    break;
                
            case 3:
                if(itemPrice > 149.99);
                    discountPrice = (itemPrice * THIRTY_PERCENT_DISCOUNT);
                    break;
        }
        
        double salesItemPrice = itemPrice - discountPrice;
        totalPrice = salesItemPrice * amountofItems;
        System.out.println("Item Price:  " + itemPrice + " Discounted Price:  " + discountPrice + " Amount of items:  " + amountofItems + " Total Price:  " + totalPrice);
        
        return;
    }
}

//________________________________________________________________________________________

// Java code implementing the Main class:
package se.kth.iv1350.POSsys.startup;

import se.kth.iv1350.POSsys.controller.Controller;
import se.kth.iv1350.POSsys.integration.RegistryCreator;
import se.kth.iv1350.POSsys.view.View;

/**
 * Contains the <code>main</code> method. Performs all startup of
 * the application.
 */
public class Main
{
    public static void main(String[] args)
    {
        RegistryCreator creator = new RegistryCreator();
        Controller contr = new Controller(creator);
        new View(contr);
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
