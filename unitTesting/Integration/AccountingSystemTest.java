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
public class AccountingSystemTest 
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testUpdateAccountingSystem()
    {
        System.out.println("updateAccountingSystem");
        AccountingSystem instance = new AccountingSystem();
        instance.updateAccountingSystem();
        fail("The test case is a prototype.");
    }
    
}
