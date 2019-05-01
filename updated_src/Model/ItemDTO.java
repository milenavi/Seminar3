/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Contains information about one particular item.
 */
public final class ItemDTO
{
    private final int id;
    private final Amount amount;
    private final Amount price;
    
    /**
     * Creates a new instance representing a particular item.
     *
     * @param id The identification number of the item.
     *
     * @param amount The amount/quantity of items.
     *
     * @param price A price of an item.
     */
    public ItemDTO(int id, Amount amount, Amount price)
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
    public boolean matches(ItemDTO recorded)
    {
        if (recorded.getID() != 0 && recorded.getID() != id)
        {
            return false;
        }
        if (recorded.getAmount() != null && !recorded.getAmount().equals(amount))
        {
            return false;
        }
        if (recorded.getPrice() != null && !recorded.getPrice().equals(price))
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
    public Amount getPrice()
    {
        return price;
    }

    Object getItemInformation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
