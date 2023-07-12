package com.learn.storage.rest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.learn.storage.model.Response;
import com.learn.storage.model.StoreMessage;

@Controller
public class WebSocketController {

	// Handles messages from /app/message. (Note the Spring adds the /app prefix for us).
	@MessageMapping("/message")
	// Sends the return value of this method to /topic/messages
	@SendTo("/topic/response")
	@CrossOrigin
	public Response send(StoreMessage message) throws Exception {
		System.out.println("Received request for:- " + message.toString());
		return new Response("successfully got from :-  " + message.name());
	}

	@MessageMapping("/stringMessage")
	@SendTo("/topic/stringResponse")
	@CrossOrigin
	public String sendString(String message) {
		System.out.println("sendString->Received request for:- " + message);
		return ("successfully got from :-  " + message);
	}
}
