/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.userdefinedoperations;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDefinedOperation other = (UserDefinedOperation) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
