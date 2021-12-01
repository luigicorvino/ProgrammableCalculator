/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.numbersstack;

/**
 *
 * @author luigi
 */
public class NotEnoughElementsException extends Exception {

    /**
     * Creates a new instance of <code>NotEnoughElementsException</code> without
     * detail message.
     */
    public NotEnoughElementsException() {
    }

    /**
     * Constructs an instance of <code>NotEnoughElementsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NotEnoughElementsException(String msg) {
        super(msg);
    }
}
