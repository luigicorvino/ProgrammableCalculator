/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package programmablecalculator.variablearray;

import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author luigi
 */
public class VariableArray{
    private static final int ALPHABET_START = 97;
    private Complex[] variables;
    
    public VariableArray(){
        variables = new Complex[26];
    }
    
    public void insertValue(char variable, Complex value) throws NotACharacterException{
       variables[getIndex(variable)] = value;
    }
    
    public Complex getValue(char variable) throws NotACharacterException{
        if(!checkCharacter(variable))
            throw new NotACharacterException();
        return variables[getIndex(variable)];
    }
    
    
    protected int getIndex(char variable) throws NotACharacterException{
        if(!checkCharacter(variable))
            throw new NotACharacterException();
        return (int) variable - ALPHABET_START;
    }

    protected Complex[] getVariables(){
        return variables;
    }
    
    
    protected boolean checkCharacter(char variable){
        return Character.isLetter(variable);
    }
    
    public VariableArray copy(){
        VariableArray copy = new VariableArray();
        copy.variables = this.variables.clone();
        return copy;
    }
}
