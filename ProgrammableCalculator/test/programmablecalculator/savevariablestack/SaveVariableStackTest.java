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

    
    @Test
    public void testSave() throws NotACharacterException{
        System.out.println("save");
        variableStack.insertValue("a".charAt(0), new Complex (1,2));
        variableStack.insertValue("z".charAt(0), new Complex (5,6));
        variableStack.insertValue("m".charAt(0), new Complex (3,4));
        variableStack.save();
        assertEquals("1 + 2i",format.format(variableStack.getVariableStack().getFirst().getValue("a".charAt(0))));
        assertEquals("5 + 6i",format.format(variableStack.getVariableStack().getFirst().getValue("z".charAt(0))));
        assertEquals("3 + 4i",format.format(variableStack.getVariableStack().getFirst().getValue("m".charAt(0))));
        variableStack.insertValue("a".charAt(0), new Complex (7,8));
        variableStack.insertValue("z".charAt(0), new Complex (11,12));
        variableStack.insertValue("m".charAt(0), new Complex (9,10));
        variableStack.save();
        assertEquals("7 + 8i",format.format(variableStack.getVariableStack().getFirst().getValue("a".charAt(0))));
        assertEquals("11 + 12i",format.format(variableStack.getVariableStack().getFirst().getValue("z".charAt(0))));
        assertEquals("9 + 10i",format.format(variableStack.getVariableStack().getFirst().getValue("m".charAt(0))));
        variableStack.getVariableStack().pop();
        assertEquals("1 + 2i",format.format(variableStack.getVariableStack().getFirst().getValue("a".charAt(0))));
        assertEquals("5 + 6i",format.format(variableStack.getVariableStack().getFirst().getValue("z".charAt(0))));
        assertEquals("3 + 4i",format.format(variableStack.getVariableStack().getFirst().getValue("m".charAt(0))));
        variableStack.getVariableStack().pop();
        assertTrue(variableStack.getVariableStack().isEmpty());
    }
    
    
    @Test
    public void testRestoreEmptyStack() throws NotACharacterException{
        assertFalse(variableStack.restore());
        variableStack.insertValue("a".charAt(0), new Complex(1,2));
        variableStack.save();
        assertTrue(variableStack.restore());
        assertFalse(variableStack.restore());
    }
    
    
    @Test
    public void testRestore() throws NotACharacterException {
        System.out.println("restore");
        variableStack.insertValue("a".charAt(0), new Complex (1,2));
        variableStack.insertValue("z".charAt(0), new Complex (5,6));
        variableStack.insertValue("m".charAt(0), new Complex (3,4));
        variableStack.save();
        variableStack.insertValue("a".charAt(0), new Complex (7,8));
        variableStack.insertValue("z".charAt(0), new Complex (11,12));
        variableStack.insertValue("m".charAt(0), new Complex (9,10));
        variableStack.save();
        variableStack.insertValue("a".charAt(0), new Complex (13,14));
        variableStack.insertValue("z".charAt(0), new Complex (17,18));
        variableStack.insertValue("m".charAt(0), new Complex (15,16));
        assertEquals("13 + 14i",format.format(variableStack.getValue("a".charAt(0))));
        assertEquals("17 + 18i",format.format(variableStack.getValue("z".charAt(0))));
        assertEquals("15 + 16i",format.format(variableStack.getValue("m".charAt(0))));
        variableStack.restore();
        assertEquals("7 + 8i",format.format(variableStack.getValue("a".charAt(0))));
        assertEquals("11 + 12i",format.format(variableStack.getValue("z".charAt(0))));
        assertEquals("9 + 10i",format.format(variableStack.getValue("m".charAt(0))));
        variableStack.restore();
        assertEquals("1 + 2i",format.format(variableStack.getValue("a".charAt(0))));
        assertEquals("5 + 6i",format.format(variableStack.getValue("z".charAt(0))));
        assertEquals("3 + 4i",format.format(variableStack.getValue("m".charAt(0))));
        assertTrue(variableStack.getVariableStack().isEmpty());
    }
}
