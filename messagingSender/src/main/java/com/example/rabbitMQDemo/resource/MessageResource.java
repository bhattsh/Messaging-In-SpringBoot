package com.example.rabbitMQDemo.resource;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageResource {

	@Autowired
	RabbitMessagingTemplate rabitTemplate;
	

	@GetMapping("/{message}")
	public void sendMessage(@PathVariable String message) {
		rabitTemplate.convertAndSend("HelloQueue",message);
	}
}
