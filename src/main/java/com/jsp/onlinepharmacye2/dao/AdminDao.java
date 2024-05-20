package com.jsp.onlinepharmacye2.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacye2.entity.Admin;
import com.jsp.onlinepharmacye2.repo.AdminRepo;

@Repository
public class AdminDao {
//	dao is meant for your database logic 
//	it will perform crud operation and it will return the data
	
    @Autowired
    private AdminRepo repo;
    
	public Admin saveAdmin(Admin admin) {
		return repo.save(admin);
	}

	public Admin findAdminById(int id) {
		Optional<Admin> optional=repo.findById(id);
		
		if(optional.isPresent()) {
//			id is present
			return optional.get();	
		}
		return null;
	}

	public Admin updateAdmin(int id, Admin admin) {
//		to update the admin first i want to check whether that id is present or not
//		if it is present then i will update the data
//		if it is not present then i will return null;
		Optional<Admin> optional=repo.findById(id);
		if(optional.isPresent()) {
//			id is present
			admin.setAdminId(id);
			return repo.save(admin);
			
		}
		return null;
	}

	public Admin deleteAdminById(int id) {
		Optional<Admin> optional=repo.findById(id);
//		optional admin is present
		if(optional.isPresent()) {
//			id is present then i can delete the data
			repo.deleteById(id);
			return optional.get();
		}
		return null;
	}

	public List<Admin> getAllAdmins() {
		return repo.findAll();
	}

	public Admin findAdminByEmail(String email) {
		Optional<Admin> optional=repo.findByEmail(email);
		if(optional.isPresent()) {
//			admin is present
			return optional.get();
		}
		return null;
	}

}
