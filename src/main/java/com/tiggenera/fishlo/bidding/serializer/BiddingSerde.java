package com.tiggenera.fishlo.bidding.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes.WrapperSerde;
import org.apache.kafka.common.serialization.Serializer;

import com.tiggenera.fishlo.bidding.generated.Bidding;

public class BiddingSerde extends WrapperSerde<Bidding> {



	public void init() {

	}

	public BiddingSerde(Serializer<Bidding> serializer, Deserializer<Bidding> deserializer) {
		super(serializer, deserializer);
	}

	
}
