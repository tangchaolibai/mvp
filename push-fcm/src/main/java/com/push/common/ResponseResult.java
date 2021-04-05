package com.push.common;

import lombok.Data;

@Data
public class ResponseResult<T> {

    private Integer code;
    private String message;
    private T data;

    public ResponseResult(){
        this.code = 200;
        this.message = "success";
    }

    public ResponseResult(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public ResponseResult(T data){
        this.code = 200;
        this.data = data;
        this.message = "success";
    }

}
