package com.jsp.onlinepharmacye2.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	private String message;
	private int httpStatus;
	private Object data;
	
}
