/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.programmablecalculatorgui;

import javax.swing.DefaultListModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bad-b
 */

public class ProgrammableCalculatorGUITest {
    private ProgrammableCalculatorGUI calculator;
    private DefaultListModel<String> modelVisibleStack;
    private DefaultListModel<String> modelVariables;
    private DefaultListModel<String> modelUserDefinedOperation;
    
    public  ProgrammableCalculatorGUITest(){
        calculator=new ProgrammableCalculatorGUI();
        modelVisibleStack=new DefaultListModel<>();
        modelVariables=new DefaultListModel<>();
        modelUserDefinedOperation= new DefaultListModel<>();
    }
  
    @Test 
    public void TestCheckInputField() {
        
        assertFalse("5.2-2.1u".equals(calculator.checkInputField("5.2-2.1u")));
        assertTrue("5.2+2.1i".equals(calculator.checkInputField("5.2+2.1i")));
        assertFalse("-5.2-2.1u".equals(calculator.checkInputField("-5.2-2.1u")));
        assertTrue("-5.2-2.1i".equals(calculator.checkInputField("-5.2-2.1i")));
        assertFalse("5.2+2.1u".equals(calculator.checkInputField("5.2+2.1u")));
        assertTrue("5.2+2.1i".equals(calculator.checkInputField("5.2+2.1i")));
        assertTrue("50".equals(calculator.checkInputField("50")));
        assertTrue("-50.0".equals(calculator.checkInputField("-50.0")));
        assertFalse("50u".equals(calculator.checkInputField("50u")));
        assertTrue("0.0+50.0i".equals(calculator.checkInputField("50.0i")));
        assertFalse("g50.0i".equals(calculator.checkInputField("g50.0i")));
        assertFalse("50g.0i".equals(calculator.checkInputField("50g.0i")));
        assertFalse("5g0.0i".equals(calculator.checkInputField("5g0.0i")));
        assertFalse("50.0+g1.0i".equals(calculator.checkInputField("50.0+g1.0i")));
        assertFalse("50.0+1.g0i".equals(calculator.checkInputField("50.0+1.g0i")));
        assertFalse("50.0+1.0gi".equals(calculator.checkInputField("50.0+1.0gi")));
        assertFalse("<ai".equals(calculator.checkInputField("<ai")));
        assertTrue("<b".equals(calculator.checkInputField("<b")));
        assertFalse(">ai".equals(calculator.checkInputField(">ai")));
        assertFalse("+ai".equals(calculator.checkInputField("+ai")));
        assertFalse("-ai".equals(calculator.checkInputField("-ai")));
        assertTrue(">b".equals(calculator.checkInputField(">b")));
        assertTrue("+b".equals(calculator.checkInputField("+b")));
        assertTrue("-b".equals(calculator.checkInputField("-b")));
}
 
