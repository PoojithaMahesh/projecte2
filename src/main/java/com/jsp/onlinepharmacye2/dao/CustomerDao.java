package com.jsp.onlinepharmacye2.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacye2.entity.Customer;
import com.jsp.onlinepharmacye2.repo.CustomerRepo;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo repo;
	
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}

	public Customer findCustomerById(int customerId) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Customer updateCustomer(int customerId, Customer dbCustomer) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
			dbCustomer.setCustomerid(customerId);
			dbCustomer.setAddresses(optional.get().getAddresses());
			dbCustomer.setBookings(optional.get().getBookings());
			
			return repo.save(dbCustomer);
		}
		return null;
		
	}
}
