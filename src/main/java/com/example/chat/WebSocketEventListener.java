package com.example.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor 
@Slf4j
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
 public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
	
	 StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());
	 String userName=(String)headerAccessor.getSessionAttributes().get("username");
	 if(userName!=null) {
		 logger.info("User Disconnected : " + userName);
		 ChatMessage chatMessage = new ChatMessage();
         chatMessage.setType(chatMessage.getType().LEAVE);
         chatMessage.setSender(userName);
         messagingTemplate.convertAndSend("/topic/public", chatMessage);
	 }
	 
	 
	 
 }
 
}
