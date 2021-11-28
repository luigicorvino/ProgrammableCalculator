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
    
    
    
    /*@brief: add two complex numbers
      @param1: first complex number
      @param2: second complex number
      @return: result complex number
    */
    public static Complex add(Complex c1, Complex c2){
        Complex result = c1.add(c2);
        return result;
    }
    
    /*@brief: subtract two complex numbers
      @param1: first complex number
      @param2: second complex number
      @return: result complex number
    */
    public static Complex sub(Complex c1, Complex c2){
        Complex result=c1.subtract(c2);
        return result;
        
    }
    
    /*@brief: multiply two complex numbers
      @param1: first complex number
      @param2: second complex number
      @return: result complex number
    */
    public static Complex multiply(Complex c1, Complex c2){
        Complex result=c1.multiply(c2);
        return result;
    }
    
    
    /*@brief: divide two complex numbers
      @param1: first complex number
      @param2: second complex number
      @return: result complex number
    */
    public static Complex divide(Complex c1, Complex c2) {
        return c1.divide(c2);
    }
}
