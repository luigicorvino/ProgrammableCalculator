/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.numbersstack;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author luigi
 */
public class NumbersStack extends ArrayDeque<Complex>{
    //TODO: methods for numbers stack manipulation
    
    
    
   public void drop() throws NoSuchElementException{
       this.removeFirst();
       
   }
   
   
   public void dup() throws NoSuchElementException{
       this.push(this.getFirst());
   }
   
   
   public void swap() throws NotEnoughElementsException{
       if(this.size()<2)
           throw new NotEnoughElementsException();
       Complex c1 = this.pop();
       Complex c2 = this.pop();
       this.push(c1);
       this.push(c2);
       
   }
   
   public void over() throws NotEnoughElementsException{
       if(this.size()<2)
           throw new NotEnoughElementsException();
       Complex first=this.pop();
       Complex dup=this.getFirst();
       this.push(first);
       this.push(dup);
   }
}
