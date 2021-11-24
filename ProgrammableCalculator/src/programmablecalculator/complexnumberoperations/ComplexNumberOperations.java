/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.complexnumberoperations;

import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author luigi
 */
public class ComplexNumberOperations {
    public static Complex add(Complex c1, Complex c2){
        Complex result = c1.add(c2);
        return result;
    }
}
