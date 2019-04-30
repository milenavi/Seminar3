// Java code implementing the CashRegister class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.Amount;

/**
 * Represents a cash register. There shall be one instance of
 * this class for each register.
 */
public class CashRegister
{
    private Amount balance;
    
    /**
     * Creates a new instance of a cashregister with a balance of zero.
     */
    public CashRegister()
    {
        this.balance = new Amount(0);
    }
    
    /**
     * Gets the value of balance.
     *
     * @return The value of balance.
     */
    public Amount getBalance()
    {
        return balance;
    }
    
    /**
     * Updates the balance wth the specified payment.
     *
     * @param payment The amount of cash that will be added to the balance from the cash register.
     */
    public void addPayment(CashPayment payment)
    {
        balance = balance.plus(payment.getTotalPrice());
    }
}
