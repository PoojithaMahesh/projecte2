package com.jsp.onlinepharmacye2.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.jsp.onlinepharmacye2.dao.AddressDao;
import com.jsp.onlinepharmacye2.dao.CustomerDao;
import com.jsp.onlinepharmacye2.dto.AddressDto;
import com.jsp.onlinepharmacye2.dto.BookingDto;
import com.jsp.onlinepharmacye2.dto.CustomerDto;
import com.jsp.onlinepharmacye2.entity.Address;
import com.jsp.onlinepharmacye2.entity.Bookings;
import com.jsp.onlinepharmacye2.entity.Customer;
import com.jsp.onlinepharmacye2.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacye2.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao dao;
	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper modelMapper;
	

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(int addressId, Customer customer) {
		Address dbAddress=addressDao.findAddress(addressId);
		if(dbAddress!=null) {
//			address is present
			List<Address> addresses=new ArrayList<Address>();
			addresses.add(dbAddress);
			customer.setAddresses(addresses);
			Customer dbCustomer=dao.saveCustomer(customer);
//			update address also
			dbAddress.setCustomer(dbCustomer);
			addressDao.updateAddress(addressId, dbAddress);
			ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
			structure.setMessage("Customer data saved successfully");
			structure.setHttpStatus(HttpStatus.CREATED.value());
			CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
			
			List<BookingDto> bookingDtos=new ArrayList<BookingDto>();
			for(Bookings bookings:dbCustomer.getBookings()) {
				BookingDto bookingDto=this.modelMapper.map(bookings, BookingDto.class);
				bookingDtos.add(bookingDto);
			}
			
			customerDto.setBookingDtos(bookingDtos);
			List<AddressDto>  addressDtos=new ArrayList<>();
			for(Address address:dbCustomer.getAddresses()) {
				AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
				addressDtos.add(addressDto);
			}
			customerDto.setAddressDtos(addressDtos);
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.CREATED);
			
			
			
		}else {
			throw new AddressIdNotFoundException("Sorry failed to save Customer");
		}
	}
	
}
