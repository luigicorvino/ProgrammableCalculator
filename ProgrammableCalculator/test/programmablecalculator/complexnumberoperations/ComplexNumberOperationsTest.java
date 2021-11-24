/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.complexnumberoperations;


import org.apache.commons.math3.complex.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import programmablecalculator.complexnumberoperations.ComplexNumberOperations;
import programmablecalculator.complexnumberoperations.ComplexNumberOperations;
import static org.junit.Assert.*;

/**
 *
 * @author luigi
 */
public class ComplexNumberOperationsTest {
    private ComplexFormat format=new ComplexFormat();
    public ComplexNumberOperationsTest() {
    }
    
    @Test
    public void testAdd() {
        System.out.println("add");
        Complex c1 = new Complex(3.5,2.2);
        Complex c2 = new Complex(6.5,4.8);
        String expResult = "10 + 7i";
        Complex result = ComplexNumberOperations.add(c1, c2);
        assertEquals(expResult,(format.format(result)) );
        c1 = new Complex(2.0,-3.9);
        c2 = new Complex(3.5,3.0);
        expResult = "5,5 - 0,9i";
        result = ComplexNumberOperations.add(c1, c2);
        assertEquals(expResult,format.format(result) );
        
        // TODO review the generated test code and remove the default call to fail.
    }
}