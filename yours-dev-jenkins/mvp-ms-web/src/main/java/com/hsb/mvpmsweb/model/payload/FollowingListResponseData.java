package com.hsb.mvpmsweb.model.payload;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

/**
 * FollowingListResponseData
 */
@Data
@Validated
@ToString
public class FollowingListResponseData {

	@JsonProperty("followingListData")
	private List<ShareInfoData> followingListData = null;

	@JsonProperty("recentShareCount")
	private Integer recentShareCount = null;

}

