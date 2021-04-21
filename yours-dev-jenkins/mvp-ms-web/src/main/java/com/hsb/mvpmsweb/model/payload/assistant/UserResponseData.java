package com.hsb.mvpmsweb.model.payload.assistant;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to user response data.
 */
@Data
@ApiModel(description = "This entity is used to user response data.")
@Validated
@ToString
public class UserResponseData {
	
	private Integer id = null;

	private String nickName = null;

	private String imagePath = null;
	
	private Integer followers = null;
	
	private Boolean isFollow = null;
	
}
