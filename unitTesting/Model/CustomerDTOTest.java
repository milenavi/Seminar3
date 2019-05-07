/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena
 */
public class CustomerDTOTest 
{
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetID() 
    {
        System.out.println("getID");
        CustomerDTO instance = null;
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
    }
    
}
