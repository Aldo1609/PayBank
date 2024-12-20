package com.aldob.exceptions;

public class CuentasException extends RuntimeException{

    public CuentasException(String message){
        super(message);
    }

    public CuentasException(String message, Throwable cause){
        super(message, cause);
    }

}
