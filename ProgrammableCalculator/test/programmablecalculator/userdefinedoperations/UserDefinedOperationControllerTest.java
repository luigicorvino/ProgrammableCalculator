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
    
    @Test (expected=OperationAlreadyExistsException.class)
    public void testCreateException() throws OperationAlreadyExistsException{
        operations.create("op1", "+ +");
        operations.create("op1", "+ -");
    }
    
    
    
    @Test
    public void testCreate() throws OperationAlreadyExistsException {
        System.out.println("create");
        operations.create("op1", "+ +");
        assertFalse(operations.getUserDefinedOperations().isEmpty());
        UserDefinedOperation operation = operations.getUserDefinedOperations().remove(0);
        assertEquals(2,operation.getOperationsSequence().size());
        List<String> expResult = new LinkedList<>();
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
    public void testInvokeException() throws NoSuchOperationException, OperationAlreadyExistsException{
        operations.create("op1", "+ +");
        operations.invoke("op2");
    }
    
    @Test
    public void testInvoke() throws NoSuchOperationException, OperationAlreadyExistsException {
        System.out.println("invoke");
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
    
    
    @Test (expected=NoSuchOperationException.class)
    public void testDeleteException() throws NoSuchOperationException, OperationAlreadyExistsException{
        operations.create("op1", "- +");
        operations.invoke("op2");
    }
    
    
    
    @Test
    public void testDelete() throws NoSuchOperationException, OperationAlreadyExistsException{
        System.out.println("delete");
        operations.create("op1", "+ - /");
        operations.create("op2", "* sqrt");
        operations.create("op3", "+ dup over");
        assertTrue(operations.getUserDefinedOperations().size() == 3);
        operations.delete("op2");
        assertNotEquals("op2",operations.getUserDefinedOperations().get(0).getName());
        assertNotEquals("op2",operations.getUserDefinedOperations().get(1).getName());
        operations.delete("op1");
        assertEquals("op3",operations.getUserDefinedOperations().get(0).getName());
        operations.delete("op3");
        assertTrue(operations.getUserDefinedOperations().isEmpty());
   
    }
    
    @Test (expected=OperationAlreadyExistsException.class)
    public void testModifyNameOperationAlreadyExistsException() throws OperationAlreadyExistsException, NoSuchOperationException{
        operations.create("op1", "+ +");
        operations.create("op2", "+ -");
        operations.modifyName("op1", "op2");
    }
    
    @Test (expected=NoSuchOperationException.class)
    public void testModifyNameNoSuchOperationException() throws OperationAlreadyExistsException, NoSuchOperationException{
        operations.create("op1", "+ +");
        operations.create("op2", "+ -");
        operations.modifyName("op3", "op3 edited");
    }
    
    
    @Test
    public void testModifyName() throws OperationAlreadyExistsException, NoSuchOperationException{
        System.out.println("Modify name");
        operations.create("op1", "+ +");
        operations.create("op2"," sqrt *");
        assertEquals("op1",operations.getUserDefinedOperations().get(0).getName());
        assertEquals("op2",operations.getUserDefinedOperations().get(1).getName());
        assertFalse(operations.getUserDefinedOperations().isEmpty());
        operations.modifyName("op1", "op1 modified");
        assertNotEquals("op1",operations.getUserDefinedOperations().get(0).getName());
        assertEquals("op1 modified",operations.getUserDefinedOperations().get(0).getName());

    }
    
    
    @Test (expected=NoSuchOperationException.class)
    public void testModifySequenceNoSuchOperationException() throws OperationAlreadyExistsException, NoSuchOperationException{
        operations.create("op1", "+ +");
        operations.create("op2", "sqrt *");
        operations.modifySequence("op3", "+ -");
    }
    
    @Test
    public void testModifySequence() throws OperationAlreadyExistsException, NoSuchOperationException{
        System.out.println("Modify sequence");
        operations.create("op1", "+ +");
        List<String> expResult = new LinkedList<String>();
        expResult.add("+");
        expResult.add("+");
        assertEquals(expResult, operations.invoke("op1"));
        
        operations.modifySequence("op1","* sqrt" );
        
        expResult = new LinkedList<>();
        expResult.add("*");
        expResult.add("sqrt");
        
        assertEquals(expResult, operations.invoke("op1"));
    }
    
    
    @Test
    public void testGetOperationsList() throws OperationAlreadyExistsException{
        System.out.println("Get Operations List");
        operations.create("op1", "+ +");
        operations.create("op2", "- -");
        operations.create("op3", "* sqrt");
        List<String> names = operations.getOperationsList();
        System.out.println(names.get(0));
        assertEquals(names.get(0), "op1 : [+, +]");
        assertEquals(names.get(1), "op2 : [-, -]");
        assertEquals(names.get(2),"op3 : [*, sqrt]");
    }
    
}
