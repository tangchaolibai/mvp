package com.hsb.mvpmsweb.validator;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsb.mvpmsweb.api.annotation.AreaCodeMatchPhoneNumber;

public class AreaCodeMatchPhoneNumberValidator implements ConstraintValidator<AreaCodeMatchPhoneNumber, Object> {
	
	private static final Logger logger = LoggerFactory.getLogger(AreaCodeMatchPhoneNumberValidator.class);

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			Field fieldAreaCode = value.getClass().getDeclaredField("areaCode");
			fieldAreaCode.setAccessible(true);
			final String areaCode = (String)fieldAreaCode.get(value);
			Field fieldMobilePhone =  value.getClass().getDeclaredField("mobilePhone");
			fieldMobilePhone.setAccessible(true);
			final String phoneNumber = (String)fieldMobilePhone.get(value);
			return isPhoneLegal(phoneNumber, areaCode);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			logger.error(e.getMessage());
			return false;
		} 
	}
	
	/**
     * 大陆号码或香港号码均可
     */
    private boolean isPhoneLegal(String phoneNumber, String areaCode) {
    	if("852".equals(areaCode))
    		return isHKPhoneLegal(phoneNumber);
    	else if("86".equals(areaCode))
    		return isChinaPhoneLegal(phoneNumber);
    	else 
    		return false;
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 此方法中前三位格式有： 13+任意数 15+除4的任意数 18+除1和4的任意数
     * 17+除9的任意数 147
     */
    private boolean isChinaPhoneLegal(String str) {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    private boolean isHKPhoneLegal(String str) {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
