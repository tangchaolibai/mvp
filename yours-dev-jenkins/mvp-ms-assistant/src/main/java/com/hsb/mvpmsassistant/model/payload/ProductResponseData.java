package com.hsb.mvpmsassistant.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to product response data.
 */
@ApiModel(description = "This entity is used to product response data.")
@Validated
public class ProductResponseData {
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("price")
	private String price = null;
	
	@JsonProperty("img_url")
	private String imgUrl = null;
	
	@JsonProperty("creation_date")
	private LocalDate creationDate = null;
	
	@JsonProperty("type")
	private String type = null;
	
	/**
	 * product id
	 * @return id
	 **/
	@ApiModelProperty(value = "product id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * product name
	 * @return name
	 **/
	@ApiModelProperty(value = "product name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * product price
	 * @return price
	 **/
	@ApiModelProperty(value = "product price")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * product image url
	 * @return imgUrl
	 **/
	@ApiModelProperty(value = "product image url")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * product creation date
	 * @return creationDate
	 **/
	@ApiModelProperty(value = "product creation date")
	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * product type
	 * @return type
	 **/
	@ApiModelProperty(value = "product type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
