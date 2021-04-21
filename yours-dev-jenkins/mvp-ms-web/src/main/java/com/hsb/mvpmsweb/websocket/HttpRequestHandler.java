package com.hsb.mvpmsweb.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hsb.mvpmsweb.constant.WebSocketConstant;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

@Component
@Sharable
public class HttpRequestHandler  extends SimpleChannelInboundHandler<Object> {

	private static final Logger logger = LoggerFactory.getLogger("WebSocket");
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof FullHttpRequest) {
			logger.info("user connect  address: {}", ctx.channel().remoteAddress().toString());
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		} else if (msg instanceof WebSocketFrame) {
			logger.info("user online address: {}", ctx.channel().remoteAddress().toString());
			ctx.fireChannelRead(((WebSocketFrame) msg).retain());
		} 
	}

	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
		if (!req.decoderResult().isSuccess()) {
			sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		/*WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
				"ws:/" + ctx.channel() + "/websocket", null, false);*/
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
				"ws://12.1.1.31:1212/websocket", null, false);
		WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(req);
		WebSocketConstant.webSocketServerHandshakerMap.put(ctx.channel().id().asLongText(), handshaker);

		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), req);
		}

	}

	private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
		if (res.status().code() == 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
		}

		boolean keepAlive = HttpUtil.isKeepAlive(req);
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if (!keepAlive) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error(cause.getMessage(), cause);
		ctx.close();
	}

}
