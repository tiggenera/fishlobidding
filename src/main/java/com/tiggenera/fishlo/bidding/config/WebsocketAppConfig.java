package com.tiggenera.fishlo.bidding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketAppConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
		stompEndpointRegistry.addEndpoint("/websocket-chat").setAllowedOrigins("http://localhost:3000").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {

		registry.setApplicationDestinationPrefixes("/app").enableStompBrokerRelay("/topic").setRelayHost("localhost")
				.setRelayPort(61613).setClientLogin("guest").setClientPasscode("guest");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowCredentials(false).allowedOrigins("http://localhost:3000").allowedMethods("*");
			}
		};
	}

}
