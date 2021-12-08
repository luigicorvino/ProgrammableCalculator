/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.userdefinedoperations;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luigi
 */
public class UserDefinedOperationControllerTest {
    private UserDefinedOperationController operations;
    public UserDefinedOperationControllerTest() {
    }
    
    @Before
    public void setUp() {
        operations = new UserDefinedOperationController();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class UserDefinedOperationController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        operations.create("op1", "+ +");
        assertFalse(operations.getUserDefinedOperations().isEmpty());
        UserDefinedOperation operation = operations.getUserDefinedOperations().remove(0);
        assertEquals(2,operation.getOperationsSequence().size());
        List<String> expResult = new LinkedList<String>();
        expResult.add("+");
        expResult.add("+");
        assertEquals(expResult,operation.getOperationsSequence());
        assertTrue(operations.getUserDefinedOperations().isEmpty());
        
        operations.create("op2", "+ - sqrt invert *");
        operation = operations.getUserDefinedOperations().remove(0);
        expResult = new LinkedList<String>();
        expResult.add("-");
        expResult.add("+");
        expResult.add("invert");
        expResult.add("sqrt");
        expResult.add("*");
        assertNotEquals(expResult, operation.getOperationsSequence());
        
        expResult = new LinkedList<String>();
        expResult.add("+");
        expResult.add("-");
        expResult.add("sqrt");
        expResult.add("invert");
        expResult.add("*");
        assertEquals(expResult, operation.getOperationsSequence());
        assertTrue(operations.getUserDefinedOperations().isEmpty());
                // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of invoke method, of class UserDefinedOperationController.
     */
    
    @Test (expected=NoSuchOperationException.class)
    public void testInvokeException() throws NoSuchOperationException{
        operations.create("op1", "+ +");
        operations.invoke("op");
    }
    
    @Test
    public void testInvoke() throws Exception {
        operations.create("op1", "+ +");
        operations.create("op2", "* *");
        List<String> expResult = new LinkedList<String>();
        expResult.add("+");
        expResult.add("+");
        
        assertEquals(expResult, operations.invoke("op1"));
        assertNotEquals(expResult,operations.invoke("op2"));
        
        expResult = new LinkedList<String>();
        expResult.add("*");
        expResult.add("*");
        
        assertNotEquals(expResult, operations.invoke("op1"));
        assertEquals(expResult,operations.invoke("op2"));
        
    }
    
}
