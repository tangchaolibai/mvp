package com.hsb.mvpmsweb.model.payload;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.ToString;

@Validated
@Data
@ToString
public class OTPResponseData {
	
	private String phoneNumber;
	
	private String verificationCode;
	
	private String result;

}
