package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * AllFollowingUserListResponseData
 */
@Data
@Validated
@ToString
public class AllFollowingUserListResponseData {

	@JsonProperty("userList")
	@Valid
	private List<UserDetailResponseData> userList = null;

}

