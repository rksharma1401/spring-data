package com.learn.storage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.learn.storage.model.Response;
import com.learn.storage.model.StoreMessage;

@Controller
public class WebSocketController {
	
	@MessageMapping("/message")
	@SendTo("/topic/response")
	public Response send(StoreMessage message) throws Exception { 
		System.out.println("Received request for:- "+message.toString());
	    return new Response("successfully got from :-  "+message.name());
	}
}