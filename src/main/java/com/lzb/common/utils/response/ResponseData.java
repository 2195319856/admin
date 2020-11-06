package com.lzb.common.utils.response;


import lombok.Data;
@Data
public class ResponseData<T> {
    private int code;
    private String message;
    private T data;
    private String token;

    public ResponseData(ResponseEnum responseEnum,T data,String token){
        this.code=responseEnum.getCode();
        this.message=responseEnum.getMessage();
        this.data=data;
        this.token=token;
    }
    public ResponseData(ResponseEnum responseEnum){
        this.code=responseEnum.getCode();
        this.message=responseEnum.getMessage();

    }
    public ResponseData(ResponseEnum responseEnum, T data){
        this.code=responseEnum.getCode();
        this.message=responseEnum.getMessage();
        this.data=data;
    }
    public ResponseData(String message){

        this.message=message;

    }
}
