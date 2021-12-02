/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.variablearray;

/**
 *
 * @author luigi
 */
public class NotACharacterException extends Exception {

    /**
     * Creates a new instance of <code>NotACharacterException</code> without
     * detail message.
     */
    public NotACharacterException() {
    }

    /**
     * Constructs an instance of <code>NotACharacterException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotACharacterException(String msg) {
        super(msg);
    }
}
