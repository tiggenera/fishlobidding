package com.tiggenera.fishlo.bidding.serializer;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiggenera.fishlo.bidding.generated.Bidding;

public class BiddingDesrializer implements Deserializer<Bidding> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public Bidding deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		Bidding bidding;
		try {
			bidding = mapper.readValue(data, Bidding.class);
			return bidding;
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

	}

	@Override
	public void close() {
	}
}
