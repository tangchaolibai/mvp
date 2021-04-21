package com.hsb.mvpmsmessage.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.hsb.mvpmsmessage.model.entity.Comment;
import com.hsb.mvpmsmessage.model.entity.Like;
import com.hsb.mvpmsmessage.model.entity.Message;
import com.hsb.mvpmsmessage.model.entity.Reply;

public class MessageUtils {
	
	public static List<Comment> initCommentList = Arrays.asList(
			new Comment(1, 1, 1, "图片真好看!", LocalDateTime.now()), 
			new Comment(2, 1, 2, "视频真好看!", LocalDateTime.now()),
			new Comment(3, 1, 3, "我也有这个图片!", LocalDateTime.now()));
	
	public static List<Like> initLikeList = Arrays.asList(
			new Like(1, 2, 1, LocalDateTime.now()), 
			new Like(2, 2, 2, LocalDateTime.now()),
			new Like(3, 2, 3, LocalDateTime.now()));
	
	public static List<Reply> initReplyList = Arrays.asList(
			new Reply(1, 1, 2, 1, "谢谢!", LocalDateTime.now()), 
			new Reply(2, 2, 3, 2, "谢谢!", LocalDateTime.now()),
			new Reply(3, 3, 1, 3, "谢谢!", LocalDateTime.now()));
	
	public static List<Message> initMessageList = Arrays.asList(
			new Message(1, "System", "系统更新版本至0.0.2!", 1, 4, null, "N", LocalDateTime.now()), 
			new Message(2, "System", "欢迎使用YOURS!", 1, 5, null, "Y", LocalDateTime.now()),
			new Message(3, "Chat", "在吗?", 4, 5, null, "N", LocalDateTime.now()),
			new Message(4, "Chat", "Hello!", 6, 7, null, "N", LocalDateTime.now()));
	
	private MessageUtils() {}
	
}
