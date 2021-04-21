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
})
public @interface NeedToken {

}
