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
public class OperationAlreadyExistsException extends Exception {

    /**
     * Creates a new instance of <code>OperationAlreadyExistsException</code>
     * without detail message.
     */
    public OperationAlreadyExistsException() {
    }

    /**
     * Constructs an instance of <code>OperationAlreadyExistsException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public OperationAlreadyExistsException(String msg) {
        super(msg);
    }
}
