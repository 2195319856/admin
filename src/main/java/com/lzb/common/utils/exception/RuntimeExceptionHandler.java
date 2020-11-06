package com.lzb.common.utils.exception;

import com.lzb.common.utils.response.ResponseData;
import com.lzb.system.admin.doman.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RuntimeExceptionHandler {


    @ExceptionHandler(AdminRuntimeException.class)
    public ResponseData<User> runtimeException(AdminRuntimeException e){

        return new ResponseData(e.getResponseEnum());
    }
    @ExceptionHandler(Exception.class)
    public ResponseData<User> Exception(Exception e){
        return new ResponseData(e.getMessage());
    }
}
