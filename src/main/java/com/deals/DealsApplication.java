package com.deals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
		, scanBasePackages = "com.deals")
public class DealsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealsApplication.class, args);
	}

}
