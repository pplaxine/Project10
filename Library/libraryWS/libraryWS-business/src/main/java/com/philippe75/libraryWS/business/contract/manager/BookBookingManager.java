package com.philippe75.libraryWS.business.contract.manager;

import com.philippe75.libraryWS.business.dto.BookBookingDto;
import com.philippe75.libraryWS.model.book.BookBooking;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

/**
 * <b>Contains all methods related to {@link BookBooking} requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface BookBookingManager {
	
	/**
	 * Method that creates new booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of a new booking.
	 * 
	 * @return int id of the newly created BookBooking.
	 */
	int createBooking(BookBookingDto bookBookingDto) throws LibraryServiceException, Exception;
	
	/**
	 * Method that ends booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of booking to end.
	 */
	void endBooking(int bookBookingId) throws LibraryServiceException;
}
