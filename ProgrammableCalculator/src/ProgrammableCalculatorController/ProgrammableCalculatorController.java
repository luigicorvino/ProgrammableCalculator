/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrammableCalculatorController;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.complex.*;
import java.util.regex.*;
import programmablecalculator.numbersstack.NumbersStack;
/**
 *
 * @author lex99
 */
public class ProgrammableCalculatorController {
    private String input;
    private NumbersStack numberStack;
    
    public ProgrammableCalculatorController() {
        numberStack = new NumbersStack();
    }
    
    public void elaborateInput(String operation) {
              
        switch (operation) {
            
            case "+": {
                return;
            } 
            
            case "-": {
                return;
            }
            
               
        }
        
        if ( operation.matches("^(?=[iI.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?(?![iI.\\d]))?([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?)?[iI])?$")) {
            ComplexFormat cFormat = new ComplexFormat();
            Complex complex = cFormat.parse(operation);
            System.out.println(" Complex: " + complex.toString());
            numberStack.push(complex);
        }
      
    }
    
    public Iterator<Complex> getNumbersStack() {
        
        return numberStack.iterator();
    }
    
    public Complex topNumberStack() {
        return numberStack.peekLast();
    }
}
