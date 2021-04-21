package com.hsb.mvpmsweb.model.payload.assistant.swiftpass;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to goods on sale prices response data.
 */
@Data
@ApiModel(description = "This entity is used to goods on sale prices response data.")
@Validated
@ToString
public class GoodsOnSalePrices {
	
	@JsonProperty("id")
	private String id = null;
	
	@JsonProperty("createDateTime")
	private String createDateTime = null;
	
	@JsonProperty("goodsSku")
	private String goodsSku = null;
	
	@JsonProperty("primaryAttribute")
	private String primaryAttribute = null;
	
	@JsonProperty("secondaryAttribute")
	private String secondaryAttribute = null;
	
	@JsonProperty("price")
	private BigDecimal price = null;
	
	@JsonProperty("onSaleQuantity")
	private Integer onSaleQuantity = null;
	
	@JsonProperty("sold")
	private Integer sold = null;
	
	@JsonProperty("comments")
	private String comments = null;
	
}
