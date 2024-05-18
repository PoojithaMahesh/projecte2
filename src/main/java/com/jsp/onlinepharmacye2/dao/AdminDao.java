package com.jsp.onlinepharmacye2.dao;

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

}
