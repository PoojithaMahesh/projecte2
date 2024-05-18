package com.jsp.onlinepharmacye2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacye2.dao.AdminDao;
import com.jsp.onlinepharmacye2.dto.AdminDto;
import com.jsp.onlinepharmacye2.entity.Admin;
import com.jsp.onlinepharmacye2.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacye2.util.ResponseStructure;

@Service
public class AdminService {

//	it will take the data from the controller then it will pass that to the dao
//	it will receive the data returned by the dao
//	it will add some extra things to the Response 
//	it will return the ResponseStructure of Admin
	@Autowired
	private AdminDao dao;
	
	@Autowired
	private AdminDto dto;
	
	public ResponseEntity<ResponseStructure<AdminDto>> signupAdmin(Admin admin) {
		Admin dbAdmin=dao.saveAdmin(admin);
//		dbADMIN IS THE SAVED ADMIN
		
		dto.setAdminId(dbAdmin.getAdminId());
		dto.setAdminName(dbAdmin.getAdminName());
		dto.setAdminAddress(dbAdmin.getAdminAddress());
		
		ResponseStructure<AdminDto> structure=new ResponseStructure<>();
		structure.setMessage("Admin SignedUp Successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
	    return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AdminDto>> fetchAdminById(int id) {
		Admin dbAdmin=dao.findAdminById(id);
		if(dbAdmin!=null) {
//			id is present
			dto.setAdminId(dbAdmin.getAdminId());
			dto.setAdminName(dbAdmin.getAdminName());
			dto.setAdminAddress(dbAdmin.getAdminAddress());
			
			ResponseStructure<AdminDto> structure=new ResponseStructure<>();
			structure.setMessage("Admin Data fetched Successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
		    return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}else {
//			id is not present
			throw new AdminIdNotFoundException("Sorry failed to fetch the data");
		}
	}

	
}