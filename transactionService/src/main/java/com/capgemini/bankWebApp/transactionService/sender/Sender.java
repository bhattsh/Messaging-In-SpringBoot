package com.capgemini.bankWebApp.transactionService.sender;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.bankWebApp.transactionService.entity.Transaction;

@Component
public class Sender{
	
	@Autowired
	private RabbitMessagingTemplate rabbitTemplate;

	public void send(Transaction transaction) {
		rabbitTemplate.convertAndSend("accountQueue", transaction);
	}
}
