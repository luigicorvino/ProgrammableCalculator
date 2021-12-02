/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.programmablecalculatorgui;

import javax.swing.DefaultListModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import programmablecalculator.programmablecalculatorgui.ProgrammableCalculatorGUI;
/**
 *
 * @author bad-b
 */

public class ProgrammableCalculatorGUITest {
    private ProgrammableCalculatorGUI calculator;
    private DefaultListModel<String> model;
    public  ProgrammableCalculatorGUITest(){
        calculator=new ProgrammableCalculatorGUI();
        model=new DefaultListModel<>();
    }
  
    @Test 
    public void TestCheckInputField() {
        
        calculator.setTextField("5.2-2.1u");
        assertFalse("5.2-2.1u".equals(calculator.checkInputField()));
        calculator.setTextField("5.2+2.1i");
        assertTrue("5.2+2.1i".equals(calculator.checkInputField()));
        calculator.setTextField("-5.2-2.1u");
        assertFalse("-5.2-2.1u".equals(calculator.checkInputField()));
        calculator.setTextField("-5.2-2.1i");
        assertTrue("-5.2-2.1i".equals(calculator.checkInputField()));
        calculator.setTextField("5.2+2.1u");
        assertFalse("5.2+2.1u".equals(calculator.checkInputField()));
        calculator.setTextField("5.2+2.1i");
        assertTrue("5.2+2.1i".equals(calculator.checkInputField()));
        calculator.setTextField("50");
        assertTrue("50".equals(calculator.checkInputField()));
        calculator.setTextField("-50.0");
        assertTrue("-50.0".equals(calculator.checkInputField()));
        calculator.setTextField("50u");
        assertFalse("50u".equals(calculator.checkInputField()));
        calculator.setTextField("50.0i");
        assertTrue("0.0+50.0i".equals(calculator.checkInputField()));
        calculator.setTextField("g50.0i");
        assertFalse("g50.0i".equals(calculator.checkInputField()));
        calculator.setTextField("50g.0i");
        assertFalse("50g.0i".equals(calculator.checkInputField()));
        calculator.setTextField("5g0.0i");
        assertFalse("5g0.0i".equals(calculator.checkInputField()));
        calculator.setTextField("50.0+g1.0i");
        assertFalse("50.0+g1.0i".equals(calculator.checkInputField()));
        calculator.setTextField("50.0+1.g0i");
        assertFalse("50.0+1.g0i".equals(calculator.checkInputField()));
        calculator.setTextField("50.0+1.0gi");
        assertFalse("50.0+1.0gi".equals(calculator.checkInputField()));
        
}
 
    @Test
 public void checkOperationStatusAndUpdate(){
     
     calculator.checkOperationStatusAndUpdate("1+2i");
     model=calculator.getModel();
     assertTrue(model.get(0).equals("1 + 2i"));
     calculator.checkOperationStatusAndUpdate("1-2i");
     assertTrue(model.get(0).equals("1 - 2i"));
     calculator.checkOperationStatusAndUpdate("*");
     assertTrue(model.get(0).equals("5"));
     calculator.checkOperationStatusAndUpdate("7-2i");
     calculator.checkOperationStatusAndUpdate("/");
     assertTrue(model.get(0).equals("0.66 + 0.189i"));
     calculator.checkOperationStatusAndUpdate("1+2i");
     calculator.checkOperationStatusAndUpdate("+");
     assertTrue(model.get(0).equals("1.66 + 2.189i"));
     calculator.checkOperationStatusAndUpdate("1+2i");
     calculator.checkOperationStatusAndUpdate("-");
     assertTrue(model.get(0).equals("0.66 + 0.189i"));
     calculator.checkOperationStatusAndUpdate("+-");
     assertTrue(model.get(0).equals("-0.66 - 0.189i"));
     calculator.checkOperationStatusAndUpdate("sqrt");
     assertTrue(model.get(0).equals("0.115 - 0.821i"));
     //calculator.checkOperationStatusAndUpdate("clear");
     //assertTrue(model.isEmpty());
     calculator.checkOperationStatusAndUpdate("1+2i");
     calculator.checkOperationStatusAndUpdate("drop");
     assertFalse(model.get(0).equals("1+2i"));
     calculator.checkOperationStatusAndUpdate("1+2i");
     calculator.checkOperationStatusAndUpdate("dup");
     assertTrue(model.get(0).equals("1 + 2i"));
     assertTrue(model.get(1).equals("1 + 2i"));
     calculator.checkOperationStatusAndUpdate("1-2i");
     calculator.checkOperationStatusAndUpdate("swap");
     assertTrue(model.get(0).equals("1 + 2i"));
     calculator.checkOperationStatusAndUpdate("over");
     assertTrue(model.get(0).equals("1 - 2i"));
     
     
     
 }
        
    
}
