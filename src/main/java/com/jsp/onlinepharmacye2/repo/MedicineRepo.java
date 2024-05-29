package com.jsp.onlinepharmacye2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.onlinepharmacye2.entity.Medicine;

public interface MedicineRepo extends JpaRepository<Medicine, Integer>{
    @Query("Select m from Medicine m where m.medicineName")
	public Optional<Medicine> findByName(String medicineName);

}
