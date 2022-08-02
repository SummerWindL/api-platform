package com.platform.api.exception;

/**
 * @author : yanl
 * @version V1.0
 * @Description:
 * @date : 2022/7/9
 */
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 7637174399993976947L;

    public ApiException(){ }

    public ApiException(String msg){
        super(msg);
    }

    public ApiException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}

