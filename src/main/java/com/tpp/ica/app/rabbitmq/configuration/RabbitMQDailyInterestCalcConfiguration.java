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
public class RabbitMQDailyInterestCalcConfiguration {

	private Logger logger = LoggerFactory.getLogger(RabbitMQDailyInterestCalcConfiguration.class);

	public static final String DAILY_INTEREST_CALC_EXCHANGE_NAME = "daily-interest-calc-exchange";
	public static final String DAILY_INTEREST_CALC_DLX_NAME = "daily-interest-calc-dlx"; // DLX - Dead Letter Exchange

	public static final String DAILY_INTEREST_CALC_QUEUE_NAME = "daily-interest-calc";
	public static final String DAILY_INTEREST_CALC_DEAD_LETTER_QUEUE_NAME = DAILY_INTEREST_CALC_QUEUE_NAME + ".dlq";// DLQ - Dead Letter Queue

	@Bean
	Queue dailyInterestCalcQueue() {
		logger.info("Queue Name::" + DAILY_INTEREST_CALC_QUEUE_NAME);
		return QueueBuilder.durable(DAILY_INTEREST_CALC_QUEUE_NAME).build();
	}

	@Bean
	FanoutExchange dailyInterestCalcExchange() {
		logger.info("Exchange Name::" + DAILY_INTEREST_CALC_EXCHANGE_NAME);
		return new FanoutExchange(DAILY_INTEREST_CALC_EXCHANGE_NAME);
	}

	@Bean
	Binding dailyInterestCalcBinding() {
		return BindingBuilder.bind(dailyInterestCalcQueue()).to(dailyInterestCalcExchange());
	}

	@Bean
	FanoutExchange dailyInterestCalcDeadLetterExchange() {
		return new FanoutExchange(DAILY_INTEREST_CALC_DLX_NAME);
	}

	@Bean
	Queue dailyInterestCalcDeadLetterQueue() {
		return QueueBuilder.durable(DAILY_INTEREST_CALC_DEAD_LETTER_QUEUE_NAME).build();
	}

	@Bean
	Binding dailyInterestCalcDeadLetterBinding() {
		return BindingBuilder.bind(dailyInterestCalcDeadLetterQueue()).to(dailyInterestCalcDeadLetterExchange());
	}
}
