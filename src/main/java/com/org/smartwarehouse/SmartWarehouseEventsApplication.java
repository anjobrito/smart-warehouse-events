package com.org.smartwarehouse;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SmartWarehouseEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartWarehouseEventsApplication.class, args);
	}

}
