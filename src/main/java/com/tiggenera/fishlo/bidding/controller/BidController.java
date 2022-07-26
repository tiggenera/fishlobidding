package com.tiggenera.fishlo.bidding.controller;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiggenera.fishlo.bidding.generated.Bidding;
import com.tiggenera.fishlo.bidding.service.SendBid;

@RestController
public class BidController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SendBid bidSender;

	@GetMapping(value = "/bid")
	public Bidding sendBid() {
		double min = 1;
		double max = 100;
		Random rd = new Random();
		long startTime = System.currentTimeMillis();
		logger.info("The start time is {}", startTime);
		for (int i = 0; i < 20; i++) {
			Bidding bidding = new Bidding();
			bidding.setAuctionID(String.valueOf(i));
			bidding.setBroker("Mandar");
			bidding.setBrokerID("Mandy");
			bidding.setBuyerCompanyID("Abc");
			bidding.setCurrentTimeStamp(new Date().toString());
			bidding.setSellerID("XYZ" + i);
			Double nextDouble = rd.nextDouble();
			double randomValue = min + (max - min) * nextDouble;
			logger.info("The new random values is {}", randomValue);
			bidding.setBuyerPrice(randomValue);
			logger.info("The bidding price is {}", bidding.getBuyerPrice());
			bidSender.send(bidding);
		}
		long endTime = System.currentTimeMillis();
		logger.info("The total time is {}", (startTime - endTime));
		return null;
	}

}
