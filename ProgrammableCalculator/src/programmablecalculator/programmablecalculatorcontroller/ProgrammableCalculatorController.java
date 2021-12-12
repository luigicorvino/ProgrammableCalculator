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
import programmablecalculator.userdefinedoperations.NoSuchOperationException;
import programmablecalculator.userdefinedoperations.OperationAlreadyExistsException;
import programmablecalculator.userdefinedoperations.UserDefinedOperationController;
import programmablecalculator.variablearray.NotACharacterException;
/**
 *
 * @author lex99
 */
public class ProgrammableCalculatorController {
    protected NumbersStack numberStack;
    private final ComplexFormat format;
    protected SaveVariableStack variableStack;
    protected UserDefinedOperationController userDefinedOperations;
    
    
    
    
    public abstract class Operation {
        protected Complex[] c;
        public Operation() {
        }       
        public abstract boolean execute(NumbersStack stack, SaveVariableStack variables);
    }
    
    public class OperationDrop extends Operation{
        public OperationDrop() {
            super();
        }
        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            if(!stack.isEmpty())
                stack.drop();
            else
                return false;
            return true;
        }
    }
    
    public class OperationDup extends Operation{
        public OperationDup() {
            super();
        }

        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            if(stack.size() < 1)
                return false;
            else
                stack.dup();
            return true;
        }
    }
    
    public class OperationSwap extends Operation{
        public OperationSwap() {
            super();
        }

        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            
            try {
                stack.swap();
            } catch (NotEnoughElementsException ex) {
                return false;
            }
            return true;
        }
    }
    
    public class OperationOver extends Operation{
        public OperationOver() {
            super();
        }

        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            try {
                stack.over();
            } catch (NotEnoughElementsException ex) {
                return false;
            }
            return true;
        }
    }
    
    public class OperationClear extends Operation{
        public OperationClear() {
            super();
        }

        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            stack.clear();
            return true;
        }
    }
    
    public class OperationSave extends Operation{
        public OperationSave() {
            super();
        }

        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            variables.save();
            return true;
        }
    }
    
    public class OperationRestore extends Operation{
        public OperationRestore() {
            super();
        }

        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            variables.restore();
            return true;
        }
    }
    
    public class OperationInsertComplex extends Operation{
        private Complex complex;
        private ComplexFormat cformat;
        public OperationInsertComplex(String s) {
            NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
            nf.setMaximumFractionDigits(8);
            cformat=new ComplexFormat(nf);
            complex = cformat.parse(s);
        }
        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            if (complex != null) {
                stack.push(complex);
                return true; 
            }
            else return false; 
        }
        
    }
    
    public abstract class OperationNumberStack extends Operation{
        
        private int operands;
    
        public OperationNumberStack(int ops) {
            super();
            operands = ops;
        }
        
        protected boolean takeComplex(NumbersStack stack) {
            if(stack.size()<operands)
                return false;
            
            c = new Complex[operands];
            for (int i = operands-1; i >= 0; i--) {
                c[i] = stack.pop();
            }
            return true;
        }
        public Complex operating(){return null;};
        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            boolean success = takeComplex(stack);
            if(success) 
                stack.push(operating());
            return success;
        }
    }

    public class OperationAdd extends OperationNumberStack {

        public OperationAdd() {
            super(2);
        }

        @Override
        public Complex operating() {
            return ComplexNumberOperations.add(super.c[0], super.c[1]);
        }
            
    }
        
    public class OperationSub extends OperationNumberStack {
        public OperationSub() {
            super(2);
        }

        @Override
        public Complex operating() {
            return ComplexNumberOperations.sub(super.c[0], super.c[1]);
        }    
        
    }
    public class OperationMultiply extends OperationNumberStack {
            
        public OperationMultiply() {
            super(2);
        }

        @Override
        public Complex operating() {
            return ComplexNumberOperations.multiply(super.c[0], super.c[1]);
        }
    }
    public class OperationDivide extends OperationNumberStack {
            
        public OperationDivide() {
            super(2);
        }

        @Override
        public Complex operating() {
            return ComplexNumberOperations.divide(super.c[0], super.c[1]);
        }
    }
    
    
    public class OperationInvertSign extends OperationNumberStack {
        public OperationInvertSign() {
            super(1);
        }

        @Override
        public Complex operating() {
            return ComplexNumberOperations.invert(super.c[0]);
        }
    }
    public class OperationSqrt extends OperationNumberStack {
        public OperationSqrt() {
            super(1);
        }

        @Override
        public Complex operating() {
            return ComplexNumberOperations.sqrt(super.c[0]);
        }
    }
    
    public abstract class OperationVariable extends OperationNumberStack {
        protected char character;
        public OperationVariable(char character) {
            super(1);
            this.character = character;
        }   
    }
    
    public class OperationSetVariable extends OperationVariable {
        public OperationSetVariable(char character) {
            super(character);
        }

        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            Complex complex = stack.peekFirst();
            try {
                if(complex != null) {
                    stack.pop();
                    variables.insertValue(character, complex);
                }
            } catch (NotACharacterException ex) {
                return false;
            }
            return (complex != null);
        }
    }
    
    public class OperationGetVariable extends OperationVariable {
        
        public OperationGetVariable(char character) {
            super(character);
        }
        
        @Override
        public boolean execute(NumbersStack stack, SaveVariableStack variables) {
            Complex complex = null;
                try {
                    complex = variables.getValue(character);
                    if (complex != null)
                        stack.push(complex);
            } catch (NotACharacterException ex) {
                return false;
            }
            return (complex != null);
        }
    }
    
    public ProgrammableCalculatorController() {
        numberStack = new NumbersStack();
        NumberFormat nf=NumberFormat.getInstance(new Locale("en","US"));
        nf.setMaximumFractionDigits(8);
        format=new ComplexFormat(nf);
        
        variableStack = new SaveVariableStack();
        numberStack = new NumbersStack();
        userDefinedOperations = new UserDefinedOperationController();
    }
    
      /*@brief: elaborate the user's input implementing the calculator's logic
      @param1: user's input
      @return: An error message if needed, a null String otherwise
    */
    public String elaborateInput(String input) {
        String result = null;
        switch (input) {
            
            case "+": {
                Operation operation = new OperationAdd();          
                if (operation.execute(numberStack, variableStack) == false)
                   result = "There aren't 2 complex numbers to add ";
                break;
            } 
            
            case "-": {
                Operation operation = new OperationSub();          
                if (operation.execute(numberStack, variableStack) == false)
                   result = "There aren't 2 complex numbers to sub ";
                break;
            }
            
            case "*": {
                Operation operation = new OperationMultiply();          
                if (operation.execute(numberStack, variableStack) == false)
                   result = "There aren't 2 complex numbers to multiply ";
                break;
            }
            
            case "/": {
                Operation operation = new OperationDivide();          
                if (operation.execute(numberStack, variableStack) == false)
                   result = "There aren't 2 complex numbers to divide ";
                break;
            }
            
            case "+-": {
                Operation operation = new OperationInvertSign();          
                if (operation.execute(numberStack, variableStack) == false)
                   result = "There isn't one complex numbers to invert sign ";
                break;
            }
            case "sqrt": {
                Operation operation = new OperationSqrt();          
                if (operation.execute(numberStack, variableStack) == false)
                   result = "There isn't one complex numbers to sqrt ";
                break;
            }
            
            case "drop": {
                Operation operation = new OperationDrop();          
                if (operation.execute(numberStack, variableStack) == false)
                   result = "There isn't one complex numbers to drop ";
                break;
            }
            
            case "dup": {
                Operation operation = new OperationDup();          
                if (operation.execute(numberStack, variableStack) == false)
                   result = "There isn't one complex numbers to dup ";
                break;
                
            }
            
            
            case "swap": {
            
                Operation operation = new OperationSwap();          
                if (operation.execute(numberStack, variableStack) == false)
                   result = "There isn't 2 complex numbers to swap ";
                break;
            }
            
            case "over": {
            
                Operation operation = new OperationOver();          
                if (operation.execute(numberStack, variableStack) == false)
                   result = "There isn't 2 complex numbers to over ";
                break;
            }
            
            case "clear": {
                Operation operation = new OperationClear();
                operation.execute(numberStack, variableStack);
                break;
            }
            
            case "save": {
                Operation operation = new OperationSave();
                operation.execute(numberStack, variableStack);
                break;
            }
            
            case "restore": {
                Operation operation = new OperationRestore();
                operation.execute(numberStack, variableStack);
                break;
            }
            
            default: {
                if(input.matches("\\>[a-z]")) {
                    Operation operation = new OperationSetVariable(input.charAt(1));
                    if(operation.execute(numberStack, variableStack) == false)
                        result = "There isn't a complex number in the stack";
                /*
                if(input.matches("\\>[a-z]")) {
                    try {
                        if(numberStack.size() > 0) 
                            variableStack.insertValue(input.charAt(1), numberStack.peekFirst());
                        else
                            result = "There isn't a complex number in the stack";
                        
                    } catch (NotACharacterException ex) {}
                */
                    break;
                    
                }
                
                
                if(input.matches("\\<[a-z]")) {
                    Operation operation = new OperationGetVariable(input.charAt(1));
                    if(operation.execute(numberStack, variableStack) == false)
                        result = "The variable '"+input.charAt(1)+"' haven't a value";
                    /*
                    try {
                        Complex c = variableStack.getValue(input.charAt(1));
                        if(c == null) 
                            result = "The variable '"+input.charAt(1)+"' haven't a value";
                        else
                            numberStack.push(c);
                    } catch (NotACharacterException ex) {
                    }
                    */
                    break;
                }
                
                if(input.matches("\\+{1}[a-z]{1}")) {
                    Operation operation = new OperationGetVariable(input.charAt(1));
                    if(!operation.execute(numberStack, variableStack)) 
                        result = "The variable '"+input.charAt(1)+"' haven't a value";
                    else {
                        operation = new OperationAdd();
                        if(!operation.execute(numberStack, variableStack))
                            result = "There isn't a complex number in the stack";
                        else {
                            operation = new OperationSetVariable(input.charAt(1));
                            operation.execute(numberStack, variableStack);
                        }
                            
                    }
                    break;
                }
                
                if(input.matches("\\-{1}[a-z]{1}")) {
                    Operation operation = new OperationGetVariable(input.charAt(1));
                    if(!operation.execute(numberStack, variableStack)) 
                        result = "The variable '"+input.charAt(1)+"' haven't a value";
                    else {
                        operation = new OperationSub();
                        if(!operation.execute(numberStack, variableStack))
                            result = "There isn't a complex number in the stack";
                        else {
                            operation = new OperationSetVariable(input.charAt(1));
                            operation.execute(numberStack, variableStack);
                        }
                            
                    }
                    break;
                }
                
                
                if(input.matches("^(?=[iI.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?(?![iI.\\d]))?([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?)?[iI])?$"))
                {
                     // Insert complex number in the numbersStack
                    Operation operation = new OperationInsertComplex(input);
                    operation.execute(numberStack, variableStack);
                    break;
                }
            
                List<String> sequence = getSequenceUserDefinedOperation(input);
                if(sequence != null) {
                    return invokeUserDefinedOperation(sequence);
                }
                
  
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
    
    public Complex[] getVariableArray() {
        return variableStack.getCurrentArray().getVariables();
    }
    
    
    public String createUserDefinedOperation(String name, String sequence) {
        try {
            userDefinedOperations.create(name, sequence);
            return null;
        } catch (OperationAlreadyExistsException ex) {
            return "Operation already exists";
        }
    }
    
    public String modifyNameUserDefinedOperation(String oldName, String newName) {
        try {
            userDefinedOperations.modifyName(oldName, newName);
            return null;
        } catch (NoSuchOperationException ex) {
           return "Operation "+ oldName + " doesn't exist";
        } catch (OperationAlreadyExistsException ex) {
           return newName + " Operation already exists";
        }
    }
    
    public String modifySequenceUserDefinedOperation(String name, String sequence) {
        try {
            userDefinedOperations.modifySequence(name, sequence);
            return null;
        } catch (NoSuchOperationException ex) {
           return "Operation "+ name + " doesn't exist";
        }
    }
    
    public Iterator<String> getUserDefinedOperationList() {
        return userDefinedOperations.getOperationsList().iterator();
    }
    
    
    protected List<String> getSequenceUserDefinedOperation(String name) {
        
        try {
            List<String> sequence = userDefinedOperations.invoke(name);
            return sequence;
        } catch (NoSuchOperationException ex) {
            return null;
        }
    }
    
    protected String invokeUserDefinedOperation(List<String> sequence) {
        String result = null;
        for (String operation: sequence) {
            result = elaborateInput(operation);
            if(result != null) 
                return result;
        }
        
        return null;
    } 
    
    public String deleteSequenceUserDefinedOperation(String name) {
        try {
            userDefinedOperations.delete(name);
            return null;
        } catch (NoSuchOperationException ex) {
            return name + " operation doesn't exist";
        }
    }
    
    /*
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
    */

    
}
