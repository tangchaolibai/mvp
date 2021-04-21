package com.hsb.mvpmsmessage.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

public class WebSocketConstant {
	
	public static Map<String, WebSocketServerHandshaker> webSocketServerHandshakerMap = 
			new ConcurrentHashMap<String, WebSocketServerHandshaker>();
	public static Map<String, ChannelHandlerContext> onlineUserMap = 
	        new ConcurrentHashMap<String, ChannelHandlerContext>();
	public static Map<ChannelHandlerContext, String> offlineUserMap = 
	        new ConcurrentHashMap<ChannelHandlerContext, String>();
	
}
