package com.hsb.mvpmsweb.websocket.init;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsb.mvpmsweb.websocket.WebSocketServer;

@Component
@Scope("singleton")
public class InitWebSocket {

	@Autowired
	private WebSocketServer webSocketServer;
	
	private Thread nettyThread;
	
	@PostConstruct
	public void init() {
		nettyThread = new Thread(webSocketServer);
		nettyThread.start();
	}
	
	@PreDestroy
    public void close() {
        webSocketServer.close();
    }
	
}
