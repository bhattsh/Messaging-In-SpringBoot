package com.capgemini.bankWebApp.account.receiver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import com.capgemini.bankWebApp.account.resource.AccountResource;
import com.capgemini.bankWebApp.transactionService.entity.Transaction;

@Component
public class Receiver {

	@Autowired
	private AccountResource resource;

	@Bean
	public Queue queue() {
		return new Queue("accountQueue", false);
	}

	@RabbitListener(queues = "accountQueue")
	public void receiver(Transaction transaction) {
		resource.updateBalance(transaction);
	}
}
