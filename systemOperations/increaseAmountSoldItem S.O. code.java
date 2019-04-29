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

// _____________________________________________________________________________________

// Java code implementing the Sale class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.Sale;
import se.kth.iv1350.POSsys.model.ItemDTO;
import se.kth.iv1350.POSsys.model.ItemRegistry;

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
    
    
    /**
     * Creates a new instance, represented as a Sale.
     *
     * @param item The item where the customer is buying and the cashier is recording.
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
}

// _______________________________________________________________________________________________

// Java code implementing the SaleRegistry class:
package se.kth.iv1350.POSsys.integration;

import se.kth.iv1350.POSsys.model.Sale;

/**
 * Contains all calls to the data store with performed sales.
 */
public class SaleRegistry
{
    private Sale sale;
    
    /**
     * Saves the specified sale permanently.
     *
     * @param sale The sale that will be saved.
     */
    public void saveSale(Sale sale)
    {
        this.sale = sale;
    }
}

// _______________________________________________________________________________________________

// Java code implementing the ItemRegistry class:
package se.kth.iv1350.POSsys.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all calls to the data store with items that may
 * be set to sale.
 */
public class ItemRegistry
{
    private List<SalesLineItem> lineItems = new ArrayList<SalesLineItem>();
    
    /**
     * Sets increased amount of a sold item. After calling this method,
     * the sold item will be incremented in the POS system.
     *
     * @param recordedItem The item that is recorded in the POS system.
     */
    public void setIncreasedAmountOfSoldItem(ItemDTO recordedItem)
    {
        for (SalesLineItem lineItem : this.lineItems)
        {
            if (matches(lineItem, recordedItem))
            {
                lineItem.add();
                return;
            }
        }
    }
    
    /**
     * Checks if a non-recorded item has the same matching features as
     * the recorded item which now contains item information inside the POS system.
     *
     * @param afterItemRecord Contains record criteria and represents a recorded item in the POS system.
     *
     * @param itemToRecord Represents a regular, non-recorded item.
     *
     * @return <code>true</code> if this object <code>itemToRecord</code> has
     *         the same features as <code>afterItemRecord</code>,
     *         <code>false</code> if it has not.
     */
    private boolean matches(SalesLineItem afterItemRecord, ItemDTO itemToRecord)
    {
        if (itemToRecord.getID() != 0 && itemToRecord.getID() != afterItemRecord.id)
        {
            return false;
        }
        if (itemToRecord.getAmount() != null && !itemToRecord.getAmount().equals(afterItemRecord.amount))
        {
            return false;
        }
        if (itemToRecord.getPrice() != 0 && itemToRecord.getPrice() != afterItemRecord.price)
        {
            return false;
        }
        return true;
    }
    
    private static class SalesLineItem
    {
        private int id;
        private Amount amount;
        private double price;
        
        public SalesLineItem(int id, Amount amount, double price)
        {
            this.id = id;
            this.amount = amount;
            this.price = price;
        }
    }
}

// _______________________________________________________________________________________________

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

// _______________________________________________________________________________________________

// Java code implementing the RegistryCreator class:
package se.kth.iv1350.POSsys.integration;

import se.kth.iv1350.POSsys.integration.ItemRegistry;
import se.kth.iv1350.POSsys.integration.SaleRegistry;

/**
 * This class is responsible for instantiating all registries.
 */
public class RegistryCreator
{
    private ItemRegistry itemRegistry;
    private SaleRegistry saleRegistry;
    
    /**
     * Creates new instances
     */
    public RegistryCreator()
    {
        itemRegistry = new ItemRegistry();
        saleRegistry = new SaleRegistry();
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
     * Get the value of itemRegistry
     *
     * @return the value of itemRegistry
     */
    public ItemRegistry getItemRegistry()
    {
        return itemRegistry;
    }
}

// ______________________________________________________________________________________

// Java code implementing the Controller class:
package se.kth.iv1350.POSsys.controller;

import se.kth.iv1350.POSsys.integration.SaleRegistry;
import se.kth.iv1350.POSsys.model.ItemDTO;
import se.kth.iv1350.POSsys.model.Sale;
import se.kth.iv1350.POSsys.integration.RegistryCreator;

/**
 * This is the application’s only controller class. All calls to the
 * model pass through here.
 */
public class Controller
{
    private SaleRegistry saleRegistry;
    private Sale sale;
    
    public Controller(RegistryCreator regCreator) {
        
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
}

