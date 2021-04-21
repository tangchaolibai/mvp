package com.hsb.mvpmsweb.api.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hsb.mvpmsweb.validator.PaymentInstanceValidator;

@Documented
@Retention(RUNTIME)
@Target({PARAMETER, FIELD})
@Constraint(validatedBy = PaymentInstanceValidator.class)
public @interface PaymentInstance {
	
	String message() default "org.validation.constraints.PaymentInstance.message";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
