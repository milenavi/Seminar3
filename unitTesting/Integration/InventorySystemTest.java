/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena
 */
public class InventorySystemTest 
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testUpdateInventorySystem()
    {
        System.out.println("updateInventorySystem");
        InventorySystem instance = new InventorySystem();
        instance.updateInventorySystem();
        fail("The test case is a prototype.");
    }
    
}
