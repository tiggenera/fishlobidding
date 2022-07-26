package com.tiggenera.fishlo.bidding.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiggenera.fishlo.bidding.generated.Bidding;

@Service
public class SendBid {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private KafkaTemplate template;

	public Bidding send(Bidding bid) {
		logger.debug("the bid message is {} the template is {}", bid, template);
		ObjectMapper mapper = new ObjectMapper();
		template.send("bidding",bid.getCurrentTimeStamp().toString(),bid);
		return bid;
	}
}
