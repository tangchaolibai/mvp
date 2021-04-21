package com.hsb.mvpmsweb.websocket.listener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class ChanneListener implements ChannelFutureListener {

	public static boolean isSuccess = false;
	
	@Override
	public void operationComplete(ChannelFuture future) throws Exception {
		isSuccess = future.isSuccess();
	}
	
}
