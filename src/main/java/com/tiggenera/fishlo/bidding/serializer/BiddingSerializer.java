package com.tiggenera.fishlo.bidding.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiggenera.fishlo.bidding.generated.Bidding;

public class BiddingSerializer implements Serializer<Bidding> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public byte[] serialize(String topic, Bidding data) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonObject = mapper.writeValueAsString(data);
			return jsonObject.getBytes();
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void close() {
	}
}
