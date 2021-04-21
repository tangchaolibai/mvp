package com.hsb.mvpmsweb.model.payload.request;

import java.time.LocalDate;

import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "This entity is used to describe an edit user Request.")
@Data
@Accessors(fluent = true)
public class EditUserRequest {
	
	@JsonProperty("userId")
	@ApiModelProperty(required = true, value = "User Id")
	private Integer userId = null;
	
	@JsonProperty("nickName")
	@ApiModelProperty(required = true, value = "Nick name")
	private String nickName = null;
	
	@JsonProperty("introduction")
	@ApiModelProperty(required = true, value = "introduction")
	private String introduction = null;
	
	@JsonProperty("gender")
	@ApiModelProperty(required = true, value = "gender")
	private Integer gender = null;
	
	@JsonProperty("dateOfBirth")
	@ApiModelProperty(required = true, value = "dateOfBirth", example = "2000-01-01")
	@Past
	private LocalDate dateOfBirth = null;
	
	@JsonProperty("location")
	@ApiModelProperty(required = true, value = "location")
	private String location = null;
	
	@JsonProperty("userImgPath")
	@ApiModelProperty(required = true, value = "User Img Path")
	private String userImgPath;
	
	@JsonProperty("coverImgPath")
	@ApiModelProperty(required = true, value = "Cover Img Path")
	private String coverImgPath;
}
