/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.userdefinedoperations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author luigi
 */
public class UserDefinedOperationController {
    private List<UserDefinedOperation> userDefinedOperations;
    
    public UserDefinedOperationController(){
        userDefinedOperations = new LinkedList<>();
    }
    
    public void create(String name, String sequence){
        List<String> sequenceOfOperations = getSequence(sequence);
        userDefinedOperations.add(new UserDefinedOperation(name,sequenceOfOperations));
    }
    
    
    public List<String> invoke(String name) throws NoSuchOperationException{
        for (UserDefinedOperation operation : userDefinedOperations){
            if(operation.getName().equals(name))
                return operation.getOperationsSequence();
        }
        throw new NoSuchOperationException();
    }

    protected List<UserDefinedOperation> getUserDefinedOperations() {
        return userDefinedOperations;
    }
    
    private List<String> getSequence(String sequence){
        return Arrays.asList(sequence.split("\\s+"));
    }
}
