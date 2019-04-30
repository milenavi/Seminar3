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
