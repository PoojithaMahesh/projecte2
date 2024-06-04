package com.jsp.onlinepharmacye2.exception;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoookingIdNotFoundException extends RuntimeException {

	private String message;
	
}
