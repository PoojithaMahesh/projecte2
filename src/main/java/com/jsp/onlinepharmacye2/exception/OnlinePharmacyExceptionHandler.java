package com.jsp.onlinepharmacye2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.onlinepharmacye2.util.ResponseStructure;
@RestControllerAdvice
public class OnlinePharmacyExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> admoinIdNotFoundException(AdminIdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("ADMIN ID IS NOT PRESENT");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingcantcancel(BookingAlreadyCancelledException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Booking Already Cancelled");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingcantcancel(BookingCantCancel exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Booking Already its a date");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingcantcancel(BookingAlreadyDeliveredException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Booking Already Delivered");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingIdNotFoundException(BoookingIdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("BOOKING ID IS NOT PRESENT");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerIdNotFoundException(CustomerIdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Customer ID IS NOT PRESENT");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> storeIdNotFoundException(MedicalStoreIdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("STORE ID IS NOT PRESENT");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminNotFoundException(AdminNotFoundWithThisEmail exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("ADMIN Email IS NOT PRESENT .... INVALID EMAIL!!!!!!!!!!!");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminPasswordNotFoundException(AdminPasswordNotValidException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage(" INVALID PASSWORD!!!!!!!!!!!");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adressIdNotFoundException(AddressIdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("ADDRESS ID IS NOT PRESENT");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicineIdNotFoundException(MedicineIdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Medicine ID IS NOT PRESENT");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicineNameNotFoundException(MedicineNameNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Medicine NOT PRESENT in this name");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> staffIdNotFoundException(StaffIdNotFoundException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("STAFF ID IS NOT PRESENT");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
