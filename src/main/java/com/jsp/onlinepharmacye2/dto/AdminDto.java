package com.jsp.onlinepharmacye2.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AdminDto {
	private int adminId;
	private String adminName;
	private String adminAddress;
}
