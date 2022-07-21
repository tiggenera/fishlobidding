package com.tiggenera.fishlo.bidding.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.tiggenera.fishlo.bidding.generated.Bidding;

@Service
public class SendBid {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RabbitTemplate template;

	public Bidding send(Bidding bid) {
		logger.debug("the bid message is {} the template is {}", bid, template);
		template.convertAndSend("myQueue", bid);
		return bid;
	}

	/*
	 * @RabbitListener(queues = "myQueue") public void listen(Message in) {
	 * System.out.println("Received the message from myqueueis "+in);
	 * logger.debug("Received Message : {}" + in); }
	 */
}
