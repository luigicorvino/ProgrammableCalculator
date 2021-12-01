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
        nf.setMaximumFractionDigits(8);
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
        System.out.println("pop");
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
    
    
    @Test
    public void testClear(){
        System.out.println("clear");
        stack.push(new Complex(2,3));
        stack.push(new Complex(4,5));
        stack.push(new Complex(1,2));
        assertEquals(stack.size(),3);
        stack.clear();
        assertTrue(stack.isEmpty());
    }
    
    
    @Test
    public void testDrop(){
        System.out.println("drop");        
        stack.push(new Complex(2,3));
        stack.push(new Complex(4,5));
        stack.push(new Complex(1,2)); 
        assertEquals("1 + 2i", format.format(stack.getFirst()));
        stack.drop();
        assertEquals("4 + 5i", format.format(stack.getFirst()));
        stack.drop();
        assertEquals("2 + 3i",format.format(stack.getFirst()));
        stack.drop();
        assertTrue(stack.isEmpty());
    }
    
    @Test
    public void testDup(){
        System.out.println("dup");        
        stack.push(new Complex(2,3));
        stack.dup();
        assertTrue(stack.size()==2);
        assertEquals("2 + 3i", format.format(stack.pop()));
        assertTrue(stack.size()==1);
        assertEquals("2 + 3i", format.format(stack.pop()));
        assertTrue(stack.isEmpty());
        stack.push(new Complex(5,4));
        stack.dup();
        stack.push(new Complex(3,2));
        stack.dup();
        assertTrue(stack.size()==4);
        assertEquals("3 + 2i", format.format(stack.pop()));
        assertEquals("3 + 2i", format.format(stack.pop()));
        assertEquals("5 + 4i", format.format(stack.pop()));
        assertEquals("5 + 4i", format.format(stack.pop()));
        assertTrue(stack.isEmpty());
    }

    @Test (expected=NotEnoughElementsException.class)
    public void testSwapException() throws NotEnoughElementsException{
        System.out.println("swap exception");        
        stack.swap();
        stack.push(new Complex(3,4));
        stack.swap();
    }
    
    @Test
    public void testSwap() throws NotEnoughElementsException{
        System.out.println("swap");
        stack.push(new Complex(2,3));
        stack.push(new Complex(5,4));
        stack.swap();
        assertEquals("2 + 3i", format.format(stack.pop()));
        assertEquals("5 + 4i", format.format(stack.pop()));
        stack.push(new Complex(1,2));
        stack.push(new Complex(3,4));
        stack.push(new Complex(5,6));
        stack.push(new Complex(7,8));
        stack.swap();
        assertEquals("5 + 6i", format.format(stack.pop()));
        stack.swap();
        assertEquals("3 + 4i", format.format(stack.pop()));
        stack.swap();
        assertEquals("1 + 2i", format.format(stack.pop()));
        assertEquals("7 + 8i", format.format(stack.pop()));
    }
    
    
    @Test (expected=NotEnoughElementsException.class)
    public void testOverException() throws NotEnoughElementsException{
        System.out.println("over exception");        
        stack.over();
        stack.push(new Complex(3,4));
        stack.over();
    }
    
    
    @Test
    public void testOver() throws NotEnoughElementsException{
        System.out.println("over");
        stack.push(new Complex(2,3));
        stack.push(new Complex(5,4));
        stack.over();
        assertEquals("2 + 3i", format.format(stack.pop()));
        assertEquals("5 + 4i", format.format(stack.pop()));
        assertEquals("2 + 3i", format.format(stack.pop()));
        stack.push(new Complex(1,2));
        stack.push(new Complex(3,4));
        stack.push(new Complex(5,6));
        stack.push(new Complex(7,8));
        stack.over();
        assertEquals("5 + 6i", format.format(stack.pop()));
        assertEquals("7 + 8i", format.format(stack.pop()));
        stack.over();
        assertEquals("3 + 4i", format.format(stack.pop()));
        assertEquals("5 + 6i", format.format(stack.pop()));
        stack.over();
        assertEquals("1 + 2i", format.format(stack.pop()));
        assertEquals("3 + 4i", format.format(stack.pop()));
        assertEquals("1 + 2i", format.format(stack.pop()));
        assertTrue(stack.isEmpty());
    }

    
    
}
