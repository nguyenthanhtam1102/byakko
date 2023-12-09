package com.byakko.service.sales.common.domain.exception;


public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
