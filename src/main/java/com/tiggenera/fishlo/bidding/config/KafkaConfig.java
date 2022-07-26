package com.tiggenera.fishlo.bidding.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.tiggenera.fishlo.bidding.generated.Bidding;
import com.tiggenera.fishlo.bidding.serializer.BiddingDesrializer;
import com.tiggenera.fishlo.bidding.serializer.BiddingSerde;
import com.tiggenera.fishlo.bidding.serializer.BiddingSerializer;

@Configuration
@EnableKafka
@PropertySource(value = { "classpath:application.properties" })
public class KafkaConfig {
	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;
	private BiddingDesrializer deserial=new BiddingDesrializer();
	private BiddingSerializer serial=new BiddingSerializer();

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic topic() {
		return new NewTopic("bidding", 1, (short) 1);
	}

	@Bean
	public ProducerFactory<String, Bidding> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"com.tiggenera.fishlo.bidding.serializer.BiddingSerializer");
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, Bidding> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
	public Serde<Bidding> biddingSerde() {
		deserial = new BiddingDesrializer();
		serial = new BiddingSerializer();
		return new BiddingSerde(serial, deserial);
	}
}
