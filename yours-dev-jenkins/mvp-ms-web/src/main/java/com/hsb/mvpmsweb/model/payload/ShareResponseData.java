package com.hsb.mvpmsweb.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;

/**
 * ShareResponseData
 */
@Validated
@Data
public class ShareResponseData   {
  @JsonProperty("shareId")
  private Integer shareId = null;

  @JsonProperty("userId")
  private Integer userId = null;
   
  @JsonProperty("userImgPath")
  private String userImgPath = null;
  
  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("postStatus")
  private String postStatus = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("fileType")
  private String fileType = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("toUserId")
  private Integer toUserId = null;

  @JsonProperty("shareCount")
  private Integer shareCount = null;

  @JsonProperty("likeCount")
  private Integer likeCount = null;

  @JsonProperty("commentCount")
  private Integer commentCount = null;

  @JsonProperty("viewAmount")
  private Integer viewAmount = null;

  @JsonProperty("createTime")
  private LocalDateTime createTime = null;

  @JsonProperty("lastUpdateTime")
  private LocalDateTime lastUpdateTime = null;

}

