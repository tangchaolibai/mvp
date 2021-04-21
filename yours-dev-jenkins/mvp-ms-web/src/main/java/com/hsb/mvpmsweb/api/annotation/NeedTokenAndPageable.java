package com.hsb.mvpmsweb.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiImplicitParams({
    @ApiImplicitParam(paramType = "header", name = "Authorization", dataType = "String", required = true, value = "token", defaultValue="Bearer "),
    @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", defaultValue = "0", example = "0", required = true, value = "Results page you want to retrieve (0..N)"),
    @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", defaultValue = "10", example = "10", required = true, value = "Number of records per page.")
})
public @interface NeedTokenAndPageable {

}
