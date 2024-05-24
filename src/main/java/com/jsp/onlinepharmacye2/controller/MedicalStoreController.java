package com.jsp.onlinepharmacye2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacye2.dto.MedicalStoreDto;
import com.jsp.onlinepharmacye2.entity.MedicalStore;
import com.jsp.onlinepharmacye2.service.MedicalStoreService;
import com.jsp.onlinepharmacye2.util.ResponseStructure;

@RestController
@RequestMapping("/store")
public class MedicalStoreController {

	@Autowired
	private MedicalStoreService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> establishTheMedicalStore(@RequestParam int adminId,
			@RequestParam int addressId,@RequestBody MedicalStore medicalStore){
		return service.establishmedicalStore(adminId,addressId,medicalStore);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(@RequestParam int storeId,@RequestBody MedicalStore medicalStore){
		return service.updateMedicalStore(storeId,medicalStore);
		
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStore(@RequestParam int storeId){
		return service.findMedicalStore(storeId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStore(@RequestParam int storeId){
		return service.deleteMedicalStore(storeId);
	}
}
