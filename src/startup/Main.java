// Java code implementing the Main class:
package se.kth.iv1350.POSsys.startup;

import se.kth.iv1350.POSsys.controller.Controller;
import se.kth.iv1350.POSsys.integration.RegistryCreator;
import se.kth.iv1350.POSsys.integration.SystemCreator;
import se.kth.iv1350.POSsys.integration.Printer;
import se.kth.iv1350.POSsys.view.View;

/**
 * Contains the <code>main</code> method. Performs all startup of
 * the application.
 */
public class Main
{
    public static void main(String[] args)
    {
        RegistryCreator regCreator = new RegistryCreator();
        SystemCreator sysCreator = new SystemCreator();
        Printer printer = new Printer();
        Controller contr = new Controller(regCreator, sysCreator, printer);
        View view = new View(contr);
        view.sampleExecution();
        view.runFakeSale();
    }
}
