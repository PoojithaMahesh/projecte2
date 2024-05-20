package com.jsp.onlinepharmacye2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminNotFoundWithThisEmail extends RuntimeException {

	private String message;
}
