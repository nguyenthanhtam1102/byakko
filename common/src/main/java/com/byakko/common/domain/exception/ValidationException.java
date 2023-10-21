package com.byakko.common.domain.exception;

import java.util.Map;

public class ValidationException extends RuntimeException {
    private Map<String , String> message;
    public ValidationException(Map<String,String> message){
        super("Validation Exception");
        this.message = message;
    }
}