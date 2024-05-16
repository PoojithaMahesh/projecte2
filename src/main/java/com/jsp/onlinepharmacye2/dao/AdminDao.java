package com.jsp.onlinepharmacye2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacye2.entity.Admin;
import com.jsp.onlinepharmacye2.repo.AdminRepo;

@Repository
public class AdminDao {
    @Autowired
    private AdminRepo repo;
	public void saveAdmin(Admin admin) {
		repo.save(admin);
	}

}
