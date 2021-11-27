/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.complexnumberoperations;


import java.text.NumberFormat;
import java.util.Locale;
import org.apache.commons.math3.complex.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luigi
 */
public class ComplexNumberOperationsTest {
    private final ComplexFormat format;
    public ComplexNumberOperationsTest() {
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        format=new ComplexFormat(nf);
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
        c2 = new Complex(-1.5,3.0);
        expResult = "0.5 - 0.9i";
        result = ComplexNumberOperations.add(c1, c2);
        assertEquals(expResult,format.format(result) );
        c1 = new Complex(-5.0,9.9);
        c2 = new Complex(3.5,-5.0);
        expResult = "-1.5 + 4.9i";
        result = ComplexNumberOperations.add(c1, c2);
        assertEquals(expResult,format.format(result) );
        c1 = new Complex(-10.0,-15.0);
        c2 = new Complex(-3.5,-5.0);
        expResult = "-13.5 - 20i";
        result = ComplexNumberOperations.add(c1, c2);
        assertEquals(expResult,format.format(result) );
        
        // TODO review the generated test code and remove the default call to fail.
    }
}