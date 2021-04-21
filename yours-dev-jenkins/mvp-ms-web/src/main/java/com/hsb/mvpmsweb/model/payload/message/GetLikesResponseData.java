package com.hsb.mvpmsweb.model.payload.message;

import com.hsb.mvpmsweb.model.payload.PageResponseData;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get likes response data.
 */
@Data
@ApiModel(description = "This entity is used to get likes response data.")
@Validated
@ToString
public class GetLikesResponseData extends PageResponseData {
	
	private List<LikeResponseData> likes = null;

}
