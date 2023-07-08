package com.learn.storage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.learn.storage.model.StoreMessage;

@Controller
public class WebSocketController {
	
	@MessageMapping("/input")
	@SendTo("/topic/response")
	public String send(StoreMessage message) throws Exception { 
	    return "successfully got from :-  "+message.name();
	}
}