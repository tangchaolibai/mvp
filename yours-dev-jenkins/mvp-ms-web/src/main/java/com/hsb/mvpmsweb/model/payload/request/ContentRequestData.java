package com.hsb.mvpmsweb.model.payload.request;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
public class ContentRequestData {
	
	 @JsonProperty("contentName")
	 private String contentName = null;

	 @JsonProperty("contentPath")
	 private String contentPath = null;
	 
	 @JsonProperty("contentSeconds")
	 private Integer contentSeconds = null;
	 
	 @JsonProperty("contentThumbnailUrl")
	 private String contentThumbnailUrl = "";

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public Integer getContentSeconds() {
		return contentSeconds;
	}

	public void setContentSeconds(Integer contentSeconds) {
		this.contentSeconds = contentSeconds;
	}

	public String getContentThumbnailUrl() {
		return contentThumbnailUrl;
	}

	public void setContentThumbnailUrl(String contentThumbnailUrl) {
		this.contentThumbnailUrl = contentThumbnailUrl;
	}

	@Override
	public String toString() {
		return "ContentRequestData [contentName=" + contentName + ", contentPath=" + contentPath + ", contentSeconds="
				+ contentSeconds + ", contentThumbnailUrl=" + contentThumbnailUrl + "]";
	}
	
}
