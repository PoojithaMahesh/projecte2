package com.jsp.onlinepharmacye2.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bookings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private LocalDate oederDate;
	private int quantity;
	private String paymentMode;
	private LocalDate expectedDate;

	@ManyToMany
	private List<Medicine> medicines;

	@ManyToOne
	@JoinColumn
	private Customer customer;
}
