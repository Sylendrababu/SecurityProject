package com.tgi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;


@SpringBootApplication
@EntityScan("com.tgi.entity")
@EnableJpaRepositories("com.tgi.repository")
@ComponentScan(basePackages = "com.tgi")
@OpenAPIDefinition

public class SecurityProjectApplication {

	static Logger logger = LoggerFactory.getLogger(SecurityProjectApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityProjectApplication.class, args);
		
		logger.info("Main class is running");
		
	}

}
