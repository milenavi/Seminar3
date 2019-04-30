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
