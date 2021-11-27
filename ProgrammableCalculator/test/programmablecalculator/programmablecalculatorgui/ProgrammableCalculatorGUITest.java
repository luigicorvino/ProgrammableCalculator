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
        assertTrue("0.0 + 50.0i".equals(calculator.checkInputField()));
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
        
        
    
}
