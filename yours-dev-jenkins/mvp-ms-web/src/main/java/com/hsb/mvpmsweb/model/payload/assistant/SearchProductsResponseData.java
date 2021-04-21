package com.hsb.mvpmsweb.model.payload.assistant;

import com.hsb.mvpmsweb.model.payload.PageResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to search products response data.
 */
@Data
@ApiModel(description = "This entity is used to search products response data.")
@Validated
@ToString
public class SearchProductsResponseData extends PageResponseData {
	
	private List<AssistantProductResponseData> products = null;

}