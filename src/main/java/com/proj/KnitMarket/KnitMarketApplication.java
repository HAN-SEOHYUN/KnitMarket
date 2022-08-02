package com.proj.KnitMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class KnitMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnitMarketApplication.class, args);
	}

}
