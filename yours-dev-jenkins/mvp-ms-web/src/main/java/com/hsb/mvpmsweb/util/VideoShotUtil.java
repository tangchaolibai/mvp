package com.hsb.mvpmsweb.util;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VideoShotUtil {
	/*
	static {
		System.setProperty("org.bytedeco.javacpp.maxphysicalbytes", "0");
		System.setProperty("org.bytedeco.javacpp.maxbytes", "0");
	}
	*/
	private final static Logger logger = LoggerFactory.getLogger(VideoShotUtil.class);
	
	public static void createVideoShot(String videoPath, String videoShotPath) throws IOException {
		File imgFile = new File(videoShotPath);
		if (!imgFile.getParentFile().exists()) {
			imgFile.getParentFile().mkdirs();
		}
		File videoFile = new File(videoPath);
		if (videoFile.exists()) {
			logger.info("The video exists.");
			//Instantiate the "Capture Video First Frame" object
			FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videoFile);
			ff.start();
			int ftp = ff.getLengthInFrames();
			int flag = 0;
			Frame frame = null;
			while (flag <= ftp) {
				// Get frame
				frame = ff.grabImage();
				// Filter the first 1 frame to avoid completely black pictures
				if ((flag > 1) && (frame != null)) {
					break;
				}
				flag++;
			}
			ImageIO.write(frameToBufferedImage(frame), "jpg", imgFile);
			ff.close();
			ff.stop();
		} else {
			logger.error("The video does not exist!");
		}
	}
	
	//Frame to stream
	private static RenderedImage frameToBufferedImage(Frame frame) {
	   //create BufferedImage object
       Java2DFrameConverter converter = new Java2DFrameConverter();
       BufferedImage bufferedImage = converter.getBufferedImage(frame);
       return bufferedImage;
	}
	
}
