/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrammableCalculatorControllerTest;

import ProgrammableCalculatorController.ProgrammableCalculatorController;
import java.util.Iterator;
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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testelaborateInputComplexNumber1() {
        controller.elaborateInput("5+2i");
        Complex c =  controller.topNumberStack();
        ComplexFormat format = new ComplexFormat();
        assertEquals(format.format(c), "5 + 2i");
    }
    
    @Test
    public void testelaborateInputComplexNumber2() {
        controller.elaborateInput("6");
        Complex c =  controller.topNumberStack();
        ComplexFormat format = new ComplexFormat();
        assertEquals(format.format(c), "6");
    }
    
    @Test
    public void testelaborateInputComplexNumber3() {
        controller.elaborateInput("-6");
        Complex c =  controller.topNumberStack();
        ComplexFormat format = new ComplexFormat();
        assertEquals(format.format(c), "-6");
    }
    
    @Test
    public void testelaborateInputComplexNumber4() {
        controller.elaborateInput("0-5i");
        Complex c =  controller.topNumberStack();
        ComplexFormat format = new ComplexFormat();
        assertEquals(format.format(c), "0 - 5i");
    }
    
    @Test
    public void testelaborateInputComplexNumber5() {
        controller.elaborateInput("0+1i");
        Complex c =  controller.topNumberStack();
        ComplexFormat format = new ComplexFormat();
        System.out.print(format.format(c));
        assertEquals(format.format(c), "0 + i");
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
