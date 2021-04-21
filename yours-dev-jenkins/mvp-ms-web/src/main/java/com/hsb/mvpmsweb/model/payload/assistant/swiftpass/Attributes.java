package com.hsb.mvpmsweb.model.payload.assistant.swiftpass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to products attributes response data.
 */
@Data
@ApiModel(description = "This entity is used to products attributes response data.")
@Validated
@ToString
public class Attributes {
	
	@JsonProperty("id")
	private String id = null;
	
	@JsonProperty("createDateTime")
	private LocalDateTime createDateTime = null;
	
	@JsonProperty("goodsSku")
	private String goodsSku = null;
	
	@JsonProperty("attributeCode")
	private String attributeCode = null;
	
	@JsonProperty("isMultiOption")
	private Boolean isMultiOption = null;
	
	@JsonProperty("comments")
	private String comments = null;
	
	@JsonProperty("content")
	private String content = null;
	
	@JsonProperty("details")
	private List<Details> details = null;
	
}
