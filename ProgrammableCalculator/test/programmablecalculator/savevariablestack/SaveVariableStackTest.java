/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.savevariablestack;

import java.text.NumberFormat;
import java.util.Locale;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import programmablecalculator.variablearray.NotACharacterException;

/**
 *
 * @author luigi
 */
public class SaveVariableStackTest {
   private final ComplexFormat format;
   private  SaveVariableStack variableStack;
    public SaveVariableStackTest() {
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        nf.setMaximumFractionDigits(8);
        format=new ComplexFormat(nf);
    }
    
    @Before
    public void setUp() {
        variableStack = new SaveVariableStack();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertValue method, of class SaveVariableStack.
     * @throws programmablecalculator.variablearray.NotACharacterException
     */
    @Test
    public void testInsertValue() throws NotACharacterException {
        String variable = "a";
        Complex value = new Complex (5,4);
        variableStack.insertValue(variable.charAt(0), value);
        assertEquals("5 + 4i", format.format(variableStack.getCurrentArray().getValue(variable.charAt(0))));
        variable = "z";
        value = new Complex (3,2);
        variableStack.insertValue(variable.charAt(0), value);
        assertEquals("3 + 2i", format.format(variableStack.getCurrentArray().getValue(variable.charAt(0))));
        variable = "m";
        value = new Complex (8,-5);
        variableStack.insertValue(variable.charAt(0), value);
        assertEquals("8 - 5i", format.format(variableStack.getCurrentArray().getValue(variable.charAt(0))));
    }
    
    
    @Test
    public void testGetValue() throws NotACharacterException {
        String variable = "a";
        Complex value = new Complex (5,4);
        variableStack.insertValue(variable.charAt(0), value);
        assertEquals("5 + 4i",format.format(variableStack.getValue(variable.charAt(0))));

        variable = "z";
        value = new Complex (5,4);
        variableStack.insertValue(variable.charAt(0), value);
        assertEquals("5 + 4i",format.format(variableStack.getValue(variable.charAt(0))));

        variable = "m";
        value = new Complex (8,-5);
        variableStack.insertValue(variable.charAt(0), value);
        assertEquals("8 - 5i",format.format(variableStack.getValue(variable.charAt(0))));
    }

    
}
