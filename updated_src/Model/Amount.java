/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Represents an amount of cash or items
 */
public class Amount
{
    private final double amount;
    
    /**
     * Creates an instance, representing the specified amount.
     *
     * @param amount The amount represented by the newly created instance.
     */
    public Amount(double amount)
    {
        this.amount = amount;
    }

    /**
     * Get the value of amount.
     *
     * @return The value of amount.
     */
    public double getAmount()
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
        return Integer.toString((int) amount);
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