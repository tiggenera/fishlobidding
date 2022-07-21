package com.tiggenera.fishlo.bidding.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiggenera.fishlo.bidding.generated.Bidding;
import com.tiggenera.fishlo.bidding.service.SendBid;

@RestController
public class BidController {

	@Autowired
	private SendBid bidSender;

	@GetMapping(value = "/bid")
	public Bidding sendBid() {
		long startTime = System.currentTimeMillis();
		for(int i=0;i<500000;i++) {
		Bidding bidding = new Bidding();
		bidding.setAuctionID(String.valueOf(i));
		bidding.setBroker("Mandar");
		bidding.setBrokerID("Mandy");
		bidding.setBuyerCompanyID("Abc");
		bidding.setCurrentTimeStamp(new Date().toString());
		bidding.setSellerID("XYZ");
		bidding.setBuyerPrice(new Double(i));
		bidSender.send(bidding);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("The total time is {}"+(startTime-endTime));
		return null;
	}

}
