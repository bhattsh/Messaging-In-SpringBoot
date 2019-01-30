package com.example.messagingReceiver.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@RabbitListener(queues="HelloQueue")
	public void showMessage(String message) {
		System.out.println("message in queue is: "+message);
	}
}
