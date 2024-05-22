package com.jsp.onlinepharmacye2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacye2.dao.AddressDao;
import com.jsp.onlinepharmacye2.entity.Address;
import com.jsp.onlinepharmacye2.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacye2.util.ResponseStructure;

@Service
public class AddrressService {

	@Autowired
	private AddressDao dao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {
		Address dbAddress=dao.saveAddress(address);
		ResponseStructure<Address> structure=new ResponseStructure<Address>();
		structure.setMessage("Address saved successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(int addressId, Address address) {
		Address dbAddress=dao.updateAddress(addressId,address);
		if(dbAddress!=null){
//			id is present and the data updated successfully
			ResponseStructure<Address> structure=new ResponseStructure<Address>();
			structure.setMessage("Address UPDATED successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.OK);
			
		}else {
//			id is not present raise AddressIdNotFoundException
			
			throw new AddressIdNotFoundException("Sorry failed to update the Address");
		}
	}

	public ResponseEntity<ResponseStructure<Address>> findAddress(int addressId) {
		Address dbAddress=dao.findAddress(addressId);
		if(dbAddress!=null){
//			id is present and the data updated successfully
			ResponseStructure<Address> structure=new ResponseStructure<Address>();
			structure.setMessage("Address fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FOUND);
			
		}else {
//			id is not present raise AddressIdNotFoundException
			
			throw new AddressIdNotFoundException("Sorry failed to fetch the Address");
		}
	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddress(int addressId) {
	Address dbAddress=dao.deleteAddressById(addressId);
	if(dbAddress!=null){
//		id is present and the data updated successfully
		ResponseStructure<Address> structure=new ResponseStructure<Address>();
		structure.setMessage("Address deleted successfully");
		structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
		structure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FORBIDDEN);
		
	}else {
//		id is not present raise AddressIdNotFoundException
		
		throw new AddressIdNotFoundException("Sorry failed to delete the Address");
	}
	}
}
