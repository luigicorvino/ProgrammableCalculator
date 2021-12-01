/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.numbersstack;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import programmablecalculator.numbersstack.NumbersStack;
import programmablecalculator.numbersstack.NumbersStack;
import static org.junit.Assert.*;



/**
 *
 * @author luigi
 */
public class NumbersStackTest {
    public NumbersStack stack;
    private final ComplexFormat format;
    public NumbersStackTest() {
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        format=new ComplexFormat(nf);
    }
    @Before
    public void setUp() {
        stack=new NumbersStack();
    }

    @Test
    public void testPush() {
        System.out.println("push");
        assertTrue(stack.isEmpty());
        //Test case 1
        Complex c1=new Complex(3,5);
        stack.push(c1);
        assertFalse(stack.isEmpty());
        assertTrue(stack.contains(c1));
        //Test case 2
        Complex c2=new Complex(2.7,8.4);
        stack.push(c2);
        assertTrue(stack.contains(c1));
        assertTrue(stack.contains(c2));
    }
    
    //Test empty stack exception
    @Test(expected=NoSuchElementException.class)
    public void testEmptyStack(){
       stack.pop();
    }
    
    @Test
    public void testPop(){
        //Test case 1
        stack.push(new Complex(5,7));
        assertEquals("5 + 7i",format.format(stack.pop()));
        assertTrue(stack.isEmpty());
        //Test case 2
        stack.push(new Complex(10,2));
        stack.push(new Complex(4.5,7));
        assertEquals("4.5 + 7i",format.format(stack.pop()));
        assertFalse(stack.isEmpty());
        assertEquals("10 + 2i",format.format(stack.pop()));
        assertTrue(stack.isEmpty());
    }
    
    
    
    
    
    
}
