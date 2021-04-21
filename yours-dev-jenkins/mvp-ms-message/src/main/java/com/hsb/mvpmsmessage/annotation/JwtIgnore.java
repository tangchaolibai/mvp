package com.hsb.mvpmsmessage.annotation;

import java.lang.annotation.*;

/**
 * JWT ignore validation annotation
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
