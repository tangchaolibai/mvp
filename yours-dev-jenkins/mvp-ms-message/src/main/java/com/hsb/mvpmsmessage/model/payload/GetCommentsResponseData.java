package com.hsb.mvpmsmessage.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to get comments response data.
 */
@ApiModel(description = "This entity is used to get comments response data.")
@Validated
public class GetCommentsResponseData {
	@JsonProperty("comments")
	private List<CommentResponseData> comments = null;

	public GetCommentsResponseData comments(List<CommentResponseData> comments) {
		this.comments = comments;
		return this;
	}

	public GetCommentsResponseData addCommentsItem(CommentResponseData commentsItem) {
		if (this.comments == null) {
			this.comments = new ArrayList<>();
		}
		this.comments.add(commentsItem);
		return this;
	}

	/**
	 * comments
	 * @return comments
	 **/
	@ApiModelProperty(value = "comments")
	public List<CommentResponseData> getComments() {
		return comments;
	}

	public void setComments(List<CommentResponseData> comments) {
		this.comments = comments;
	}

}
