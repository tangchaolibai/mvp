package com.hsb.mvpmsweb.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hsb.mvpmsweb.api.annotation.Currency;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

	@Override
	public boolean isValid(String currency, ConstraintValidatorContext context) {
		return "HKD".equals(currency) || "CNY".equals(currency);
	}

}
