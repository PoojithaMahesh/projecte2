package com.jsp.onlinepharmacye2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacye2.entity.Admin;

@RestController
public class AdminController {

	@PostMapping
	public void signupAdmin(@RequestBody Admin admin) {
		
	}
	
}
