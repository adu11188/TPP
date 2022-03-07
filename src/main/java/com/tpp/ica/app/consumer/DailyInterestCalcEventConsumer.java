package com.tpp.ica.app.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.client.Channel;
import com.tpp.ica.app.exception.InValidJsonInputMessage;
import com.tpp.ica.app.rabbitmq.configuration.RabbitMQDailyInterestCalcConfiguration;
import com.tpp.ica.app.sevice.InterestCalcService;
/**
 * 
 * @author adu11
 *
 */
@Component
public class DailyInterestCalcEventConsumer {

	private Logger logger = LoggerFactory.getLogger(DailyInterestCalcEventConsumer.class);

	@Autowired
	private InterestCalcService interestCalcService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public static final int UNPROCESSED_MESAAGE_RETRY_COUNT = 3;

	@RabbitListener(queues = "#{dailyInterestCalcQueue.getName()}")
	public void consumeMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
		logger.info("Event message received");
		try {
			interestCalcService.processAccountEndOfDayBalances(message);
			
		} catch (JsonProcessingException | InValidJsonInputMessage e) {
			logger.error("Event message could not be processed ", e);
			/**
			 * Push message to Dead letter Queue since data received is malformed
			 */
			pushMessageToDeadLetterQueue(message);
		} catch (Exception e) {
			logger.error("Event message could not be processed ", e);
			handleUnprocessedMessage(message,channel,tag);
		}

	}

	private void pushMessageToDeadLetterQueue(String message) {
		rabbitTemplate.convertAndSend(RabbitMQDailyInterestCalcConfiguration.DAILY_INTEREST_CALC_DLX_NAME,"", message);
	}
	
	private void handleUnprocessedMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
		int retryCount = UNPROCESSED_MESAAGE_RETRY_COUNT;
		try {
			rejectMessage(channel, tag);
		} catch (IOException e) {
			logger.error("I/O failure while rejecting", e);

			while (retryCount-- > 0) {
				try {
					rejectMessage(channel, tag);
					return;
				} catch (IOException e1) {
					logger.error("I/O failure while retrying", e);
				}
			}

			pushMessageToDeadLetterQueue(message);

		}
	}

	private void rejectMessage(Channel channel, long tag) throws IOException {
		channel.basicReject(tag, true);
	}
}
