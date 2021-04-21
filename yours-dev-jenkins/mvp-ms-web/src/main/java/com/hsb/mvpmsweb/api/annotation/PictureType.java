package com.hsb.mvpmsweb.api.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import com.hsb.mvpmsweb.validator.PictureTypeValidator;

@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER})
@NotNull
@Constraint(validatedBy = PictureTypeValidator.class)
public @interface PictureType {
	
	String message() default "org.validation.constraints.PictureType.message";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
