/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.programmablecalculatorcontroller;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import org.apache.commons.math3.complex.*;
import java.util.regex.*;
import programmablecalculator.complexnumberoperations.ComplexNumberOperations;
import programmablecalculator.numbersstack.NumbersStack;
/**
 *
 * @author lex99
 */
public class ProgrammableCalculatorController {
    private NumbersStack numberStack;
    private final ComplexFormat format;
    
    public abstract class CallBackOperation {       
        public abstract Complex call(Complex c1, Complex c2);
    
    }
    public class CallbackAdd extends CallBackOperation {
            
        @Override
        public  Complex call(Complex c1, Complex c2) {
            return ComplexNumberOperations.add(c1, c2);
        }
    }
    public class CallbackSub extends CallBackOperation {
            
        @Override
        public  Complex call(Complex c1, Complex c2) {
            return ComplexNumberOperations.sub(c1, c2);
        }
    }
    public class CallbackMultiply extends CallBackOperation {
            
        @Override
        public  Complex call(Complex c1, Complex c2) {
            return ComplexNumberOperations.multiply(c1, c2);
        }
    }
    public class CallbackDivide extends CallBackOperation {
            
        @Override
        public  Complex call(Complex c1, Complex c2) {
            return ComplexNumberOperations.divide(c1, c2);
        }
    }
        
    
    public ProgrammableCalculatorController() {
        numberStack = new NumbersStack();
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        format=new ComplexFormat(nf);
    }
    
      /*@brief: elaborate the user's input implementing the calculator's logic
      @param1: user's input
      @return: An error message if needed, a null String otherwise
    */
    public String elaborateInput(String operation) {
        String result = null;
        switch (operation) {
            
            case "+": {
                if (takeComplexAndOperation(new CallbackAdd()) == false)
                   result = "There aren't 2 complex numbers to add ";
                break;
            } 
            
            case "-": {
                if (takeComplexAndOperation(new CallbackSub())== false)
                   result = "There aren't 2 complex numbers to sub ";
                break;
            }
            
            case "*": {
                if (takeComplexAndOperation(new CallbackMultiply())== false)
                   result = "There aren't 2 complex numbers to multiply ";
                break;
            }
            
            case "/": {
                if (takeComplexAndOperation(new CallbackDivide())== false)
                   result = "There aren't 2 complex numbers to divide ";
                break;
            }
            
            default: {
                // Insert complex number in the numbersStack
                Complex complex = format.parse(operation);
                numberStack.push(complex);
                break;
            }
               
        }
        
        return result;
      
    }
    
    public Iterator<Complex> getNumbersStack() {
        
        return numberStack.iterator();
    }

    public NumbersStack getNumberStack() {
        return numberStack;
    }
    

    private boolean takeComplexAndOperation( CallBackOperation operation) {
        if(numberStack.size()<2)
            return false;
        Complex c2=numberStack.pop();
        Complex c1=numberStack.pop();
        numberStack.push(operation.call(c1,c2));
        return true;
        
    }
    

    
}
