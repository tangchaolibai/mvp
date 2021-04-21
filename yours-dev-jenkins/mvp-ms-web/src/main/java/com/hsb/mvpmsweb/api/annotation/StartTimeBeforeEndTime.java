package com.hsb.mvpmsweb.api.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hsb.mvpmsweb.validator.StartTimeBeforeEndTimeValidator;

@Documented
@Retention(RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = StartTimeBeforeEndTimeValidator.class)
public @interface StartTimeBeforeEndTime {
	
	String message() default "org.validation.constraints.StartTimeBeforeEndTime.message";
	 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    /**
     * @return The first field
     */
    String startTime();
 
    /**
     * @return The second field
     */
    String endTime();
}
