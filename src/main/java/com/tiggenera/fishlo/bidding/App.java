package com.tiggenera.fishlo.bidding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.tiggenera.fishlo.bidding.config", "com.tiggenera.fishlo.bidding.controller",
		"com.tiggenera.fishloprofile.app", "com.tiggenera.fishlo.bidding.dao", "com.tiggenera.fishlo.bidding.service" })
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class, GsonAutoConfiguration.class ,SecurityAutoConfiguration.class})
public class App extends SpringBootServletInitializer {
	public App() {
		super();
		setRegisterErrorPageFilter(false);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	

}