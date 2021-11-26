/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.programmablecalculatorgui;

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
    public ProgrammableCalculatorGUI calculator=new ProgrammableCalculatorGUI();
    public  ProgrammableCalculatorGUITest(){
        
    }
  
    @Test 
    public void TestCheckInputField() {
        
        calculator.setTextField("5.2-2.1u");
        assertFalse("5.2-2.1u".equals(calculator.checkInputField()));
        calculator.setTextField("5.2+2.1j");
        assertTrue("5.2+2.1j".equals(calculator.checkInputField()));
        calculator.setTextField("-5.2-2.1u");
        assertFalse("-5.2-2.1u".equals(calculator.checkInputField()));
        calculator.setTextField("-5.2-2.1j");
        assertTrue("-5.2-2.1j".equals(calculator.checkInputField()));
        calculator.setTextField("5.2+2.1u");
        assertFalse("5.2+2.1u".equals(calculator.checkInputField()));
        calculator.setTextField("5.2+2.1j");
        assertTrue("5.2+2.1j".equals(calculator.checkInputField()));
        calculator.setTextField("50");
        assertTrue("50".equals(calculator.checkInputField()));
        calculator.setTextField("-50.0");
        assertTrue("-50.0".equals(calculator.checkInputField()));
        calculator.setTextField("50u");
        assertFalse("50u".equals(calculator.checkInputField()));
        calculator.setTextField("50.0j");
        assertTrue("50.0j".equals(calculator.checkInputField()));
        /*calculator.setTextField("g50.0j");
        assertFalse("g50.0j".equals(calculator.checkInputField()));
        calculator.setTextField("50g.0j");
        assertFalse("50g.0j".equals(calculator.checkInputField()));
        calculator.setTextField("5g0.0j");
        assertFalse("5g0.0j".equals(calculator.checkInputField()));
        calculator.setTextField("50.0+g1.0j");
        assertFalse("50.0+g1.0j".equals(calculator.checkInputField()));
        calculator.setTextField("50.0+1.g0j");
        assertFalse("50.0+1.g0j".equals(calculator.checkInputField()));
        calculator.setTextField("50.0+1.0gj");
        assertFalse("50.0+1.0gj".equals(calculator.checkInputField()));
    **/
}
        
        
    
}
