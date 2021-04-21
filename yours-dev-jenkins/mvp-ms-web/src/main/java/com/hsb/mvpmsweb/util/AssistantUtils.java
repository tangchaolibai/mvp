package com.hsb.mvpmsweb.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.hsb.mvpmsweb.model.domain.Photo;
import com.hsb.mvpmsweb.model.domain.Video;

public class AssistantUtils {
	
	public static List<Photo> initPhotoList = Arrays.asList(
			new Photo(1, "第一张照片", "这是第一张照片", "深圳", "D:/temp/photo/1/第一张照片.jpg", 1, LocalDateTime.now()), 
			new Photo(2, "第二张照片", "这是第二张照片", "深圳", "D:/temp/photo/1/第二张照片.jpg", 1, LocalDateTime.now()),
			new Photo(3, "第三张照片", "这是第三张照片", "深圳", "D:/temp/photo/1/第三张照片.jpg", 1, LocalDateTime.now()));
	
	public static List<Video> initVideoList = Arrays.asList(
			new Video(1, "第一个视频", "这是第一个视频", "深圳", "D:/temp/video/2/第一个视频.avi", 68, "D:/temp/video/2/thumbnail/第一个视频.jpg", 2, LocalDateTime.now()), 
			new Video(2, "第二个视频", "这是第二个视频", "深圳", "D:/temp/video/2/第二个视频.avi", 38, "D:/temp/video/2/thumbnail/第一个视频.jpg", 2, LocalDateTime.now()),
			new Video(3, "第三个视频", "这是第三个视频", "深圳", "D:/temp/video/2/第三个视频.avi", 44, "D:/temp/video/2/thumbnail/第一个视频.jpg", 2, LocalDateTime.now()));
	
	private AssistantUtils() {}
	
}
