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
    
    public void create(String name, String sequence) throws OperationAlreadyExistsException{
        List<String> sequenceOfOperations = getSequence(sequence);
        UserDefinedOperation operation = new UserDefinedOperation(name,sequenceOfOperations);
        if(userDefinedOperations.contains(operation))
            throw new OperationAlreadyExistsException();
        userDefinedOperations.add(operation);

    }
    
    
    public List<String> invoke(String name) throws NoSuchOperationException{
        return getOperation(name).getOperationsSequence();
    }
    
    public void delete(String name) throws NoSuchOperationException{
        userDefinedOperations.remove(getOperation(name));
    }
    
    public void modifyName(String oldName, String newName) throws NoSuchOperationException, OperationAlreadyExistsException{
        if(userDefinedOperations.contains(new UserDefinedOperation(newName,null)))
            throw new OperationAlreadyExistsException();
        getOperation(oldName).setName(newName);
    }
    
    public void modifySequence(String name, String sequence) throws NoSuchOperationException{
        List<String> sequenceOfOperations = getSequence(sequence);
        getOperation(name).setOperationsSequence(sequenceOfOperations);
    }
    
    
    protected List<UserDefinedOperation> getUserDefinedOperations() {
        return userDefinedOperations;
    }
    
    private List<String> getSequence(String sequence){
        return Arrays.asList(sequence.split("\\s+"));
    }
    

    
    private UserDefinedOperation getOperation(String name) throws NoSuchOperationException{
        for (UserDefinedOperation operation : userDefinedOperations){
            if(operation.getName().equals(name))
                return operation;
        }
        throw new NoSuchOperationException();
    }
    
    
    
}
