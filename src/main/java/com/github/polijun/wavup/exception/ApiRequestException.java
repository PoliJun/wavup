package com.github.polijun.wavup.exception;

public class ApiRequestException extends  RuntimeException{
    public ApiRequestException(String message){
        super(message);
    }
    public ApiRequestException(String message, Throwable cause){
        super(message, cause);
    }
    public ApiRequestException(Throwable cause){
        super(cause);
    }
    public ApiRequestException(){
        super();
    }
    public ApiRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public ApiRequestException(String message, Throwable cause, int statusCode){
        super(message, cause);
    }
}
