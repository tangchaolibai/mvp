package com.hsb.mvpmsweb.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hsb.mvpmsweb.api.annotation.PaymentInstance;

public class PaymentInstanceValidator implements ConstraintValidator<PaymentInstance, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return "ALIPAYHK".equals(value) || "ALIPAYCN".equals(value);
	}

}
