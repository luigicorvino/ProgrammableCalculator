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
public class UserDefinedOperationTest {
    private UserDefinedOperation operation;
    public UserDefinedOperationTest() {
    }
   
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class UserDefinedOperation.
     */
    @Test
    public void testGetAndSetName() {
        System.out.println("getAndSetName");
        operation = new UserDefinedOperation("ipotenusa",null);
        String result = operation.getName();
        assertEquals("ipotenusa", result);
        operation.setName("calcolo ipotenusa");
        result = operation.getName();
        assertEquals("calcolo ipotenusa", result);
        operation.setName("^3");
        result = operation.getName();
        assertEquals("^3", result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setName method, of class UserDefinedOperation.
     */

    /**
     * Test of getOperationsSequence method, of class UserDefinedOperation.
     */
    @Test
    public void testGetAndSetOperationsSequence() {
        System.out.println("getAndSetOperationsSequence");
        List<String> sequence = new LinkedList<>();
        sequence.add("+");
        sequence.add("sqrt");
        sequence.add("multiply");
        operation = new UserDefinedOperation("op1",sequence);
        assertEquals(sequence,operation.getOperationsSequence());
        
        List<String> sequence2 = new LinkedList<>();
        sequence2.add("+");
        sequence2.add("sqrt");
        sequence2.add("-");
        assertNotEquals(sequence2,operation.getOperationsSequence());
        operation.setOperationsSequence(sequence2);
        assertEquals(sequence2,operation.getOperationsSequence());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setOperationsSequence method, of class UserDefinedOperation.
     */
  //  @Test

}
