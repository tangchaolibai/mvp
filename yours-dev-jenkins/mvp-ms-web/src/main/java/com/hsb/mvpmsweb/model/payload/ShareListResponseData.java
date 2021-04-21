package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * ShareListResponseData
 */
@Data
@Validated
@ToString
public class ShareListResponseData {

	@JsonProperty("shareList")
	private List<ShareInfoData> shareList = null;

}

