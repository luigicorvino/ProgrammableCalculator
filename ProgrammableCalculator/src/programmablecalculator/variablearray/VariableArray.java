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
    private static final int alphabetStart = 97;
    private Complex[] variables;
    
    public VariableArray(){
        variables = new Complex[26];
    }
    
    public void insertValue(char variable, Complex value){
       variables[getIndex(variable)] = value;
    }
    
    public Complex getValue(char variable){
        return variables[getIndex(variable)];
    }
    
    
    protected int getIndex(char variable){
        return (int) variable - alphabetStart;
    }

    protected Complex[] getVariables(){
        return variables;
    }
    
    
    
    
    
}
