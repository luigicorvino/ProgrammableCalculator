/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.userdefinedoperations;

/**
 *
 * @author luigi
 */
public class NoSuchOperationException extends Exception {

    /**
     * Creates a new instance of <code>NoSuchOperationException</code> without
     * detail message.
     */
    public NoSuchOperationException() {
    }

    /**
     * Constructs an instance of <code>NoSuchOperationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoSuchOperationException(String msg) {
        super(msg);
    }
}
