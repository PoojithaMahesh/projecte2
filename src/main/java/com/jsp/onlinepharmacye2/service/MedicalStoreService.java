package com.jsp.onlinepharmacye2.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacye2.dao.AddressDao;
import com.jsp.onlinepharmacye2.dao.AdminDao;
import com.jsp.onlinepharmacye2.dao.MedicalStoreDao;
import com.jsp.onlinepharmacye2.dto.AddressDto;
import com.jsp.onlinepharmacye2.dto.AdminDto;
import com.jsp.onlinepharmacye2.dto.MedicalStoreDto;
import com.jsp.onlinepharmacye2.entity.Address;
import com.jsp.onlinepharmacye2.entity.Admin;
import com.jsp.onlinepharmacye2.entity.MedicalStore;
import com.jsp.onlinepharmacye2.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacye2.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacye2.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacye2.util.ResponseStructure;

@Service
public class MedicalStoreService {

	@Autowired
	private MedicalStoreDao dao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper mapper;
	

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> establishmedicalStore(int adminId, int addressId,
			MedicalStore medicalStore) {
		Admin dbAdmin=adminDao.findAdminById(adminId);
		
		if(dbAdmin!=null) {
//			admin is a valid admin
			medicalStore.setAdmin(dbAdmin);
//			i have set the admin to the medicalstore
//			i want to check whether that address is present or not
			Address dbAddress=addressDao.findAddress(addressId);
			if(dbAddress!=null) {
//				that address ius present then i can establish the medicalstore
				medicalStore.setAddress(dbAddress);
			
				MedicalStore dbMedicalStore=dao.saveMedicalStore(medicalStore);
//				extra code added on 24/05/2024 next 2 lines
				
				dbAddress.setMedicalStore(medicalStore);
				addressDao.updateAddress(addressId, dbAddress);
				
				MedicalStoreDto dto=new MedicalStoreDto();
				dto.setStoreId(dbMedicalStore.getStoreId());
				dto.setStoreName(dbMedicalStore.getStoreName());
				dto.setManagerName(dbMedicalStore.getManagerName());
				dto.setPhone(dbMedicalStore.getPhone());
				
				Address address=dbMedicalStore.getAddress();
				
				AddressDto addressDto=new AddressDto();
				addressDto.setAddressId(address.getAddressId());
				addressDto.setStreetName(address.getStreetName());
				addressDto.setState(address.getState());
				addressDto.setPincode(address.getPincode());
				addressDto.setCity(address.getCity());
				
				dto.setAddressDto(addressDto);
				
				Admin admin=dbMedicalStore.getAdmin();
				
				AdminDto adminDto=new AdminDto();
				adminDto.setAdminAddress(admin.getAdminAddress());
				adminDto.setAdminId(admin.getAdminId());
				adminDto.setAdminName(admin.getAdminName());
		
				dto.setAdminDto(adminDto);
				
				ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<MedicalStoreDto>();
				structure.setMessage("MedicalStore established successfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.CREATED);
			}else {
//				address id is nit present
				throw new AddressIdNotFoundException("Sorry failed to establish the medicalStore");
			}
		}else {
//			id is not present and he is not a valid admin 
			throw new AdminIdNotFoundException("Sorry failed to establish the medicalstore");
		}
		
		
		
		
	}


	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId,
			MedicalStore medicalStore) {
		MedicalStore dbMedicalStore=dao.updateMedicalStore(storeId,medicalStore);
		if(dbMedicalStore!=null) {
//			id is present and data updated successfully
			
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
		
			AddressDto addressDto=this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto=this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class);
			
			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);
		
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore data updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(medicalStoreDto);
			
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.OK);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to update the MedicalStore");
			
		}
	}


	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStore(int storeId) {
		MedicalStore dbMedicalStore=dao.findMedicalStore(storeId);
		if(dbMedicalStore!=null) {
//			id is present and data updated successfully
			
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
		
			AddressDto addressDto=this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto=this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class);
			
			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);
		
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore data fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(medicalStoreDto);
			
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to FETCH the MedicalStore");
			
		}
	}


	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStore(int storeId) {
		MedicalStore dbMedicalStore=dao.deleteMedicalStore(storeId);
		if(dbMedicalStore!=null) {
//			id is present and data updated successfully
			
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicalStore, MedicalStoreDto.class);
		
			AddressDto addressDto=this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto=this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class);
			
			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);
		
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore data deleted successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(medicalStoreDto);
			
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FORBIDDEN);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to delete the MedicalStore");
			
		}
	}
}
