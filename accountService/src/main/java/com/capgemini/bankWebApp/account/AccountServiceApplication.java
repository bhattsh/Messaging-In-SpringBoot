package com.capgemini.bankWebApp.account;

import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	   @Bean
	    public MappingJackson2MessageConverter jackson2Converter() {
	        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	        return converter;
	    }

	    @Bean
	    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
	        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
	        factory.setMessageConverter(jackson2Converter());
	        return factory;
	    }
//
//	    @Override
//	    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
//	        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
//	    }
}

