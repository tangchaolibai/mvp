package com.hsb.mvpmsweb.model.payload;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
public class ContentResponseData {
	
	 @JsonProperty("contentId")
	 private Integer contentId = null;

	 @JsonProperty("contentName")
	 private String contentName = null;

	 @JsonProperty("contentDes")
	 private String contentDes = null;

	 @JsonProperty("contentLoc")
	 private String contentLoc = null;

	 @JsonProperty("contentPath")
	 private String contentPath = null;
	 
	 @JsonProperty("contentSeconds")
	 private Integer contentSeconds = null;
	 
	 @JsonProperty("contentType")
	 private String contentType = null;

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getContentDes() {
		return contentDes;
	}

	public void setContentDes(String contentDes) {
		this.contentDes = contentDes;
	}

	public String getContentLoc() {
		return contentLoc;
	}

	public void setContentLoc(String contentLoc) {
		this.contentLoc = contentLoc;
	}

	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getContentSeconds() {
		return contentSeconds;
	}

	public void setContentSeconds(Integer contentSeconds) {
		this.contentSeconds = contentSeconds;
	}

	@Override
	public String toString() {
		return "ContentResponseData [contentId=" + contentId + ", contentName=" + contentName + ", contentDes="
				+ contentDes + ", contentLoc=" + contentLoc + ", contentPath=" + contentPath + ", contentSeconds="
				+ contentSeconds + ", contentType=" + contentType + "]";
	}
	
}
