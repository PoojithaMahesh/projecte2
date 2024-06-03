package com.jsp.onlinepharmacye2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinepharmacye2.entity.Bookings;

public interface BookingRepo extends JpaRepository<Bookings, Integer>{

}
