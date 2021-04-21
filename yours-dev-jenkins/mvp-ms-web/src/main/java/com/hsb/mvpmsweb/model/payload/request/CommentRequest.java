package com.hsb.mvpmsweb.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import org.springframework.validation.annotation.Validated;

/**
 * This entity is used to describe a comment request.
 */
@ApiModel(description = "This entity is used to describe a comment request.")
@Validated
@Data
public class CommentRequest   {
	
  @JsonProperty("shareId")
  @ApiModelProperty(required = true,value = "Share Id")
  private Integer shareId;

  @JsonProperty("entityId")
  @ApiModelProperty(required = true,value = "Entity Id")
  private Integer entityId;

  @JsonProperty("fromUserId")
  @ApiModelProperty(required = true,value = "From User Id")
  private Integer fromUserId;

  @JsonProperty("toUserId")
  @ApiModelProperty(required = true,value = "To User Id")
  private Integer toUserId;

  @JsonProperty("comment")
  @ApiModelProperty(required = true,value = "Comment")
  private String comment;

}

