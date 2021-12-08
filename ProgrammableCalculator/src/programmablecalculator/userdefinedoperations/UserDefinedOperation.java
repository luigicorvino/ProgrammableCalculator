/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.userdefinedoperations;

import java.util.List;

/**
 *
 * @author luigi
 */
public class UserDefinedOperation {
    private String name;
    private List<String> operationsSequence;
    
    public UserDefinedOperation(String name, List<String> operationsSequence){
        this.name = name;
        this.operationsSequence = operationsSequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOperationsSequence() {
        return operationsSequence;
    }

    public void setOperationsSequence(List<String> operationsSequence) {
        this.operationsSequence = operationsSequence;
    }
}
