// Java code implementing the CashPayment class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.Amount;

/**
 * Represents one specific payment for one specific sale item. The
 * sale item is payed with cash.
 */
public class CashPayment
{
    private Amount paidAmt;
    private Amount totalPrice;
    
    /**
     * Creates a new instance. The customer handed over the
     * specified amount.
     *
     * @param paidAmt The amount of cash that was handed over by the customer.
     *
     */
    public CashPayment(Amount paidAmt)
    {
        this.paidAmt = paidAmt;
    }
    
    /**
     * Calculates the total price of the specified sale that the item is set to.
     *
     * @param paidSale The sale that the item is set to for which the customer is paying.
     *
     */
    void calculateTotalPrice(Sale paidSale)
    {
        totalPrice = paidSale.getItemForSale().getPrice();
    }
    
    /**
     * @return The total price of the saled item that was paid.
     */
    Amount getTotalPrice()
    {
        return totalPrice;
    }
    
    /**
     * @return The amount of change the customer shall have.
     */
    Amount getChange()
    {
        return paidAmt.minus(totalPrice);
    }
}
