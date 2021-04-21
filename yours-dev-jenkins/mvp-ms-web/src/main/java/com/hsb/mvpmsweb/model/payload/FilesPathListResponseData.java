package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * FilesPathListResponseData
 */
@Data
@Validated
@ToString
public class FilesPathListResponseData {

	@JsonProperty("filesPathList")
	@Valid
	private List<String> filesPathList = null;

}

