package com.tiggenera.fishlo.bidding.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = { "classpath:application.properties" })
@EnableAutoConfiguration
@EnableRabbit
public class AppConfig {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${spring.rabbitmq.queue}")
	String queueName;

	@Value("${spring.rabbitmq.exchange}")
	String exchange;

	@Value("${amqp.username}")
	private String username;

	@Value("${amqp.password}")
	private String password;

	@Value("${amqp.host}")
	private String host;

	@Autowired
	private Receiver receiver;

	@Bean
	Declarables queueDeclarable(Queue queue,DirectExchange exchange) {
		logger.debug("The declarable is being called");
		BindingBuilder.bind(queue).to(exchange);
		return new Declarables(queue, exchange);
	}

	Queue queue() {
		return new Queue("auction", true);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	CachingConnectionFactory connectionFactory() {
		try {
			logger.debug("The username password and host is {} {} {}", username, password, host);
			CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
			cachingConnectionFactory.setUsername(username);
			cachingConnectionFactory.setPassword(password);
			return cachingConnectionFactory;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Bean
	public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory, Queue queue) {
		logger.debug("The message container is initialized");
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueues(queue);
		container.setExposeListenerChannel(true);
		container.setMaxConcurrentConsumers(100);
		container.setConcurrentConsumers(100);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMessageListener(listenerAdapter());
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter() {
		logger.debug("==================================Initializing the receiver=====================================");
		return new MessageListenerAdapter(receiver);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,Queue queue) {
		try {
			final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
			rabbitTemplate.setMessageConverter(jsonMessageConverter());
			return rabbitTemplate;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}