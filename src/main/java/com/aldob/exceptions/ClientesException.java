package com.aldob.exceptions;

public class ClientesException extends RuntimeException {

    public ClientesException(String message){
        super(message);
    }

    public ClientesException(String message, Throwable cause){
        super(message, cause);
    }

}
