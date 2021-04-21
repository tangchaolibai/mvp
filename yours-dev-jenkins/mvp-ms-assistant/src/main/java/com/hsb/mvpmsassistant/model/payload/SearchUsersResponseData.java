package com.hsb.mvpmsassistant.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to search users response data.
 */
@ApiModel(description = "This entity is used to search users response data.")
@Validated
public class SearchUsersResponseData {
	@JsonProperty("users")
	private List<UserResponseData> users = null;

	/**
	 * search users
	 * @return users
	 **/
	@ApiModelProperty(value = "search users")
	public List<UserResponseData> getUsers() {
		return users;
	}

	public void setUsers(List<UserResponseData> users) {
		this.users = users;
	}

}
