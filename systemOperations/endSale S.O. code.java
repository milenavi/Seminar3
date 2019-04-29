// Java code implementing the View class:
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

// Java code implementing the Controller class:
package se.kth.iv1350.POSsys.controller;

import se.kth.iv1350.POSsys.integration.SaleRegistry;
import se.kth.iv1350.POSsys.integration.AccountingSystem;
import se.kth.iv1350.POSsys.integration.InventorySystem;
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
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private RegistryCreator regCreator;
    
    /**
     * Creates a new instance.
     *
     * @param regCreator Used to get all classes that handle database calls.
     * */
    public Controller(RegistryCreator regCreator) {
        
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
}

//________________________________________________________________________________________

// Java code implementing the Sale class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.ItemDTO;

/**
 * Represents one particular sale transaction, where one
 * particular item is set to sale by a cashier.
 */
public class Sale
{
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
}

//________________________________________________________________________________________

//  Java code implementing the SaleRegistry class:
package se.kth.iv1350.POSsys.integration;

import se.kth.iv1350.POSsys.model.Sale;

/**
 * Contains all calls to the data store with performed sales.
 */
public class SaleRegistry
{
    private boolean completeSale = false;
    
    /**
     * Completes the specified sale permanently.
     *
     * @param sale The sale that will be completed.
     */
    public void setSaleComplete(Sale sale)
            throws java.lang.IllegalArgumentException
    {
        if (sale == null)
            throw new IllegalArgumentException("A sale is not underway.");
        
        completeSale = true;
    }
    
    /**
     * Completes the specified sale permanently.
     *
     * @return completeSale The completed sale.
     */
    public boolean completeSale()
    {
        return completeSale;
    }
}

//________________________________________________________________________________________

// Java code implementing the Main class:
package se.kth.iv1350.POSsys.startup;

import se.kth.iv1350.POSsys.controller.Controller;
import se.kth.iv1350.POSsys.integration.RegistryCreator;
import se.kth.iv1350.POSsys.integration.SystemCreator;
import se.kth.iv1350.POSsys.integration.Printer;
import se.kth.iv1350.POSsys.view.View;

/**
 * Contains the <code>main</code> method. Performs all startup of
 * the application.
 */
public class Main
{
    public static void main(String[] args)
    {
        RegistryCreator regCreator = new RegistryCreator();
        SystemCreator sysCreator = new SystemCreator();
        Printer printer = new Printer();
        Controller contr = new Controller(regCreator, sysCreator, printer);
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