    @Test
 public void TestCheckOperationStatusAndUpdate(){
     
     calculator.checkOperationStatusAndUpdate("1+2i");
     modelVisibleStack=calculator.getModelVisibleStack();
     modelVariables=calculator.getModelVariableArray();
     modelUserDefinedOperation=calculator.getModelUserDefinedOperationVisualizer();
     assertTrue(modelVisibleStack.get(0).equals("1 + 2i"));
     calculator.checkOperationStatusAndUpdate("1-2i");
     assertTrue(modelVisibleStack.get(0).equals("1 - 2i"));
     calculator.checkOperationStatusAndUpdate("*");
     assertTrue(modelVisibleStack.get(0).equals("5"));
     calculator.checkOperationStatusAndUpdate("7-2i");
     calculator.checkOperationStatusAndUpdate("/");
     assertTrue(modelVisibleStack.get(0).equals("0.66037736 + 0.18867925i"));
     calculator.checkOperationStatusAndUpdate("1+2i");
     calculator.checkOperationStatusAndUpdate("+");
     assertTrue(modelVisibleStack.get(0).equals("1.66037736 + 2.18867925i"));
     calculator.checkOperationStatusAndUpdate("1+2i");
     calculator.checkOperationStatusAndUpdate("-");
     assertTrue(modelVisibleStack.get(0).equals("0.66037736 + 0.18867925i"));
     calculator.checkOperationStatusAndUpdate("+-");
     assertTrue(modelVisibleStack.get(0).equals("-0.66037736 - 0.18867925i"));
     calculator.checkOperationStatusAndUpdate("sqrt");
     assertTrue(modelVisibleStack.get(0).equals("0.11494664 - 0.82072534i"));
     calculator.checkOperationStatusAndUpdate("clear");
     assertTrue(modelVisibleStack.isEmpty());
     calculator.checkOperationStatusAndUpdate("1+2i");
     calculator.checkOperationStatusAndUpdate("drop");
     assertTrue(modelVisibleStack.isEmpty());
     calculator.checkOperationStatusAndUpdate("1+2i");
     calculator.checkOperationStatusAndUpdate("dup");
     assertTrue(modelVisibleStack.get(0).equals("1 + 2i"));
     assertTrue(modelVisibleStack.get(1).equals("1 + 2i"));
     calculator.checkOperationStatusAndUpdate("1-2i");
     calculator.checkOperationStatusAndUpdate("swap");
     assertTrue(modelVisibleStack.get(0).equals("1 + 2i"));
     calculator.checkOperationStatusAndUpdate("over");
     assertTrue(modelVisibleStack.get(0).equals("1 - 2i"));
     
     calculator.checkOperationStatusAndUpdate(">a");
     assertTrue(modelVariables.get(0).equals("a: 1 - 2i"));
     calculator.checkOperationStatusAndUpdate("<a");
     assertTrue(modelVisibleStack.get(0).equals("1 - 2i"));
     calculator.checkOperationStatusAndUpdate("+a");
     assertTrue(modelVariables.get(0).equals("a: 2 - 4i"));
     calculator.checkOperationStatusAndUpdate("<a");
     assertTrue(modelVisibleStack.get(0).equals("2 - 4i"));
     calculator.checkOperationStatusAndUpdate("-a");
     calculator.checkOperationStatusAndUpdate("<a");
     assertTrue(modelVisibleStack.get(0).equals("0"));
     calculator.checkOperationStatusAndUpdate("save");
     calculator.checkOperationStatusAndUpdate("1-2i");
     assertTrue(modelVisibleStack.get(0).equals("1 - 2i"));
     calculator.checkOperationStatusAndUpdate("restore");
     calculator.checkOperationStatusAndUpdate("<a");
     assertTrue(modelVisibleStack.get(0).equals("0"));
     calculator.checkOperationStatusAndUpdate(">v");
     assertTrue(modelVariables.get(1).equals("v: 0"));
     calculator.checkOperationStatusAndUpdate("1-2i");
     calculator.checkOperationStatusAndUpdate("-v");
     assertTrue(modelVariables.get(1).equals("v: 1 - 2i"));
     calculator.controller.createUserDefinedOperation("add", "2+i 1 +");
     calculator.updateUserDefinedOperationsVisualizer();
     assertTrue(modelUserDefinedOperation.get(1).startsWith("add"));
     calculator.controller.modifyNameUserDefinedOperation("add", "sub");
     calculator.updateUserDefinedOperationsVisualizer();
     assertTrue(modelUserDefinedOperation.get(1).startsWith("sub"));
     calculator.controller.modifySequenceUserDefinedOperation("add", "2+i 1 *");
     calculator.updateUserDefinedOperationsVisualizer();
     assertFalse(modelUserDefinedOperation.get(1).contains("2+i 1 *"));
     calculator.controller.modifySequenceUserDefinedOperation("sub", "2+i sqrt");
     calculator.updateUserDefinedOperationsVisualizer();
     assertTrue(modelUserDefinedOperation.get(1).contains("sub:2+i sqrt"));
     calculator.controller.deleteSequenceUserDefinedOperation("add");
     calculator.updateUserDefinedOperationsVisualizer();
     assertTrue(modelUserDefinedOperation.get(1).startsWith("sub"));
     calculator.controller.deleteSequenceUserDefinedOperation("sub");
     calculator.updateUserDefinedOperationsVisualizer();
     assertTrue(modelUserDefinedOperation.getSize()==1);
     modelVisibleStack.clear();
     calculator.controller.createUserDefinedOperation("add", "2+1.0i 1 +");
     calculator.checkOperationStatusAndUpdate("add");
     assertTrue(modelVisibleStack.get(0).equals("3 + i"));
     calculator.controller.createUserDefinedOperation("add2", "add 2 +");
     calculator.checkOperationStatusAndUpdate("add2");
     assertTrue(modelVisibleStack.get(0).equals("5 + i"));
     
     
    }

    @Test
public void TestCheckOperationSequence(){
    assertFalse(calculator.checkOperationSequence("5.2-2.1u")!=null);
    assertTrue(calculator.checkOperationSequence("5.2+2.1i").equals("5.2+2.1i "));
    assertFalse(calculator.checkOperationSequence("-5.2-2.1u")!=null);
    assertTrue(calculator.checkOperationSequence("-5.2-2.1i").equals("-5.2-2.1i "));
    assertFalse(calculator.checkOperationSequence("5.2+2.1u")!=null);
    assertTrue(calculator.checkOperationSequence("5.2+2.1i").equals("5.2+2.1i "));
    assertTrue(calculator.checkOperationSequence("50").equals("50 "));
    assertTrue(calculator.checkOperationSequence("-50.0").equals("-50.0 "));
    assertFalse(calculator.checkOperationSequence("50u")!=null);
    assertTrue(calculator.checkOperationSequence("50.0i").equals("0.0+50.0i "));
    assertFalse(calculator.checkOperationSequence("g50.0i")!=null);
    assertFalse(calculator.checkOperationSequence("50g.0i")!=null);
    assertFalse(calculator.checkOperationSequence("5g0.0i")!=null);
    assertFalse(calculator.checkOperationSequence("50.0+g1.0i")!=null);
    assertFalse(calculator.checkOperationSequence("50.0+1.g0i")!=null);
    assertFalse(calculator.checkOperationSequence("50.0+1.0gi")!=null);
   
    assertFalse(calculator.checkOperationSequence("<ai")!=null);
    assertTrue(calculator.checkOperationSequence("<b").equals("<b "));
    assertFalse(calculator.checkOperationSequence(">ai")!=null);
   
    assertFalse(calculator.checkOperationSequence("+ai")!=null);
    assertFalse(calculator.checkOperationSequence("-ai")!=null);
    assertTrue(calculator.checkOperationSequence(">b").equals(">b "));
    assertTrue(calculator.checkOperationSequence("+b").equals("+b "));
    assertTrue(calculator.checkOperationSequence("-b").equals("-b "));
}
    
}
