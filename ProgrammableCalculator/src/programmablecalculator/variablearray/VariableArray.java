/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package programmablecalculator.variablearray;

import java.util.ArrayList;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author luigi
 */
public class VariableArray{
    private static final int alphabetStart = 97;
    private ArrayList<Complex> variables;
    
    public VariableArray(){
        variables=new ArrayList<>(26);
    }
    
    
    protected int getIndex(char variable){
        return (int) variable - alphabetStart;
    }
    
    
}
