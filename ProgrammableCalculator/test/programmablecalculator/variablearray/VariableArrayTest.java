/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.variablearray;

import java.text.NumberFormat;
import java.util.ArrayList;
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
 * @author luigi
 */
public class VariableArrayTest {

    private class VariableArrayStub extends VariableArray{
    
        public VariableArrayStub(){
            super();
        }
    
    }
    
    private VariableArrayStub variables;
    private final ComplexFormat format;
    public VariableArrayTest() {
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        nf.setMaximumFractionDigits(8);
        format=new ComplexFormat(nf);
    }
    
    @Before
    public void setUp() {
        variables = new VariableArrayStub();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIndex method, of class VariableArray.
     * @throws programmablecalculator.variablearray.NotACharacterException
     */
    @Test (expected=NotACharacterException.class)
    public void getIndexExceptionTest() throws NotACharacterException{
        variables.getIndex("-".charAt(0));
    }    
       
    
 
    @Test
    public void testGetIndex() throws NotACharacterException{
        String variable = "a";
        int expResult = 0;
        int result = variables.getIndex(variable.charAt(0));
        assertEquals(expResult, result);
        variable = "z";
        expResult = 25;
        result = variables.getIndex(variable.charAt(0));
        assertEquals(expResult, result);
        variable = "m";
        expResult = 12;
        result = variables.getIndex(variable.charAt(0));
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test (expected=NotACharacterException.class)
    public void insertValueExceptionTest() throws NotACharacterException{
        variables.insertValue("-".charAt(0),new Complex(1,1));
    }    
    
    
    @Test
    public void insertValueTest() throws NotACharacterException{
        String variable = "a";
        int index = variables.getIndex(variable.charAt(0));
        Complex value = new Complex (5,4);
        variables.insertValue(variable.charAt(0), value);
        assertEquals("5 + 4i", format.format(variables.getVariables()[index]));
        variable = "z";
        index = variables.getIndex(variable.charAt(0));
        value = new Complex (3,2);
        variables.insertValue(variable.charAt(0), value);
        assertEquals("3 + 2i", format.format(variables.getVariables()[index]));
        variable = "m";
        index = variables.getIndex(variable.charAt(0));
        value = new Complex (8,-5);
        variables.insertValue(variable.charAt(0), value);
        assertEquals("8 - 5i", format.format(variables.getVariables()[index]));
    }
    
    
    @Test (expected=NotACharacterException.class)
    public void getValueExceptionTest() throws NotACharacterException{
        variables.getValue("-".charAt(0));
    }
    
    
    @Test
    public void getValueTest() throws NotACharacterException{
        String variable = "a";
        Complex value = new Complex (5,4);
        variables.insertValue(variable.charAt(0), value);
        assertEquals("5 + 4i",format.format(variables.getValue(variable.charAt(0))));

        variable = "z";
        value = new Complex (5,4);
        variables.insertValue(variable.charAt(0), value);
        assertEquals("5 + 4i",format.format(variables.getValue(variable.charAt(0))));

        variable = "m";
        value = new Complex (8,-5);
        variables.insertValue(variable.charAt(0), value);
        assertEquals("8 - 5i",format.format(variables.getValue(variable.charAt(0))));
    }
}
