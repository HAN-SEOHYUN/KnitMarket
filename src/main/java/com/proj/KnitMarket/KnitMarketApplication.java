package com.proj.KnitMarket;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KnitMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnitMarketApplication.class, args);
	}
}
