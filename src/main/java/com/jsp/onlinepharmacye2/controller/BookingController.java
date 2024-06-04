package com.jsp.onlinepharmacye2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacye2.dto.BookingDto;
import com.jsp.onlinepharmacye2.entity.Bookings;
import com.jsp.onlinepharmacye2.service.BookingService;
import com.jsp.onlinepharmacye2.util.ResponseStructure;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<BookingDto>> addBooking(@RequestParam int customerId,
			@RequestParam int medicineId,@RequestBody Bookings bookings){
		return service.addBooking(customerId,medicineId,bookings);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<BookingDto>> cancelBooking(@RequestParam int bookingId){
		return service.cancelBooking(bookingId);
	}
	
}
