package com.jsp.onlinepharmacye2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacye2.dao.AdminDao;
import com.jsp.onlinepharmacye2.dto.AdminDto;
import com.jsp.onlinepharmacye2.entity.Admin;
import com.jsp.onlinepharmacye2.service.AdminService;
import com.jsp.onlinepharmacye2.util.ResponseStructure;

@RestController
public class AdminController {
//	it is meant for creating a rest api 
//	it will control the other classes
	
	@Autowired
	private AdminService service;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<AdminDto>> signupAdmin(@RequestBody Admin admin) {
		return service.signupAdmin(admin);
	}
	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<AdminDto>> fetchAdminById(@RequestParam int id){
		return service.fetchAdminById(id);
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(@RequestParam int id,@RequestBody Admin admin){
		return service.updateAdmin(id,admin);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdminById(@RequestParam int id){
		return service.deleteAdminById(id);
	}
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<List<AdminDto>>> fetchAllAdmins(){
		return service.fetchAllAdmin();
	}
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmin(@RequestParam String email,@RequestParam String password){
		return service.loginAdmin(email,password);
	}
	
}
