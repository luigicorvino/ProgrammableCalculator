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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.complex.*;
import java.util.regex.*;
import programmablecalculator.complexnumberoperations.ComplexNumberOperations;
import programmablecalculator.numbersstack.NotEnoughElementsException;
import programmablecalculator.numbersstack.NumbersStack;
import programmablecalculator.savevariablestack.SaveVariableStack;
import programmablecalculator.variablearray.NotACharacterException;
/**
 *
 * @author lex99
 */
public class ProgrammableCalculatorController {
    protected NumbersStack numberStack;
    private final ComplexFormat format;
    protected SaveVariableStack variableStack;
    
    public abstract class CallBackBinaryOperation {       
        public abstract Complex call(Complex c1, Complex c2);
    
    }
    
    public class CallbackAdd extends CallBackBinaryOperation {
            
        @Override
        public  Complex call(Complex c1, Complex c2) {
            return ComplexNumberOperations.add(c1, c2);
        }
    }
    public class CallbackSub extends CallBackBinaryOperation {
            
        @Override
        public  Complex call(Complex c1, Complex c2) {
            return ComplexNumberOperations.sub(c1, c2);
        }
    }
    public class CallbackMultiply extends CallBackBinaryOperation {
            
        @Override
        public  Complex call(Complex c1, Complex c2) {
            return ComplexNumberOperations.multiply(c1, c2);
        }
    }
    public class CallbackDivide extends CallBackBinaryOperation {
            
        @Override
        public  Complex call(Complex c1, Complex c2) {
            return ComplexNumberOperations.divide(c1, c2);
        }
    }
    
    
    public abstract class CallBackUnaryOperation {
        public abstract Complex call(Complex c1);
        
    }
    public class CallBackInvertSign extends CallBackUnaryOperation {
        public Complex call(Complex c1) {
            return ComplexNumberOperations.invert(c1);
        }
    }
    public class CallBackSqrt extends CallBackUnaryOperation {
        public Complex call(Complex c1) {
            return ComplexNumberOperations.sqrt(c1);
        }
    }
        
    
    public ProgrammableCalculatorController() {
        numberStack = new NumbersStack();
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        format=new ComplexFormat(nf);
        
        variableStack = new SaveVariableStack();
        numberStack = new NumbersStack();
    }
    
      /*@brief: elaborate the user's input implementing the calculator's logic
      @param1: user's input
      @return: An error message if needed, a null String otherwise
    */
    public String elaborateInput(String operation) {
        String result = null;
        switch (operation) {
            
            case "+": {
                if (takeComplexAndBinaryOperation(new CallbackAdd()) == false)
                   result = "There aren't 2 complex numbers to add ";
                break;
            } 
            
            case "-": {
                if (takeComplexAndBinaryOperation(new CallbackSub())== false)
                   result = "There aren't 2 complex numbers to sub ";
                break;
            }
            
            case "*": {
                if (takeComplexAndBinaryOperation(new CallbackMultiply())== false)
                   result = "There aren't 2 complex numbers to multiply ";
                break;
            }
            
            case "/": {
                if (takeComplexAndBinaryOperation(new CallbackDivide())== false)
                   result = "There aren't 2 complex numbers to divide ";
                break;
            }
            
            case "+-": {
                if (takeComplexAndUnaryOperation(new CallBackInvertSign())== false)
                   result = "There isn't one complex numbers to invert sign ";
                break;
            }
            case "sqrt": {
                if (takeComplexAndUnaryOperation(new CallBackSqrt())== false)
                   result = "There isn't one complex numbers to sqrt ";
                break;
            }
            
            case "drop": {
                if (numberStack.isEmpty())
                    return result = "There isn't one complex numbers to drop ";
                numberStack.drop();
                break;
            }
            
            case "dup": {
                if (numberStack.size() < 1)
                    return result = "There isn't one complex numbers to dup ";
                numberStack.dup();
                break;
            }
            
            
            case "swap": {
            try { 
                
                numberStack.swap();
                    } catch (NotEnoughElementsException ex) {
                        return result = "There isn't 2 complex numbers to swap ";
                    }
                break;
            }
            
            case "over": {
            try {
                    
                numberStack.over();
                    } catch (NotEnoughElementsException ex) {
                        return result = "There isn't 2 complex numbers to over "; 
                    }
                break;
            }
            
            case "clear": {
                numberStack.clear();
                break;
            }
            
            default: {
                
                if(operation.matches(">[a-z]")) {
                    System.out.println(operation);
                    System.out.println("length: "+operation.length());
                    System.out.println(operation.charAt(1));
                    try {
                        if(numberStack.size() > 0) {
                            System.out.println(numberStack.peekFirst());
                            Complex c = numberStack.peekFirst();
                            char ch = operation.charAt(1);
                            variableStack.insertValue(ch, c);
                        }
                        else
                            result = "There isn't a complex number in the stack";
                        
                    } catch (NotACharacterException ex) {}
                    break;
                }
                
                
                if(operation.matches("<[a-z]")) {
                    try {
                        Complex c = variableStack.getValue(operation.charAt(1));
                        if(c == null) 
                            return result = "The variable '"+operation.charAt(1)+"' haven't a value";
                        else
                            numberStack.push(c);
                    } catch (NotACharacterException ex) {
                    }
                    break;
                }
       
                
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
    

    private boolean takeComplexAndBinaryOperation( CallBackBinaryOperation operation) {
        if(numberStack.size()<2)
            return false;
        Complex c2=numberStack.pop();
        Complex c1=numberStack.pop();
        numberStack.push(operation.call(c1,c2));
        return true;
        
    }
    
    private boolean takeComplexAndUnaryOperation( CallBackUnaryOperation operation) {
        if(numberStack.size()<1)
            return false;
        numberStack.push(operation.call(numberStack.pop()));
        return true;
    }
    

    
}
