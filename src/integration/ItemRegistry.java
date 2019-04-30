// Java code implementing the ItemRegistry class:
package se.kth.iv1350.POSsys.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all calls to the data store with items that may be
 * saled.
 */
public class ItemRegistry
{
    private List<ItemDTO> items = new ArrayList<>();
    private List<SalesLineItem> lineItems = new ArrayList<SalesLineItem>();
    
    ItemRegistry()
    {
        addItems();
    }
    
    /**
     * Get the information of the recorded item.
     *
     * @param recordedItem This object contains the record
     *                    criteria. Fields in the object that
     *                    are set to <code>null</code> or
     *                    <code>0</code> are ignored.
     *
     * @return <code>true</code> if an item with the same
     *         features as <code>recordedItem</code> was recorded,
     *         <code>false</code> if no such item was recorded.
     */
    public ItemDTO getItemInformation(ItemDTO recordedItem)
    {
        for (ItemDTO item : items)
        {
            if (item.matches(recordedItem))
            {
                return item;
            }
        }
        return null;
    }
    
    private void addItems()
    {
        items.add(new ItemDTO(1004021401204, 2, 129.99));
        items.add(new ItemDTO(2004202309023, 1, 249.99));
        items.add(new ItemDTO(3004204192474, 8, 12.99));
    }
    
    /**
     * Sets increased amount of a sold item. After calling this method,
     * the sold item will be incremented in the POS system.
     *
     * @param recordedItem The item that is recorded in the POS system.
     */
    public void setIncreasedAmountOfSoldItem(ItemDTO recordedItem)
    {
        for (SalesLineItem lineItem : this.lineItems)
        {
            if (matches(lineItem, recordedItem))
            {
                lineItem.add();
                return;
            }
        }
    }
    
    /**
     * Checks if a non-recorded item has the same matching features as
     * the recorded item which now contains item information inside the POS system.
     *
     * @param afterItemRecord Contains record criteria and represents a recorded item in the POS system.
     *
     * @param itemToRecord Represents a regular, non-recorded item.
     *
     * @return <code>true</code> if this object <code>itemToRecord</code> has
     *         the same features as <code>afterItemRecord</code>,
     *         <code>false</code> if it has not.
     */
    private boolean matches(SalesLineItem afterItemRecord, ItemDTO itemToRecord)
    {
        if (itemToRecord.getID() != 0 && itemToRecord.getID() != afterItemRecord.id)
        {
            return false;
        }
        if (itemToRecord.getAmount() != null && !itemToRecord.getAmount().equals(afterItemRecord.amount))
        {
            return false;
        }
        if (itemToRecord.getPrice() != 0 && itemToRecord.getPrice() != afterItemRecord.price)
        {
            return false;
        }
        return true;
    }
    
    private static class SalesLineItem
    {
        private int id;
        private Amount amount;
        private double price;
        
        public SalesLineItem(int id, Amount amount, double price)
        {
            this.id = id;
            this.amount = amount;
            this.price = price;
        }
    }
}
