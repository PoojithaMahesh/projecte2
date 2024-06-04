package com.jsp.onlinepharmacye2.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacye2.dao.BookingDao;
import com.jsp.onlinepharmacye2.dao.CustomerDao;
import com.jsp.onlinepharmacye2.dao.MedicineDao;
import com.jsp.onlinepharmacye2.dto.BookingDto;
import com.jsp.onlinepharmacye2.dto.CustomerDto;
import com.jsp.onlinepharmacye2.entity.Bookings;
import com.jsp.onlinepharmacye2.entity.Customer;
import com.jsp.onlinepharmacye2.entity.Medicine;
import com.jsp.onlinepharmacye2.enums.BookingStatus;
import com.jsp.onlinepharmacye2.exception.BookingAlreadyCancelledException;
import com.jsp.onlinepharmacye2.exception.BookingAlreadyDeliveredException;
import com.jsp.onlinepharmacye2.exception.BookingCantCancel;
import com.jsp.onlinepharmacye2.exception.BoookingIdNotFoundException;
import com.jsp.onlinepharmacye2.exception.CustomerIdNotFoundException;
import com.jsp.onlinepharmacye2.exception.MedicineIdNotFoundException;
import com.jsp.onlinepharmacye2.util.ResponseStructure;

@Service
public class BookingService {

	@Autowired
	private BookingDao dao;
	
	@Autowired
	private MedicineDao medicineDao;

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ModelMapper mapper;
	
	public ResponseEntity<ResponseStructure<BookingDto>> addBooking(int customerId, int medicineId, Bookings bookings) {
		
		Customer dbCustomer=customerDao.findCustomerById(customerId);
	    if(dbCustomer!=null) {
	    	bookings.setBookingStatus(BookingStatus.ACTIVE);
	    	bookings.setCustomer(dbCustomer);
//	    	customer is having a bookings
//	    	booking is also having the customer
//	    	update customer details
	    	Medicine dbMedicine=medicineDao.findMedicine(medicineId);
	    	List<Medicine> medicines=new ArrayList<Medicine>();
	    	if(dbMedicine!=null) {
	    		medicines.add(dbMedicine);
	    		bookings.setMedicines(medicines);
	    		Bookings dbBookings=dao.saveBookings(bookings);
//	    		its time to update customer details
	    		List<Bookings> bookingsslist=dbCustomer.getBookings();
//	    	im taking the customer old bookings also
//	    		now im goiong to add new booking along with the old bookings
	    		bookingsslist.add(dbBookings);
//	    		now inside this bookingss we are having old and new bookings
//	    		again im going to reset the bookings
	    		dbCustomer.setBookings(bookingsslist);
	    		customerDao.updateCustomer(customerId,dbCustomer);
	    		
	    		
	    		ResponseStructure<BookingDto> structure=new ResponseStructure<>();
	    		structure.setMessage("Booking successfully");
	    		
	    		structure.setHttpStatus(HttpStatus.CREATED.value());
	    		
	    		BookingDto bookingDto=this.mapper.map(dbBookings, BookingDto.class);
	    		bookingDto.setMedicines(medicines);
	    		bookingDto.setCustomerDto(this.mapper.map(dbBookings.getCustomer(), CustomerDto.class));
	    		
	    		
	    		structure.setData(bookingDto);
	    		
	    		return new  ResponseEntity<ResponseStructure<BookingDto>>(structure,HttpStatus.CREATED);
	    	}else {
//	    		medicine id is not found
	    		throw new MedicineIdNotFoundException("Sorry failed to add bookings");
	    	}
	    }else {
	    	throw new CustomerIdNotFoundException("sorry faiiled to add bookings");
	    }
	}

	public ResponseEntity<ResponseStructure<BookingDto>> cancelBooking(int bookingId) {
		Bookings dbBookings=dao.findBooking(bookingId);
		if(dbBookings!=null) {
//			booking id is present
//			cantcancelleddate:
			
			LocalDate cantCancelledDate=dbBookings.getExpectedDate().minusDays(2);
//			now you want to check if it is cancelled::means dbBookings 
//			please write this much ill take after 10 mins....
			if(dbBookings.getBookingStatus().equals(BookingStatus.CANCELLED)) {
				throw new BookingAlreadyCancelledException("Sorry failed to cancel the booking");
			}else if(dbBookings.getBookingStatus().equals(BookingStatus.DELIVERED)) {
				throw new BookingAlreadyDeliveredException("Sorry failed to cancel the booking");
			}else if(LocalDate.now().equals(cantCancelledDate)||LocalDate.now().isAfter(cantCancelledDate)) {
				throw new BookingCantCancel("Sorry failed to cancel the booking");
			}else {
				Bookings deletedBooking=dao.deleteBooking(bookingId);
				ResponseStructure<BookingDto> structure=new ResponseStructure<>();
	    		structure.setMessage("Booking deleted successfully");
	    		
	    		structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
	    		
	    		BookingDto bookingDto=this.mapper.map(dbBookings, BookingDto.class);
	            bookingDto.setMedicines(dbBookings.getMedicines());
	    		bookingDto.setCustomerDto(this.mapper.map(dbBookings.getCustomer(), CustomerDto.class));
	    		
	    		
	    		structure.setData(bookingDto);
	    		
	    		return new  ResponseEntity<ResponseStructure<BookingDto>>(structure,HttpStatus.CREATED);
			}
			
			
			
		}else {
//			booking id is not present
			throw new BoookingIdNotFoundException("Sorry faiiled to cancel booking");
		}
	}
	
}
