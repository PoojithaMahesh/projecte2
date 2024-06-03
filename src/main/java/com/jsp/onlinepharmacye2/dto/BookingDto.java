package com.jsp.onlinepharmacye2.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jsp.onlinepharmacye2.entity.Medicine;
import com.jsp.onlinepharmacye2.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Component
public class BookingDto {
	private int bookingId;
	private LocalDate oederDate;
	private int quantity;
	private String paymentMode;
	private LocalDate expectedDate;
	private BookingStatus bookingStatus;

	@ManyToMany
	private List<Medicine> medicines;
	
	@ManyToOne
	private CustomerDto customerDto;
}
