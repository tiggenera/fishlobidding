package com.tiggenera.fishlo.bidding.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.tiggenera.fishlo.bidding.service.SendBid;

@PropertySource(value = { "classpath:application.properties" })
public class AppConfig {
	/*
	 * private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 * 
	 * @Value("${spring.rabbitmq.queue}") String queueName;
	 * 
	 * @Value("${spring.rabbitmq.exchange}") String exchange;
	 * 
	 * @Value("${spring.rabbitmq.routingkey}") private String routingkey;
	 * 
	 * @Value("${amqp.username}") private String username;
	 * 
	 * @Value("${amqp.password}") private String password;
	 * 
	 * @Value("${amqp.host}") private String host;
	 * 
	 * @Bean Queue queue() { return new Queue(queueName, false); }
	 * 
	 * @Bean DirectExchange exchange() { return new DirectExchange(exchange); }
	 * 
	 * @Bean public MessageConverter jsonMessageConverter() { return new
	 * Jackson2JsonMessageConverter(); }
	 * 
	 * @Bean CachingConnectionFactory connectionFactory() { try {
	 * logger.debug("The username password and host is {} {} {}", username,
	 * password, host); CachingConnectionFactory cachingConnectionFactory = new
	 * CachingConnectionFactory(host);
	 * cachingConnectionFactory.setUsername(username);
	 * cachingConnectionFactory.setPassword(password); return
	 * cachingConnectionFactory; } catch (Exception e) { e.printStackTrace(); throw
	 * e; } }
	 * 
	 * @Bean public Queue myQueue() { return new Queue("myQueue", false); }
	 * 
	 * @Bean public RabbitTemplate rabbitTemplate(ConnectionFactory
	 * connectionFactory) { try { final RabbitTemplate rabbitTemplate = new
	 * RabbitTemplate(connectionFactory);
	 * rabbitTemplate.setMessageConverter(jsonMessageConverter()); return
	 * rabbitTemplate; } catch (Exception e) { e.printStackTrace(); throw e; } }
	 */

}