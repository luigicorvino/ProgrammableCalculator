/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.savevariablestack;

import org.apache.commons.math3.complex.Complex;
import programmablecalculator.variablearray.NotACharacterException;
import programmablecalculator.variablearray.VariableArray;

/**
 *
 * @author luigi
 */
public class SaveVariableStack {
  //  private ArrayDeque<vVariableArray>
    private VariableArray currentArray;

    public SaveVariableStack( ) {
        currentArray = new VariableArray();
    }


    
    
    public void insertValue(char variable, Complex value) throws NotACharacterException{
       currentArray.insertValue(variable, value);
    }    
    
    
    public Complex getValue(char variable) throws NotACharacterException{
        return currentArray.getValue(variable);
    }
    
    
    protected VariableArray getCurrentArray() {
        return currentArray;
    }
    
}
