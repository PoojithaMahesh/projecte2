package com.jsp.onlinepharmacye2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacye2.entity.Bookings;
import com.jsp.onlinepharmacye2.repo.BookingRepo;

@Repository
public class BookingDao {
@Autowired
private BookingRepo repo;

public Bookings saveBookings(Bookings bookings) {
	return repo.save(bookings);
}
}
