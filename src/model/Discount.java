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
     */
     // public void setDiscountItem(ItemDTO discountedItem) {
     // }
}
