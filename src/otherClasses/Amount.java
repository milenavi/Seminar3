// Java code implementing the Amount class:
package se.kth.iv1350.POSsys.otherClasses;

/**
 * Represents an amount of cash or items
 */
public final class Amount
{
    private final int amount;
    
    /**
     * Creates an instance, representing the specified amount.
     *
     * @param amount The amount represented by the newly created instance.
     */
    public Amount(int amount)
    {
        this.amount = amount;
    }
    
    /**
     * Get the value of amount.
     *
     * @return The value of amount.
     */
    public int getAmount()
    {
        return amount;
    }
    
    /**
     * Sets the instance amount to become a string.
     *
     * @return amount as a string.
     */
    @Override
    public String toString()
    {
        return Integer.toString(amount);
    }
    
    /**
     * Subtracts the specified amount instance
     *
     * @param another The second specified amount
     *
     * @return The difference of the first amount and the second specified amount
     */
    public Amount minus(Amount another)
    {
        return new Amount(this.amount - another.amount);
    }
    
    /**
     * Adds the specified amount instance
     *
     * @param another The second specified amount
     *
     * @return The sum of the first amount and the second specified amount
     */
    public Amount plus(Amount another)
    {
        return new Amount(this.amount + another.amount);
    }
}
