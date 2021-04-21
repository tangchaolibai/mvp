package com.hsb.mvpmsweb.api.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;

import com.hsb.mvpmsweb.validator.CurrencyValidator;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@NotBlank
@Constraint(validatedBy = CurrencyValidator.class)
public @interface Currency {
	
	String message() default "org.validation.constraints.Currency.message";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
