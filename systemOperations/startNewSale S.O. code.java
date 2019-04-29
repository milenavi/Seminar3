// Startup:
package se.kth.iv1350.POSsys.startup;

import se.kth.iv1350.POSsys.controller.Controller;
import se.kth.iv1350.POSsys.view.View;

public class Main
{
    public static void main(String[] args)
    {
        Controller contr = new Controller();
        View view = new View(contr);
        view.runFakeSale();
    }
}

// _____________________________________________________

// View:
package se.kth.iv1350.POSsys.view;

import se.kth.iv1350.POSsys.controller.Controller;

public class View
{
    private Controller contr;
    
    public View(Controller contr)
    {
        this.contr = contr;
    }
    
    public void runFakeSale()
    {
        contr.startNewSale();
        System.out.println("New sale was started.");
    }
}

// _____________________________________________________

// Controller:
package se.kth.iv1350.POSsys.controller;

import se.kth.iv1350.POSsys.model.Sale;

public class Controller
{
    private Sale sale;
    private DiscountRegistry discountRegistry;
    private ItemRegistry itemRegistry;
    private SaleRegistry saleRegistry;
    private Printer printer;
    private CashRegister cashRegister;
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    
    /**
     * Creates a new instance, represented as a controller.
     *
     * @param sysCreator     Used to get all classes that handles external system calls.
     * @param regCreator    Used to get all classes that handles database calls.
     * @param printer           Interface to printer.
     */
    public Controller(RegistryCreator regCreator, SystemCreator sysCreator, Printer printer)
    {
        this.discountRegistry = regCreator.getDiscountRegistry();
        this.itemRegistry = regCreator.getItemRegistry();
        this.saleRegistry = regCreator.getSaleRegistry();
        this.printer = printer;
        this.cashRegister = new CashRegister();
        this.accountingSystem = sysCreator.getAccountingSystem();
        this.inventorySystem = sysCreator.getInventorySystem();
    }
    
    /**
     *  Initiates a new sale.
     */
    public void startNewSale()
    {
        this.sale = new Sale();
    }
}

// ____________________________________________________________________________

// Model:
package se.kth.iv1350.POSsys.model;

public class Sale
{
    public Sale() {
        
    }
}
