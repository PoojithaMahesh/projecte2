package com.jsp.onlinepharmacye2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacye2.dto.CustomerDto;
import com.jsp.onlinepharmacye2.entity.Customer;
import com.jsp.onlinepharmacye2.service.CustomerService;
import com.jsp.onlinepharmacye2.util.ResponseStructure;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> signUpCustomer(@RequestParam int addressId ,
			@RequestBody Customer customer){
		return service.saveCustomer(addressId,customer);
	}
}
