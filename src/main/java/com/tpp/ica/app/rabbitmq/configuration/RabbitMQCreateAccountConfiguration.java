package com.tpp.ica.app.rabbitmq.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQCreateAccountConfiguration {

	private Logger logger = LoggerFactory.getLogger(RabbitMQCreateAccountConfiguration.class);

	public static final String CREATE_ACCOUNT_EXCHANGE_NAME = "create-account-exchange";
	public static final String CREATE_ACCOUNT_DLX_NAME = "create-account-dlx"; // DLX - Dead Letter Exchange

	public static final String CREATE_ACCOUNT_QUEUE_NAME = "create-account";
	public static final String CREATE_ACCOUNT_DEAD_LETTER_QUEUE_NAME = CREATE_ACCOUNT_QUEUE_NAME + ".dlq";// DLQ - Dead Letter Queue

	@Bean
	Queue createAccountQueue() {
		logger.info("Queue Name::" + CREATE_ACCOUNT_QUEUE_NAME);
		return QueueBuilder.durable(CREATE_ACCOUNT_QUEUE_NAME).build();
	}

	@Bean
	FanoutExchange createAccountExchange() {
		logger.info("Exchange Name::" + CREATE_ACCOUNT_EXCHANGE_NAME);
		return new FanoutExchange(CREATE_ACCOUNT_EXCHANGE_NAME);
	}

	@Bean
	Binding binding() {
		return BindingBuilder.bind(createAccountQueue()).to(createAccountExchange());
	}

	@Bean
	FanoutExchange deadLetterExchange() {
		return new FanoutExchange(CREATE_ACCOUNT_DLX_NAME);
	}

	@Bean
	Queue deadLetterQueue() {
		return QueueBuilder.durable(CREATE_ACCOUNT_DEAD_LETTER_QUEUE_NAME).build();
	}

	@Bean
	Binding deadLetterBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange());
	}
}
