/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Sale;
import java.time.LocalDateTime;

/**
 * The receipt of a sale
 */
public class Receipt
{
    private final Sale sale;
    private ItemDTO item;
    
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
        builder.append("Amount of items: ");
        builder.append("Price for the item: ");
        appendLine(builder, item.toString());
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