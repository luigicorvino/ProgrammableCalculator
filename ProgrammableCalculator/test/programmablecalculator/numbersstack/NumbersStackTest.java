/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.numbersstack;

import org.apache.commons.math3.complex.Complex;
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
    public NumbersStackTest() {
    }
    @Before
    public void setUp() {
        stack=new NumbersStack();
    }

    @Test
    public void testPush() {
        System.out.println("push");
        Complex c1=new Complex(3,5);
        assertTrue(stack.isEmpty());
        stack.push(c1);
        assertFalse(stack.isEmpty());
        assertTrue(stack.contains(c1));
        Complex c2=new Complex(2.7,8.4);
        stack.push(c2);
        assertTrue(stack.contains(c1));
        assertTrue(stack.contains(c2));
    }
    
    
    
    
    
    
}
