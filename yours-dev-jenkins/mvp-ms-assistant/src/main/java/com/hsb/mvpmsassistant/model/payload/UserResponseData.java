package com.hsb.mvpmsassistant.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to user response data.
 */
@ApiModel(description = "This entity is used to user response data.")
@Validated
public class UserResponseData {
	
	@JsonProperty("id")
	private Integer id = null;

	@JsonProperty("nick_name")
	private String nickName = null;

	@JsonProperty("image_path")
	private String imagePath = null;
	
	@JsonProperty("followers")
	private Integer followers = null;
	
	@JsonProperty("is_follow")
	private Boolean isFollow = null;
	
	/**
	 * user id
	 * @return id
	 **/
	@ApiModelProperty(value = "user id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * user nickName
	 * @return user nickName
	 **/
	@ApiModelProperty(value = "user nickName")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * user image path
	 * @return imagePath
	 **/
	@ApiModelProperty(value = "user image path")
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	/**
	 * user followers
	 * @return followers
	 **/
	@ApiModelProperty(value = "user followers")
	public Integer getFollowers() {
		return followers;
	}

	public void setFollowers(Integer followers) {
		this.followers = followers;
	}
	
	/**
	 * if user is followed
	 * @return isFollow
	 **/
	@ApiModelProperty(value = "if user is followed")
	public Boolean getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(Boolean isFollow) {
		this.isFollow = isFollow;
	}

}
