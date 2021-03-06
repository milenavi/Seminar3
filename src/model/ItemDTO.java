// Java code implementing the ItemDTO class:
package se.kth.iv1350.POSsys.model;

/**
 * Contains information about one particular item.
 */
public final class ItemDTO
{
    private final int id;
    private final Amount amount;
    private final double price;
    
    /**
     * Creates a new instance representing a particular item.
     *
     * @param id The identification number of the item.
     *
     * @param amount The amount/quantity of items.
     *
     * @param price A price of an item.
     */
    public ItemDTO(int id, Amount amount, double price)
    {
        this.id = id;
        this.amount = amount;
        this.price = price;
    }
    
    /**
     * Checks if the recorded item has the same matching features as
     * the item information that exists inside the POS system.
     *
     * @param recorded Contains record criteria.
     *
     * @return <code>true</code> if this object has
     *         the same features as <code>recorded</code>,
     *         <code>false</code> if it has not.
     */
    boolean matches(ItemDTO recorded)
    {
        if (recorded.getID() != 0 && recorded.getID() != id)
        {
            return false;
        }
        if (recorded.getAmount() != null && !recorded.getAmount().equals(amount))
        {
            return false;
        }
        if (recorded.getPrice() != 0 && recorded.getPrice() != price)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Makes the <code>Amount</code>, <code>int</code> and
     * <code>double</code> into a <code>String</code> object.
     *
     * @return <code>Amount</code>, <code>int</code>
     * and <code>double</code> as a <code>String</code>.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("id: " + id + ", ");
        builder.append("amount: " + amount);
        builder.append("price: " + price);
        return builder.toString();
    }
    
    /**
     * Get the id of an item
     *
     * @return the id of an item
     */
    public int getID()
    {
        return id;
    }
    
    /**
     * Get the amount of items
     *
     * @return the amount of items
     */
    public Amount getAmount()
    {
        return amount;
    }
    
    /**
     * Get the price of an item
     *
     * @return the price of an item
     */
    public double getPrice()
    {
        return price;
    }
}
