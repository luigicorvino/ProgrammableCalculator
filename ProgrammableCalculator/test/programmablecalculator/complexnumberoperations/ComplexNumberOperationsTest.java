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
        nf.setMaximumFractionDigits(8);
        format=new ComplexFormat(nf);
    }
    
    @Test
    public void testAdd() {
        System.out.println("add");
        //Test case 1
        Complex c1 = new Complex(3.5,2.2);
        Complex c2 = new Complex(6.5,4.8);
        String expResult = "10 + 7i";
        Complex result = ComplexNumberOperations.add(c1, c2);
        assertEquals(expResult,(format.format(result)) );
        //Test case 2
        c1 = new Complex(2.0,-3.9);
        c2 = new Complex(-1.5,3.0);
        expResult = "0.5 - 0.9i";
        result = ComplexNumberOperations.add(c1, c2);
        assertEquals(expResult,format.format(result) );
        //Test case 3
        c1 = new Complex(-5.0,9.9);
        c2 = new Complex(3.5,-5.0);
        expResult = "-1.5 + 4.9i";
        result = ComplexNumberOperations.add(c1, c2);
        assertEquals(expResult,format.format(result) );
        //Test case 4
        c1 = new Complex(-10.0,-15.0);
        c2 = new Complex(-3.5,-5.0);
        expResult = "-13.5 - 20i";
        result = ComplexNumberOperations.add(c1, c2);
        assertEquals(expResult,format.format(result) );
        
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testSub(){
        System.out.println("sub");
        //Test case 1
        Complex c1 = new Complex(5,7.8);
        Complex c2 = new Complex(6.5,4.8);
        String expResult = "-1.5 + 3i";
        Complex result = ComplexNumberOperations.sub(c1, c2);
        assertEquals(expResult,(format.format(result)) );
        //Test case 2
        c1 = new Complex(8.0,-6.9);
        c2 = new Complex(-1.5,3.0);
        expResult = "9.5 - 9.9i";
        result = ComplexNumberOperations.sub(c1, c2);
        assertEquals(expResult,format.format(result) );
        //Test case 3
        c1 = new Complex(-5.0,10);
        c2 = new Complex(3.5,-5.0);
        expResult = "-8.5 + 15i";
        result = ComplexNumberOperations.sub(c1, c2);
        assertEquals(expResult,format.format(result) );
        //Test case 4
        c1 = new Complex(-10.0,-15.0);
        c2 = new Complex(-3.5,-5.0);
        expResult = "-6.5 - 10i";
        result = ComplexNumberOperations.sub(c1, c2);
        assertEquals(expResult,format.format(result) );    
    }
    
    
        @Test
    public void testMultiply(){
        System.out.println("multiply");
        //Test case 1
        Complex c1 = new Complex(1,2);
        Complex c2 = new Complex(3,4);
        String expResult = "-5 + 10i";
        Complex result = ComplexNumberOperations.multiply(c1, c2);
        assertEquals(expResult,(format.format(result)) );
        //Test case 2
        c1 = new Complex(13.0,-6.9);
        c2 = new Complex(-2.5,2.0);
        expResult = "-18.7 + 43.25i";
        result = ComplexNumberOperations.multiply(c1, c2);
        assertEquals(expResult,format.format(result) );
        //Test case 3
        c1 = new Complex(-5.0,8);
        c2 = new Complex(4.5,-5.0);
        expResult = "17.5 + 61i";
        result = ComplexNumberOperations.multiply(c1, c2);
        assertEquals(expResult,format.format(result) );
        //Test case 4
        c1 = new Complex(-10.0,-10.0);
        c2 = new Complex(-1.5,-5.0);
        expResult = "-35 + 65i";
        result = ComplexNumberOperations.multiply(c1, c2);
        assertEquals(expResult,format.format(result) );    
    }
    
    
    @Test
    public void testSqrt(){
        System.out.println("sqrt");
        //Test case 1
        Complex c=new Complex(3.5,4);
        Complex result=ComplexNumberOperations.sqrt(c);
        String expResult = "2.09941336 + 0.95264708i";
        assertEquals(expResult,format.format(result));
        //Test case 2
        c=new Complex(7,-2);
        result=ComplexNumberOperations.sqrt(c);
        expResult = "2.67208812 - 0.37423915i";
        assertEquals(expResult,format.format(result));   
        //Test case 3
        c=new Complex(-0.5,10);
        result=ComplexNumberOperations.sqrt(c);
        expResult = "2.18088195 + 2.29265045i";
        assertEquals(expResult,format.format(result));  
        //Test case 4
        c=new Complex(-15,-20.5);
        result=ComplexNumberOperations.sqrt(c);
        expResult = "2.28054507 - 4.49453955i";
        assertEquals(expResult,format.format(result));  
        
    }
    
    @Test
    public void testInvert(){
        System.out.println("invert");
        //Test case 1
        Complex c=new Complex(3.5,4);
        Complex result=ComplexNumberOperations.invert(c);
        String expResult = "-3.5 - 4i";
        assertEquals(expResult,format.format(result));
        //Test case 2
        c=new Complex(7,-2);
        result=ComplexNumberOperations.invert(c);
        expResult = "-7 + 2i";
        assertEquals(expResult,format.format(result));   
        //Test case 3
        c=new Complex(-0.5,10);
        result=ComplexNumberOperations.invert(c);
        expResult = "0.5 - 10i";
        assertEquals(expResult,format.format(result));  
        //Test case 4
        c=new Complex(-15,-20.5);
        result=ComplexNumberOperations.invert(c);
        expResult = "15 + 20.5i";
        assertEquals(expResult,format.format(result));  
        
    }    
    
    
}