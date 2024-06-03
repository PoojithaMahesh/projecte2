package com.jsp.onlinepharmacye2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinepharmacye2.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
