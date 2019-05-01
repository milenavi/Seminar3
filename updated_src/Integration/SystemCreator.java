/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import Integration.AccountingSystem;
import Integration.InventorySystem;

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