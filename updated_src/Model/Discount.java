/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Sale;
import Model.CustomerDTO;

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
     * Sets the item to discount.
     *
     * @param discountedItem The item that was discounted.
     *
     */
     // public void setDiscountItem(ItemDTO discountedItem) {
     // }
}
