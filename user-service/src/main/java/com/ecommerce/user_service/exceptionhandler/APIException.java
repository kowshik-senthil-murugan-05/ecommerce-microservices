package com.ecommerce.user_service.exceptionhandler;

public class APIException extends RuntimeException{

    public String message;

    public APIException(String message)
    {
        super(message);
    }
}
