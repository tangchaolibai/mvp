package com.hsb.mvpmsweb.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hsb.mvpmsweb.constant.WebSocketConstant;
import com.hsb.mvpmsweb.service.ChatService;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

@Component
@Sharable
public class WebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

	private static final Logger logger = LoggerFactory.getLogger("WebSocket");
	
	@Autowired
	private ChatService chatService;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
		logger.info("ChannelHandlerContext {}", ctx);
		handlerWebSocketFrame(ctx, frame);
	}

	private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		logger.info("handlerWebSocketFrame frame instanceof CloseWebSocketFrame :{}", frame instanceof CloseWebSocketFrame);
		if (frame instanceof CloseWebSocketFrame) {
			String id = ctx.channel().id().asLongText();
			WebSocketServerHandshaker handshaker = WebSocketConstant.webSocketServerHandshakerMap.get(id);
			logger.info("handlerWebSocketFrame handshaker :{}", handshaker);
			if (handshaker == null) {
                sendErrorMessage(ctx, "Not Exist Client Connect!");
            } else {
                handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            }
            return;
		}
		String rquestContxt = ((TextWebSocketFrame)frame).text();
		JSONObject param = null;
		try {
			param = JSONObject.parseObject(rquestContxt);
		} catch (Exception e) {
			logger.error("String Format JSON Fail", e);
			sendErrorMessage(ctx, "String Format JSON Fail");
		}
		if (param == null) {
            sendErrorMessage(ctx, "The Parament is null");
            return;
        }
		
		boolean checkUser = chatService.checkUser(param, ctx);
		logger.info("recive all message from ui:{}", rquestContxt);
		if (checkUser) {
			String type =(String) param.get("type");
			switch (type) {
			case "online":
				chatService.online(param, ctx);
				break;
			case "downline":
				chatService.downline(param);
				break;
			case "chat":
				boolean isFriend = chatService.checkIsFriend(param);
				if (isFriend) {
					chatService.singleSend(param, ctx);
				}else {
					sendErrorMessage(ctx, "not is your friend");
				}
				break;
			case "add_request":
				chatService.addFriendRequest(param, ctx);
				break;
			case "add_response":
				chatService.addFriendResponse(param, ctx);
				break;
			case "at":
				chatService.atFriend(param, ctx);
				break;
			case "follower":
				chatService.followMe(param, ctx);
				break;
			case "comments":
				chatService.comments(param, ctx);
				break;
			case "like":
				chatService.like(param, ctx);
				break;
			}
		}else {
			sendErrorMessage(ctx, "user not exsit");
		}
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("the user: online {}", ctx.channel().remoteAddress());
	}
	
	@Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("user {} break link", ctx.channel().remoteAddress().toString());
        chatService.offline(ctx);
    }
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	logger.error(cause.getMessage(), cause);
        ctx.close();
    }
	
	private void sendErrorMessage(ChannelHandlerContext ctx, String errorMsg) {
		logger.info("sendErrorMessage errorMsg: {}", errorMsg);
		ctx.channel().writeAndFlush(new TextWebSocketFrame(errorMsg));
	}

	/*@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent)evt;
			if (event.state().equals(IdleState.READER_IDLE)) {
				System.out.println("No data was received for a while.");
			}else if (event.state().equals(IdleState.WRITER_IDLE)) {
				System.out.println("No data was sent for a while.");
			}else if (event.state().equals(IdleState.ALL_IDLE)) {
				System.out.println("No data was either received or sent for a while.");
			}
		}
	}*/
	
}
