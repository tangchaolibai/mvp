package com.hsb.mvpmsweb.model.payload.assistant.swiftpass;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.util.List;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to base product response data.
 */
@Data
@ApiModel(description = "This entity is used to base product response data.")
@Validated
@ToString
public class SPProductResponseData {
	
	@JsonProperty("id")
	private String id = null;
	
	@JsonProperty("name")
	private String name = null;
	
	@JsonProperty("isbn")
	private String isbn = null;
	
	@JsonProperty("category")
	private String category = null;
	
	@JsonProperty("subCategory")
	private String subCategory = null;
	
	@JsonProperty("shopId")
	private String shopId = null;
	
	@JsonProperty("createDateTime")
	private String createDateTime = null;
	
	@JsonProperty("contentUrl")
	private String contentUrl = null;
	
	@JsonProperty("isOnSale")
	private Boolean isOnSale = null;
	
	@JsonProperty("comments")
	private String comments = null;
	
	@JsonProperty("attributes")
	private List<Attributes> attributes = null;
	
	@JsonProperty("goodsOnSalePrices")
	private List<GoodsOnSalePrices> goodsOnSalePrices = null;
	
	@JsonProperty("photoGallery")
	private List<PhotoGallery> photoGallery = null;
	
	@JsonProperty("tags")
	private List<Object> tags = null;
	
	@JsonProperty("onSale")
	private Integer onSale = null;
	
	@JsonProperty("totalSold")
	private Integer totalSold = null;
	
}
