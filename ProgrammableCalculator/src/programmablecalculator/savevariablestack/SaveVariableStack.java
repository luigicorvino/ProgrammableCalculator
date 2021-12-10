/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.savevariablestack;

import java.util.ArrayDeque;
import java.util.Iterator;
import org.apache.commons.math3.complex.Complex;
import programmablecalculator.variablearray.NotACharacterException;
import programmablecalculator.variablearray.VariableArray;

/**
 *
 * @author luigi
 */
public class SaveVariableStack {
    private ArrayDeque<VariableArray> variableStack;
    private VariableArray currentArray;

    public SaveVariableStack( ) {
        variableStack = new ArrayDeque<>();
        currentArray = new VariableArray();
    }


    
    public void save(){
        variableStack.push(currentArray.copy());
    }
    
    
    public boolean restore(){
        if(variableStack.isEmpty())
            return false;
        currentArray = variableStack.pop();
        return true;
    }


    
    public void insertValue(char variable, Complex value) throws NotACharacterException{
       currentArray.insertValue(variable, value);
    }    
    
    
    public Complex getValue(char variable) throws NotACharacterException{
        return currentArray.getValue(variable);
    }
    

    
    public VariableArray getCurrentArray() {
        return currentArray;
    }
    
    protected ArrayDeque<VariableArray> getVariableStack() {
        return variableStack;
    }
}
