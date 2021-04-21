package com.hsb.mvpmsweb.api.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;

import com.hsb.mvpmsweb.validator.AreaCodeValidator;

@Documented
@Constraint(validatedBy = AreaCodeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RUNTIME)
@NotBlank
public @interface AreaCode {
	
	String message() default "org.validation.constraints.AreaCode.message";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
