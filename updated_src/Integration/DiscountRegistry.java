package Integration;

import Model.Amount;
import Model.ItemDTO;
import Model.Discount;
import Model.CustomerDTO;

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
     * @throws java.lang.ArgumentException
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
        double discountPrice = 0;
        Amount amountOfItems = item.getAmount();
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
        totalPrice = salesItemPrice * amountOfItems;
        System.out.println("Item Price:  " + itemPrice + " Discounted Price:  " + discountPrice + " Amount of items:  " + amountOfItems + " Total Price:  " + totalPrice);
        
        return;
    }
}