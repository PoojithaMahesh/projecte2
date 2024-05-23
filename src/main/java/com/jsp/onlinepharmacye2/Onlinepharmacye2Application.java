package com.jsp.onlinepharmacye2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Onlinepharmacye2Application {

	public static void main(String[] args) {
		SpringApplication.run(Onlinepharmacye2Application.class, args);
	}
    @Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
