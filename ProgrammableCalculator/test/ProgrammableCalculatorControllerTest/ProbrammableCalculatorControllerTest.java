/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrammableCalculatorControllerTest;

import ProgrammableCalculatorController.ProgrammableCalculatorController;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lex99
 */
public class ProbrammableCalculatorControllerTest {
    
    private class ControllerStub extends ProgrammableCalculatorController {
        
        public ControllerStub() {
            super();
        }
        
        
        
    }
    
    ControllerStub controller;
    ComplexFormat format;
    
    public ProbrammableCalculatorControllerTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        controller = new ControllerStub();
        format = new ComplexFormat();
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testElaborateInputComplexNumber1() {
        controller.elaborateInput("5+2i");
        Complex c =  controller.topNumberStack();
        assertEquals(format.format(c), "5 + 2i");
    }
    
    @Test
    public void testElaborateInputComplexNumber2() {
        controller.elaborateInput("6");
        Complex c =  controller.topNumberStack();
        assertEquals(format.format(c), "6");
    }
    
    @Test
    public void testElaborateInputComplexNumber3() {
        controller.elaborateInput("-6");
        Complex c =  controller.topNumberStack();
        assertEquals(format.format(c), "-6");
    }
    
    @Test
    public void testElaborateInputComplexNumber4() {
        controller.elaborateInput("0-5i");
        Complex c =  controller.topNumberStack();
        assertEquals(format.format(c), "0 - 5i");
    }
    
    @Test
    public void testElaborateInputComplexNumber5() {
        controller.elaborateInput("0+1i");
        Complex c =  controller.topNumberStack();
        ComplexFormat format = new ComplexFormat();
        assertEquals(format.format(c), "0 + i");
    }
    
    @Test
    public void testElaborateInputSum() {
        Complex c;
        controller.elaborateInput("5+2i");
        controller.elaborateInput("5+2i");
        controller.elaborateInput("+");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "10 + 4i");
        
        controller.elaborateInput("5");
        controller.elaborateInput("5+2i");
        controller.elaborateInput("+");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "10 + 2i");
        
        controller.elaborateInput("5+2i");
        controller.elaborateInput("8-5i");
        controller.elaborateInput("+");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "13 - 3i");
        
        controller.elaborateInput("0+2i");
        controller.elaborateInput("-9-8i");
        controller.elaborateInput("+");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "-9 - 6i");
    }
    
    @Test
    public void testTopNumberStack() {
        controller.elaborateInput("3+4i");
        controller.elaborateInput("7-2i");
        ComplexFormat format = new ComplexFormat();
        assertEquals(format.format(controller.topNumberStack()), "7 - 2i");
    }
    
    

    @Test
    public void testdoAdd(){
        ComplexFormat format = new ComplexFormat();
        //Test Case 1
        assertEquals("There aren't 2 complex numbers to add ",controller.elaborateInput("+"));
        controller.elaborateInput("5+2i");
        assertEquals("There aren't 2 complex numbers to add ",controller.elaborateInput("+"));
        controller.elaborateInput("7+3i");
        controller.elaborateInput("+");
        assertEquals("12 + 5i",format.format(controller.topNumberStack()));
        //Test Case 2
        controller.elaborateInput("-10-8i");
        controller.elaborateInput("+");
        assertEquals("2 - 3i", format.format(controller.topNumberStack()));
        //Test Case 3
        controller.elaborateInput("-4+2i");
        controller.elaborateInput("+");
        assertEquals("-2 - i",format.format(controller.topNumberStack()));
        //Test Case 4
        controller.elaborateInput("-5-2i");
        controller.elaborateInput("+");
        assertEquals("-7 - 3i",format.format(controller.topNumberStack()));
        
        
    }
    
    @Test
    public void testdoSub(){
        ComplexFormat format = new ComplexFormat();
        //Test Case 1
        assertEquals("There aren't 2 complex numbers to sub ",controller.elaborateInput("-"));
        controller.elaborateInput("9+3i");
        assertEquals("There aren't 2 complex numbers to sub ",controller.elaborateInput("-"));
        controller.elaborateInput("7+5i");
        controller.elaborateInput("-");
        assertEquals("2 - 2i",format.format(controller.topNumberStack()));
        //Test Case 2
        assertEquals("There aren't 2 complex numbers to sub ",controller.elaborateInput("-"));
        controller.elaborateInput("1+6i");
        controller.elaborateInput("-");
        assertEquals("1 - 8i", format.format(controller.topNumberStack()));
        //Test Case 3
        assertEquals("There aren't 2 complex numbers to sub ",controller.elaborateInput("-"));
        controller.elaborateInput("4-5i");
        controller.elaborateInput("-");
        assertEquals("-3 - 3i",format.format(controller.topNumberStack()));
        //Test Case 4
        assertEquals("There aren't 2 complex numbers to sub ",controller.elaborateInput("-"));
        controller.elaborateInput("-5-2i");
        controller.elaborateInput("-");
        assertEquals("2 - i",format.format(controller.topNumberStack()));
        
        
    }
    
    
    @Test
    public void testdoMultiply(){
        ComplexFormat format;
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        format=new ComplexFormat(nf);
        //Test Case 1
        assertEquals("There aren't 2 complex numbers to multiply ",controller.elaborateInput("*"));
        controller.elaborateInput("2+3i");
        assertEquals("There aren't 2 complex numbers to multiply ",controller.elaborateInput("*"));
        controller.elaborateInput("4+1i");
        controller.elaborateInput("*");
        assertEquals("5 + 14i",format.format(controller.topNumberStack()));
        //Test Case 2
        assertEquals("There aren't 2 complex numbers to multiply ",controller.elaborateInput("*"));
        controller.elaborateInput("-0.5+0.2i");
        controller.elaborateInput("*");
        assertEquals("-5.3 - 6i", format.format(controller.topNumberStack()));
        //Test Case 3
        assertEquals("There aren't 2 complex numbers to multiply ",controller.elaborateInput("*"));
        controller.elaborateInput("2-7i");
        controller.elaborateInput("*");
        assertEquals("-52.6 + 25.1i",format.format(controller.topNumberStack()));
        //Test Case 4
        controller.elaborateInput("-0.2-1i");
        controller.elaborateInput("*");
        assertEquals("35.62 + 47.58i",format.format(controller.topNumberStack()));
        
        
    }
    
    @Test
    public void testdoDivide() {
        Complex c;
        ComplexFormat format;
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        format=new ComplexFormat(nf);
        
        assertEquals("There aren't 2 complex numbers to divide ",controller.elaborateInput("/"));
        controller.elaborateInput("20 +4i");
        controller.elaborateInput("0 + 2i");
        controller.elaborateInput("/");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "2 - 10i");
        
        controller.elaborateInput("0 + 50i");
        controller.elaborateInput("2 + 2i");
        controller.elaborateInput("/");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "12.5 + 12.5i");
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
