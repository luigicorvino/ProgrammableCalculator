/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.programmablecalculatorcontroller;

import programmablecalculator.programmablecalculatorcontroller.ProgrammableCalculatorController;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import programmablecalculator.userdefinedoperations.NoSuchOperationException;
import programmablecalculator.userdefinedoperations.OperationAlreadyExistsException;
import programmablecalculator.variablearray.NotACharacterException;

/**
 *
 * @author lex99
 */
public class ProbrammableCalculatorControllerTest {
    
    private class ControllerStub extends ProgrammableCalculatorController {
        
        public ControllerStub() {
            super();
        }
        
        public Complex topNumberStack() {
            return super.getNumberStack().peekFirst();
        }
        
        public Complex popNumberStack() {
            return super.getNumberStack().pop();
        }
        
        
        
    }
    
    ControllerStub controller;
    ComplexFormat format;
    
    public ProbrammableCalculatorControllerTest() {
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        nf.setMaximumFractionDigits(8);
        format=new ComplexFormat(nf);
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
    public void testDoAdd(){
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
    public void testDoSub(){
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
    public void testDoMultiply(){
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
    public void testDoDivide() {
        Complex c;
        
        assertEquals("There aren't 2 complex numbers to divide ",controller.elaborateInput("/"));
        controller.elaborateInput("20+4i");
        controller.elaborateInput("0+2i");
        controller.elaborateInput("/");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "2 - 10i");
        
        controller.elaborateInput("0+50i");
        controller.elaborateInput("2+2i");
        controller.elaborateInput("/");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "12.5 + 12.5i");
        
    }
    
    @Test
    public void testDoInvertSign() {
        Complex c;
        
        assertEquals("There isn't one complex numbers to invert sign ",controller.elaborateInput("+-"));
        controller.elaborateInput("20+4i");
        controller.elaborateInput("+-");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "-20 - 4i");
        
        controller.elaborateInput("0+50i");
        controller.elaborateInput("+-");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "-0 - 50i");
        
        controller.elaborateInput("-4");
        controller.elaborateInput("+-");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "4");
        
