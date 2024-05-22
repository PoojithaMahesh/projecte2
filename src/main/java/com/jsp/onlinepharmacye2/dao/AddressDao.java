package com.jsp.onlinepharmacye2.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacye2.entity.Address;
import com.jsp.onlinepharmacye2.repo.AddressRepo;

@Repository
public class AddressDao {
@Autowired
private AddressRepo repo;

public Address saveAddress(Address address) {
	
	return repo.save(address);
}

public Address updateAddress(int addressId, Address address) {
	Optional<Address> optional=repo.findById(addressId);
	if(optional.isPresent()) {
//		id is present
		address.setAddressId(addressId);
		return repo.save(address);
	}
	return null;
}

public Address findAddress(int addressId) {
	Optional<Address> optional=repo.findById(addressId);
	if(optional.isPresent()) {
//		id is present
		
		return optional.get();
	}
	return null;
}

public Address deleteAddressById(int addressId) {
	Optional<Address> optional=repo.findById(addressId);
	if(optional.isPresent()) {
//		id is present
		repo.deleteById(addressId);
		return optional.get();
	}
	return null;
}
}
