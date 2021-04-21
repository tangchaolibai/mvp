package com.hsb.mvpmsweb.validator;

import java.lang.reflect.Field;
import java.time.LocalTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsb.mvpmsweb.api.annotation.StartTimeBeforeEndTime;

public class StartTimeBeforeEndTimeValidator implements ConstraintValidator<StartTimeBeforeEndTime, Object> {
	
	private static final Logger logger = LoggerFactory.getLogger(StartTimeBeforeEndTimeValidator.class);

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			Field fieldStartTime = value.getClass().getDeclaredField("startTime");
			fieldStartTime.setAccessible(true);
			final LocalTime startTime = (LocalTime)fieldStartTime.get(value);
			Field fieldEndTime =  value.getClass().getDeclaredField("endTime");
			fieldEndTime.setAccessible(true);
			final LocalTime endTime = (LocalTime)fieldEndTime.get(value);
			return startTime.isBefore(endTime);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			logger.error(e.getMessage());
			return false;
		}
		
	}

}
