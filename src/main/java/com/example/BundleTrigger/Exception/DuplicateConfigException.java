package com.example.BundleTrigger.Exception;

public class DuplicateConfigException extends RuntimeException{

    public DuplicateConfigException(String error){
        super(error);
    }
    
}
