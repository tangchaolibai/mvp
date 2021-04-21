package com.hsb.mvpmsweb.api.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hsb.mvpmsweb.validator.AreaCodeMatchPhoneNumberValidator;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = AreaCodeMatchPhoneNumberValidator.class)
@Documented
public @interface AreaCodeMatchPhoneNumber {
	
	String message() default "org.validation.constraints.AreaCodeMatchPhoneNumber.message";
	 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    /**
     * @return The first field
     */
    String areaCode();
 
    /**
     * @return The second field
     */
    String mobilePhone();
 
}
