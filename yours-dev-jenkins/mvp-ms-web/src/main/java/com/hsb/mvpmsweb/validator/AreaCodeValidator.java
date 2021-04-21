package com.hsb.mvpmsweb.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hsb.mvpmsweb.api.annotation.AreaCode;

public class AreaCodeValidator implements ConstraintValidator<AreaCode, String>{

	@Override
	public boolean isValid(String areaCode, ConstraintValidatorContext context) {
		return "86".equals(areaCode) || "852".equals(areaCode);
	}

}
