/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator;
import org.apache.commons.math3.complex.*;
/**
 *
 * @author luigi
 */
public class ProgrammableCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world");
        System.out.println("hello world alfonso");
        System.out.println("Hello world2");
        System.out.println("Hello world3");
        Complex c = new Complex(4,9);
        ComplexFormat format=new ComplexFormat();
        String s=format.format(c);
        System.out.println(s);
        Complex root=c.sqrt();
        String s2=format.format(root);
        System.out.println(s2);
    }
    
}
