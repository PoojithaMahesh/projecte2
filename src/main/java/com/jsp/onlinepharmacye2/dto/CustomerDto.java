package com.jsp.onlinepharmacye2.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CustomerDto {
	private int customerid;
	private String customerName;
	
	@OneToMany
	private List<AddressDto> addressDtos;
	
	@OneToMany
	private List<BookingDto> bookingDtos;
}
