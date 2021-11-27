/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgrammableCalculatorController;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.math3.complex.*;
import java.util.regex.*;
import programmablecalculator.complexnumberoperations.ComplexNumberOperations;
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
    
    public String elaborateInput(String operation) {
        String result = "";
        switch (operation) {
            
            case "+": {
                if (doAdd() == false)
                   result = "There aren't 2 complex numbers to add ";
                break;
            } 
            
            case "-": {
                break;
            }
            
            default : {
                // Insert od complex number in the numbersStack
                ComplexFormat cFormat;
                NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
                cFormat=new ComplexFormat(nf);
                //ComplexFormat cFormat = new ComplexFormat();
                Complex complex = cFormat.parse(operation);
                numberStack.push(complex);
                break;
            }
               
        }
        
        return result;
      
    }
    
    public Iterator<Complex> getNumbersStack() {
        
        return numberStack.iterator();
    }
    
    public Complex topNumberStack() {
        return numberStack.peekFirst();
    }
    
    public Complex[] getTwelweNumbersStack() {
        
        Complex[] twelveComplex = new Complex[12];
        Iterator<Complex> stack = numberStack.iterator();
        int i = 0;
        while(stack.hasNext() && i<12) {
            twelveComplex[i] = stack.next();
            i+=1;
        }
        return twelveComplex;
    }
    
    
    
    public boolean doAdd(){
        if(numberStack.size()<2)
            return false;
        Complex c2=numberStack.pop();
        Complex c1=numberStack.pop();
        numberStack.push(ComplexNumberOperations.add(c1, c2));
        return true;
    }
    
    public boolean doSub(){
        if(numberStack.size()<2)
            return false;
        Complex c2=numberStack.pop();
        Complex c1=numberStack.pop();
        numberStack.push(ComplexNumberOperations.sub(c1, c2));
        return true;
    }
    
    
    public boolean doMultiply(){
        if(numberStack.size()<2)
            return false;
        Complex c2=numberStack.pop();
        Complex c1=numberStack.pop();
        numberStack.push(ComplexNumberOperations.multiply(c1, c2));
        return true;
    }
    
}
