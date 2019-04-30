// Java code implementing the Receipt class:
package se.kth.iv1350.POSsys.model;

import se.kth.iv1350.POSsys.model.Sale;

/**
 * The receipt of a sale
 */
public class Receipt
{
    private final Sale sale;
    
    /**
     * Creates a new instance.
     *
     * @param sale The sale proved by this receipt.
     */
    public Receipt(Sale sale)
    {
        this.sale = sale;
    }
    
    /**
     * Creates a well-formatted string with the entire content
     * of the receipt.
     *
     * @return The well-formatted receipt string.
     */
    public String createReceiptString()
    {
        StringBuilder builder = new StringBuilder();
        appendLine(builder, "Item Sale");
        endSection(builder);
        
        LocalDateTime saleTime = LocalDateTime.now();
        builder.append("Sale time: ");
        appendLine(builder, saleTime.toString());
        endSection(builder);
        
        builder.append("Item for sale: ");
        appendLine(builder, item.getItemInformation().getID());
        builder.append("Amount of items: ");
        appendLine(builder, item.getItemInformation().getAmount());
        builder.append("Price for the item: ");
        appendLine(builder, item.getItemInformation().getPrice());
        builder.append("Total price: ");
        appendLine(builder, sale.getPayment().getTotalPrice().toString());
        builder.append("Change after payment: ");
        appendLine(builder, sale.getPayment().getChange().toString());
        endSection(builder);
        
        return builder.toString();
    }
    
    private void appendLine(StringBuilder builder, String line)
    {
        builder.append(line);
        builder.append("\n");
    }
    
    private void endSection(StringBuilder builder)
    {
        builder.append("\n");
    }
}
