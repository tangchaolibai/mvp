package com.hsb.mvpmsweb.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import com.hsb.mvpmsweb.api.annotation.PictureType;

public class PictureTypeValidator implements ConstraintValidator<PictureType, MultipartFile>{

	@Override
	public boolean isValid(MultipartFile posterPic, ConstraintValidatorContext context) {
		String name = posterPic.getOriginalFilename();
		String subffix = name.substring(name.lastIndexOf('.'));
		return "jpg".equals(subffix) || "png".equals(subffix);
	}

}
