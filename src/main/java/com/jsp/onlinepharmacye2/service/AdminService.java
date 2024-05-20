package com.jsp.onlinepharmacye2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacye2.dao.AdminDao;
import com.jsp.onlinepharmacye2.dto.AdminDto;
import com.jsp.onlinepharmacye2.entity.Admin;
import com.jsp.onlinepharmacye2.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacye2.exception.AdminNotFoundWithThisEmail;
import com.jsp.onlinepharmacye2.exception.AdminPasswordNotValidException;
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

	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(int id, Admin admin) {
	Admin dbAdmin=dao.updateAdmin(id,admin);
	if(dbAdmin!=null) {
		
		dto.setAdminId(dbAdmin.getAdminId());
		dto.setAdminName(dbAdmin.getAdminName());
		dto.setAdminAddress(dbAdmin.getAdminAddress());
		
//		id is present and the data updated successfully
		ResponseStructure<AdminDto> structure=new ResponseStructure<>();
		structure.setMessage("Data Updated successfully");
		structure.setHttpStatus(HttpStatus.OK.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
	}else {
//		id is not present
//		raise an exception
		throw new AdminIdNotFoundException("Sorry failed to update the data");
	}
	
	
	
		
	}

	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdminById(int id) {
		Admin dbAdmin=dao.deleteAdminById(id);
		if(dbAdmin!=null) {
//			id is present
			dto.setAdminId(dbAdmin.getAdminId());
			dto.setAdminName(dbAdmin.getAdminName());
			dto.setAdminAddress(dbAdmin.getAdminAddress());
			
			ResponseStructure<AdminDto> structure=new ResponseStructure<>();
			structure.setMessage("Admin Data deleted Successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(dto);
		    return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FORBIDDEN);
		}else {
			throw new AdminIdNotFoundException("Sorry failed to delete the data");
		}
	}

	public ResponseEntity<ResponseStructure<List<AdminDto>>> fetchAllAdmin() {
		List<Admin> list=dao.getAllAdmins();
		
		List<AdminDto> adminDtos=new ArrayList<>();
		
		for(Admin admin:list) {
			AdminDto adminDto=new AdminDto();

			adminDto.setAdminId(admin.getAdminId());
			adminDto.setAdminName(admin.getAdminName());
			adminDto.setAdminAddress(admin.getAdminAddress());
			adminDtos.add(adminDto);
		}
		
		ResponseStructure<List<AdminDto>> structure=new ResponseStructure<>();
		structure.setMessage("Admin Data fetched Successfully");
		structure.setHttpStatus(HttpStatus.FOUND.value());
		structure.setData(adminDtos);
	    return new ResponseEntity<ResponseStructure<List<AdminDto>>>(structure,HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmin(String email, String password) {
		Admin dbAdmin=dao.findAdminByEmail(email);
		if(dbAdmin!=null) {
//			admin is present then i can check with the password
			if(password.equals(dbAdmin.getAdminPassword())) {
//				pasword also matches then login success
				dto.setAdminId(dbAdmin.getAdminId());
				dto.setAdminName(dbAdmin.getAdminName());
				dto.setAdminAddress(dbAdmin.getAdminAddress());
				
				ResponseStructure<AdminDto> structure=new ResponseStructure<>();
				structure.setMessage("Admin LOGGEDIN Successfully");
				structure.setHttpStatus(HttpStatus.FOUND.value());
				structure.setData(dto);
			    return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
			}else {
//				login failure invalid password
				throw new AdminPasswordNotValidException("Sorry failed to login");
			}
			
		}else {
//			admin is not present with this email
//			invalid Email
			throw new AdminNotFoundWithThisEmail("Sorry failed to login");
		}
	}

	
}
