package com.hsb.mvpmsweb.model.payload;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.ToString;

@Validated
@Data
@ToString
public class ValidateRegisterResponseData {
	
	private Boolean isRegister = false;

}
