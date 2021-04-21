package com.hsb.mvpmsweb.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

public class WebSocketConstant {
	
	public static Map<String, WebSocketServerHandshaker> webSocketServerHandshakerMap = 
			new ConcurrentHashMap<String, WebSocketServerHandshaker>();
	public static Map<Integer, ChannelHandlerContext> onlineUserMap = 
	        new ConcurrentHashMap<Integer, ChannelHandlerContext>();
	public static Map<ChannelHandlerContext, Integer> offlineUserMap = 
	        new ConcurrentHashMap<ChannelHandlerContext, Integer>();
	
}
