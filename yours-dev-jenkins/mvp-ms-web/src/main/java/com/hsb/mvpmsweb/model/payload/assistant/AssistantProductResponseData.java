package com.hsb.mvpmsweb.model.payload.assistant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to product response data.
 */
@Data
@ApiModel(description = "This entity is used to product response data.")
@Validated
@ToString
public class AssistantProductResponseData {
	
	private String id = null;

	private String name = null;

	private String price = null;
	
	private String imgUrl = null;
	
	private String contentUrl = null;
	
	@ApiModelProperty(example = "2020-01-01 12:00:00")
	private LocalDateTime creationDateTime = null;
	
	private String type = null;
	
}
