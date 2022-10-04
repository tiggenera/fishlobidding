package com.tiggenera.fishlo.bidding.config;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiggenera.fishlo.bidding.generated.Bidding;

@Component
public class Receiver {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ObjectMapper mapper = new ObjectMapper();
	private ConcurrentHashMap<String, Double> map = new ConcurrentHashMap<>();
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private SimpMessagingTemplate messageTemplate;

	private static final Object obj = new Object();
	
	@RabbitListener(queues = { "auction" })
	public void receiveMessage(String message) {
		try {
			logger.debug("received message{}",message);
			
			Bidding bidding = mapper.readValue(message, Bidding.class);
			String auctionID = bidding.getAuctionID();
			synchronized (obj) {
				if (map.containsKey(auctionID)) {
					Double doubleValue = map.get(auctionID);
					logger.debug("The auction value is {}",doubleValue);
					if (Double.compare(bidding.getBuyerPrice(), doubleValue)>0) {
						map.put(auctionID, bidding.getBuyerPrice());
						sendMessage(bidding);
					} 
				}else {
					map.put(auctionID, bidding.getBuyerPrice());
					sendMessage(bidding);
				}
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendMessage(Bidding bidding) {
		try {
			logger.debug("The auction bidding is {}",bidding);
			messageTemplate.convertAndSend("/topic/user",mapper.writeValueAsString(bidding));
		} catch (JsonProcessingException | AmqpException e) {
			e.printStackTrace();
		}
	}

}
