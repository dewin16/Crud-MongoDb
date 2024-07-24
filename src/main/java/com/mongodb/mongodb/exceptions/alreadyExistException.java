package com.mongodb.mongodb.exceptions;

public class alreadyExistException extends RuntimeException {

    public alreadyExistException(String error){
        super(error);
    }

}
