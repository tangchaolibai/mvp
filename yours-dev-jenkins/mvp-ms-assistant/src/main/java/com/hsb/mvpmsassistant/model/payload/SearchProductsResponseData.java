package com.hsb.mvpmsassistant.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to search products response data.
 */
@ApiModel(description = "This entity is used to search products response data.")
@Validated
public class SearchProductsResponseData {
	@JsonProperty("products")
	private List<ProductResponseData> products = null;

	/**
	 * search products
	 * @return products
	 **/
	@ApiModelProperty(value = "search products")
	public List<ProductResponseData> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponseData> products) {
		this.products = products;
	}

}