        controller.elaborateInput("0-9i");
        controller.elaborateInput("+-");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "-0 + 9i");
        
        controller.elaborateInput("12-9i");
        controller.elaborateInput("+-");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "-12 + 9i");
    }
    
    @Test
    public void testDoSqrt() {
        Complex c;
        
        assertEquals("There isn't one complex numbers to sqrt ",controller.elaborateInput("sqrt"));
        controller.elaborateInput("25+4i");
        controller.elaborateInput("sqrt");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "5.01587369 + 0.39873412i");
        
        controller.elaborateInput("21-6i");
        controller.elaborateInput("sqrt");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "4.62819239 - 0.64820123i");
        
        controller.elaborateInput("-4");
        controller.elaborateInput("sqrt");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "0 + 2i");
    }
    
    @Test
    public void testDoDrop() {
        Complex c;
        
        assertEquals("There isn't one complex numbers to drop ",controller.elaborateInput("drop"));
        controller.elaborateInput("5-5i");
        controller.elaborateInput("drop");
        c = controller.topNumberStack();
        assertEquals(c, null);
        
        controller.elaborateInput("-4");
        controller.elaborateInput("drop");
        c = controller.topNumberStack();
        assertEquals(c, null);
        
        controller.elaborateInput("-4+8i");
        controller.elaborateInput("3+9i");
        controller.elaborateInput("drop");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "-4 + 8i");
    }
    
    @Test
    public void testDoDup() {
        Complex c;
        
        assertEquals("There isn't one complex numbers to dup ",controller.elaborateInput("dup"));
        controller.elaborateInput("3-9i");
        controller.elaborateInput("dup");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "3 - 9i");
        c = controller.popNumberStack();
        assertEquals(format.format(c), "3 - 9i");
        
        controller.elaborateInput("-4+123i");
        controller.elaborateInput("dup");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "-4 + 123i");
        c = controller.popNumberStack();
        assertEquals(format.format(c), "-4 + 123i");
        
        controller.elaborateInput("43+6i");
        controller.elaborateInput("7+9i");
        controller.elaborateInput("dup");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "7 + 9i");
        c = controller.popNumberStack();
        assertEquals(format.format(c), "7 + 9i");
    }
    
    @Test
    public void testDoSwap() {
        Complex c;
        
        assertEquals("There isn't 2 complex numbers to swap ",controller.elaborateInput("swap"));
        controller.elaborateInput("3-9i");
        assertEquals("There isn't 2 complex numbers to swap ",controller.elaborateInput("swap"));
        controller.elaborateInput("4+7i");
        controller.elaborateInput("swap");
        c = controller.popNumberStack();
        assertEquals(format.format(c), "3 - 9i");
        c = controller.popNumberStack();
        assertEquals(format.format(c), "4 + 7i");
        
        controller.elaborateInput("-9+2i");
        controller.elaborateInput("6+8i");
        controller.elaborateInput("swap");
        c = controller.popNumberStack();
        assertEquals(format.format(c), "-9 + 2i");
        c = controller.popNumberStack();
        assertEquals(format.format(c), "6 + 8i");
    }
    
    
    @Test
    public void testDoOver() {
        Complex c;
        
        assertEquals("There isn't 2 complex numbers to swap ",controller.elaborateInput("swap"));
        controller.elaborateInput("-21-9i");
        assertEquals("There isn't 2 complex numbers to swap ",controller.elaborateInput("swap"));
        controller.elaborateInput("6+7i");
        controller.elaborateInput("over");
        c = controller.popNumberStack();
        assertEquals(format.format(c), "-21 - 9i");
        
        controller.elaborateInput("0+9i");
        controller.elaborateInput("33-3i");
        controller.elaborateInput("over");
        c = controller.popNumberStack();
        assertEquals(format.format(c), "0 + 9i");
    }
    
    
    @Test
    public void testDoPushVariableStack() throws NotACharacterException {
        Complex c;
        
        assertEquals("There isn't a complex number in the stack",controller.elaborateInput(">x"));
        controller.elaborateInput("7+4i");
        controller.elaborateInput(">m");
        c = controller.variableStack.getValue('m');
        assertEquals(format.format(c), "7 + 4i");
        
        controller.elaborateInput("0+5i");
        controller.elaborateInput(">b");
        c = controller.variableStack.getValue('b');
        assertEquals(format.format(c), "0 + 5i");
        
        controller.elaborateInput("3+4i");
        controller.elaborateInput(">v");
        c = controller.variableStack.getValue('v');
        assertEquals(format.format(c), "3 + 4i");
    }
    
    @Test
    public void testDoTakeVariableStakeAndPushNumberStack() throws NotACharacterException {
        Complex c;
        
        assertEquals("The variable 'f' haven't a value",controller.elaborateInput("<f"));
        controller.elaborateInput("7+4i");
        controller.elaborateInput(">f");
        controller.variableStack.getValue('f');
        controller.elaborateInput("3+8i");
        controller.elaborateInput("<f");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "7 + 4i");
        
        controller.elaborateInput("5+6i");
        controller.elaborateInput(">f");
        controller.variableStack.getValue('f');
        controller.elaborateInput("0+8i");
        controller.elaborateInput("<f");
        c = controller.topNumberStack();
        assertEquals(format.format(c), "5 + 6i");
        
    }
    
    @Test
    public void testDoAddNumberStackAndVariable() throws NotACharacterException {
        Complex c;
        
        assertEquals("The variable 'f' haven't a value",controller.elaborateInput("+f"));
        controller.elaborateInput("7+4i");
        controller.elaborateInput(">f");
//        controller.popNumberStack();
        assertEquals("There isn't a complex number in the stack",controller.elaborateInput("+f"));
        controller.elaborateInput("9+4i");
        controller.elaborateInput("+f");
        c = controller.variableStack.getValue('f');
        assertEquals(format.format(c), "16 + 8i");
        
        controller.elaborateInput("3+2i");
        controller.elaborateInput(">l");
        //controller.popNumberStack();
        controller.elaborateInput("10-10i");
        controller.elaborateInput("+l");
        c = controller.variableStack.getValue('l');
        assertEquals(format.format(c), "13 - 8i");
        
        controller.elaborateInput("3-2i");
        controller.elaborateInput(">u");
        //controller.popNumberStack();
        controller.elaborateInput("-10-10i");
        controller.elaborateInput("+u");
        c = controller.variableStack.getValue('u');
        assertEquals(format.format(c), "-7 - 12i");
        
    }
    
    @Test
    public void testDoSubNumberStackAndVariable() throws NotACharacterException {
        Complex c;
        
        assertEquals("The variable 'f' haven't a value",controller.elaborateInput("+f"));
        controller.elaborateInput("7+4i");
        controller.elaborateInput(">f");
        assertEquals("There isn't a complex number in the stack",controller.elaborateInput("+f"));
        controller.elaborateInput("9+4i");
        controller.elaborateInput("-f");
        c = controller.variableStack.getValue('f');
        assertEquals(format.format(c), "2");
        
        controller.elaborateInput("3+2i");
        controller.elaborateInput(">l");
        controller.elaborateInput("10-10i");
        controller.elaborateInput("-l");
        c = controller.variableStack.getValue('l');
        assertEquals(format.format(c), "7 - 12i");
        
        controller.elaborateInput("3-2i");
        controller.elaborateInput(">u");
        controller.elaborateInput("-10-10i");
        controller.elaborateInput("-u");
        c = controller.variableStack.getValue('u');
        assertEquals(format.format(c), "-13 - 8i");
        
    }
    
    @Test
    public void testDoSaveAndRestore() throws NotACharacterException {
        Complex c1, c2, c3, c4;
        
        controller.elaborateInput("1+2i");
        controller.elaborateInput(">a");
        controller.elaborateInput("5+6i");
        controller.elaborateInput(">z");
        controller.elaborateInput("3+4i");
        controller.elaborateInput(">m");
        controller.elaborateInput("save");
        controller.elaborateInput("8+6i");
        controller.elaborateInput(">a");
        controller.elaborateInput("7+9i");
        controller.elaborateInput(">m");
        controller.elaborateInput("restore");
        c1 = controller.variableStack.getValue('a');
        c2 = controller.variableStack.getValue('z');
        c3 = controller.variableStack.getValue('m');
        assertEquals(format.format(c1), "1 + 2i");
        assertEquals(format.format(c2), "5 + 6i");
        assertEquals(format.format(c3), "3 + 4i");
        
        controller.elaborateInput("1+2i");
        controller.elaborateInput(">h");
        controller.elaborateInput("5+6i");
        controller.elaborateInput(">b");
        controller.elaborateInput("3+4i");
        controller.elaborateInput(">n");
        controller.elaborateInput("save");
        controller.elaborateInput("8+6i");
        controller.elaborateInput(">h");
        controller.elaborateInput("7+9i");
        controller.elaborateInput(">b");
        controller.elaborateInput("+n");
        controller.elaborateInput("4+7i");
        controller.elaborateInput(">v");
        controller.elaborateInput("restore");
        c1 = controller.variableStack.getValue('a');
        c2 = controller.variableStack.getValue('z');
        c3 = controller.variableStack.getValue('m');
        c4 = controller.variableStack.getValue('v');
        assertEquals(format.format(c1), "1 + 2i");
        assertEquals(format.format(c2), "5 + 6i");
        assertEquals(format.format(c3), "3 + 4i");
        assertEquals(c4, null);
        
    }
    
    @Test
    public void testDoCreateUserDefinedOperation() throws NoSuchOperationException {
        List<String> sequence = new LinkedList<>();
        List<String> seq = new LinkedList<>();
        String s;
        
        s = controller.createUserDefinedOperation("operation1", "+ - *");    
        assertEquals(s, null);
        
        controller.createUserDefinedOperation("operation2", "- sqrt swap * dup +");  
        assertEquals(s, null);
        
        s = controller.createUserDefinedOperation("operation1", "* dup +");
        assertEquals("Operation already exists", s);
        
    }
    
    @Test
    public void testDoModifyNameUserDefinedOperation() {
        String s;
        
        controller.createUserDefinedOperation("operation1", "+ swap /");
        s = controller.modifyNameUserDefinedOperation("operation1", "oper");
        assertEquals(s, null);
        
        s = controller.modifyNameUserDefinedOperation("operation1", "jacket");
        assertEquals(s, "Operation operation1 doesn't exist");
        
        controller.createUserDefinedOperation("train", "+ over /");
        s = controller.modifyNameUserDefinedOperation("oper", "train");
        assertEquals(s, "train Operation already exists");
    }
    
    @Test
    public void testDoModifySequenceUserDefinedOperation() {
        String s;
        
        controller.createUserDefinedOperation("operation1", "+ + + /");
        s = controller.modifySequenceUserDefinedOperation("operation1", "swap - + +");
        assertEquals(s, null);
        
        s = controller.modifySequenceUserDefinedOperation("read", "swap +a");
        assertEquals("Operation read doesn't exist", s);
        
        controller.createUserDefinedOperation("operation5", "+ + swap /");
        s = controller.modifySequenceUserDefinedOperation("operation5", "swap - +a +");
        assertEquals(s, null);
        
    }
    
    @Test
    public void testDoGetSequenceUserDefinedOperation() {
        
        controller.createUserDefinedOperation("operation1", "swap / +");
        List<String> sequence = new LinkedList<>();
        sequence.add("swap");
        sequence.add("/");
        assertNotEquals(sequence, controller.getSequenceUserDefinedOperation("operation1"));
        sequence.add("+");
        assertEquals(sequence, controller.getSequenceUserDefinedOperation("operation1"));
        
        assertEquals(null, controller.getSequenceUserDefinedOperation("oper"));
        
    }
    
    
    @Test
    public void testDoInvokeUserDefinedOperation() throws NotACharacterException {
        Complex c;
        
        controller.elaborateInput("operation1");
        controller.createUserDefinedOperation("operation1", "+ + +");
        controller.elaborateInput("3+5i");
        controller.elaborateInput("6-5i");
        controller.elaborateInput("-2+7i");
        controller.elaborateInput("10+5i");
        
        controller.elaborateInput("operation1");
        c = controller.topNumberStack();  
        assertEquals(format.format(c), "17 + 12i");
        
        controller.createUserDefinedOperation("operation2", "operation1 +");
        controller.elaborateInput("3+5i");
        controller.elaborateInput("6-5i");
        controller.elaborateInput("-2+7i");
        controller.elaborateInput("10+5i");
        controller.elaborateInput("operation2");
        c = controller.topNumberStack();  
        assertEquals(format.format(c), "34 + 24i");
        
        controller.createUserDefinedOperation("operation3", "8+6i - >a");
        controller.elaborateInput("operation3");
        c = controller.variableStack.getValue('a');
        assertEquals(format.format(c), "26 + 18i");
        
        
    }
    
   
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
