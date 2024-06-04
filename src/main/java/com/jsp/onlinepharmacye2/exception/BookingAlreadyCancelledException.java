package com.jsp.onlinepharmacye2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingAlreadyCancelledException extends RuntimeException {

	private String message;
}
