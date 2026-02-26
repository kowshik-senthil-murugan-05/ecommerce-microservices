package com.ecommerce.order_service.exceptionhandler;

public class APIException extends RuntimeException{

    public String message;

    public APIException(String message)
    {
        super(message);
    }
}
