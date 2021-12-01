/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.variablearray;

import java.text.NumberFormat;
import java.util.Locale;
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
    public VariableArray variables;
    private final ComplexFormat format;
    public VariableArrayTest() {
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        nf.setMaximumFractionDigits(8);
        format=new ComplexFormat(nf);
    }
    
    @Before
    public void setUp() {
        variables = new VariableArray();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIndex method, of class VariableArray.
     */
    @Test
    public void testGetIndex() {
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
    
}
