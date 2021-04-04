package com.push.common;

import lombok.Data;

@Data
public class ResponseResult<T> {

    private String code;
    private String message;
    private T data;
}
