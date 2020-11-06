package com.lzb.common.utils.exception;

import com.lzb.common.utils.response.ResponseEnum;
import lombok.Data;

@Data
public class AdminRuntimeException extends RuntimeException {
    private ResponseEnum responseEnum;
    public AdminRuntimeException(ResponseEnum responseEnum){
        this.responseEnum=responseEnum;
    }
}
