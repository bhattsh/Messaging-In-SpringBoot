package com.example.messagingReceiver;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessagingReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingReceiverApplication.class, args);
	}

	@Bean
	public Queue queue() {
		return new Queue("HelloQueue", false);
	}
}

