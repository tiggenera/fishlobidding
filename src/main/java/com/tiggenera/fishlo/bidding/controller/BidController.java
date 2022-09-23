package com.tiggenera.fishlo.bidding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.tiggenera.fishlo.bidding.generated.Bidding;
import com.tiggenera.fishlo.bidding.service.SendBid;

@RestController
public class BidController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SendBid bidSender;

    @MessageMapping("/user-all")
    @SendTo("/topic/user")
	public Bidding sendBid(@Payload Bidding bidding) {
    	bidSender.send(bidding);
		return bidding;
	}

}
