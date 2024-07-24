package com.mongodb.mongodb.exceptions;

public class notFoundException extends RuntimeException {

    public notFoundException (String error){
        super(error);
    }

}