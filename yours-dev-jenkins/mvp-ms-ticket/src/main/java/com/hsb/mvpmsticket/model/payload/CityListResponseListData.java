package com.hsb.mvpmsticket.model.payload;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "This entity is used to get city list data.")
@Validated
@Data
public class CityListResponseListData {

	private List<CityResponseData> data;
}
