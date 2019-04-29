                                // The recordItem system operation \\

// Java code implementing the View class:
package se.kth.iv1350.POSsys.view;

import se.kth.iv1350.POSsys.controller.Controller;
import se.kth.iv1350.POSsys.integration.ItemDTO;

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
    
    /**
     * Simulates a user input that generates calls to all
     * system operations.
     */
    public void sampleExecution()
    {{
        ItemDTO invalidItem = new ItemDTO(99122837102322, 0, 0.0);
        
        ItemDTO validItem = new ItemDTO(99122837131, 2, 14.99);
        
        ItemDTO recordedItem = contr.recordItem(invalidItem);
        System.out.println("Result of recording an invalid item: " + recordedItem);
        
        recordedItem = contr.recordItem(validItem);
        System.out.println("Result of recording a valid item: " + recordedItem);
    }
    
}

//_____________________________________________________________________________

// Java code implementing the Controller class:
package se.kth.iv1350.POSsys.controller;

import se.kth.iv1350.POSsys.integration.ItemRegistry;
import se.kth.iv1350.POSsys.model.ItemDTO;
import se.kth.iv1350.POSsys.integration.RegistryCreator;

public class Controller
{
    private ItemRegistry itemRegistry;
    
    /**
     * Creates a new instance.
     *
     * @param regCreator Used to get all classes that handle database calls.
     *
     */
    public Controller(RegistryCreator regCreator)
    {
        this.itemRegistry = regCreator.getItemRegistry();
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
}

//_____________________________________________________________________________

// Java code implementing the ItemDTO class:
package se.kth.iv1350.POSsys.model;
    
/**
 * Contains information about one particular item.
 */
public final class ItemDTO
{
    private final int id;
    private final Amount amount;
    private final double price;
    
    /**
     * Creates a new instance representing a particular item.
     *
     * @param id The identification number of the item.
     *
     * @param amount The amount/quantity of items.
     *
     * @param price A price of an item.
     */
    public ItemDTO(int id, Amount amount, double price)
    {
        this.id = id;
        this.amount = amount;
        this.price = price;
    }
    
    /**
     * Checks if the recorded item has the same matching features as
     * the item information that exists inside the POS system.
     *
     * @param recorded Contains record criteria.
     *
     * @return <code>true</code> if this object has
     *         the same features as <code>recorded</code>,
     *         <code>false</code> if it has not.
     */
    boolean matches(ItemDTO recorded)
    {
        if (recorded.getID() != 0 && recorded.getID() != id)
        {
            return false;
        }
        if (recorded.getAmount() != null && !recorded.getAmount().equals(amount))
        {
            return false;
        }
        if (recorded.getPrice() != 0 && recorded.getPrice() != price)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Makes the <code>Amount</code>, <code>int</code> and
     * <code>double</code> into a <code>String</code> object.
     *
     * @return <code>Amount</code>, <code>int</code>
     * and <code>double</code> as a <code>String</code>.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("id: " + id + ", ");
        builder.append("amount: " + amount);
        builder.append("price: " + price);
        return builder.toString();
    }
    
    /**
     * Get the id of an item
     *
     * @return the id of an item
     */
    public int getID()
    {
        return id;
    }
    
    /**
     * Get the amount of items
     *
     * @return the amount of items
     */
    public Amount getAmount()
    {
        return amount;
    }
    
    /**
     * Get the price of an item
     *
     * @return the price of an item
     */
    public double getPrice()
    {
        return price;
    }
}


//_____________________________________________________________________________

// Java code implementing the ItemRegistry class:
package se.kth.iv1350.POSsys.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all calls to the data store with items that may be
 * saled.
 */
public class ItemRegistry
{
    private List<ItemDTO> items = new ArrayList<>();
    
    ItemRegistry()
    {
        addItems();
    }
    
    /**
     * Get the information of the recorded item.
     *
     * @param recordedItem This object contains the record
     *                    criteria. Fields in the object that
     *                    are set to <code>null</code> or
     *                    <code>0</code> are ignored.
     *
     * @return <code>true</code> if an item with the same
     *         features as <code>recordedItem</code> was recorded,
     *         <code>false</code> if no such item was recorded.
     */
    public ItemDTO getItemInformation(ItemDTO recordedItem)
    {
        for (ItemDTO item : items)
        {
            if (item.matches(recordedItem))
            {
                return item;
            }
        }
        return null;
    }
    
    private void addItems()
    {
        items.add(new ItemDTO(1004021401204, 2, 129.99));
        items.add(new ItemDTO(2004202309023, 1, 249.99));
        items.add(new ItemDTO(3004204192474, 8, 12.99));
    }
}

//_____________________________________________________________________________

// Java code implementing the RegistryCreator class:
package se.kth.iv1350.POSsys.integration;
    
/**
 * This class is responsible for instantiating all registries.
 */
public class RegistryCreator
{
    private ItemRegistry itemRegistry;
    
    /**
     * Creates a new instance
     */
    public RegistryCreator()
    {
        itemRegistry = new ItemRegistry();
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

//_____________________________________________________________________________

// Java code implementing the Main class:
package se.kth.iv1350.POSsys.startup;

import se.kth.iv1350.POSsys.controller.Controller;
import se.kth.iv1350.POSsys.integration.RegistryCreator;
import se.kth.iv1350.POSsys.view.View;

/**
 * Contains the <code>main</code> method. Performs all startup
 * of the application.
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
        Controller contr = new Controller(creator);
        new View(contr).sampleExecution();
    }
}













