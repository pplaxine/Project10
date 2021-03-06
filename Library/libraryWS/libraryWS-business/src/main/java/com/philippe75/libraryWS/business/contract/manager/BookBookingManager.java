package com.philippe75.libraryWS.business.contract.manager;

import java.util.List;

import com.philippe75.libraryWS.business.dto.BookBookingDto;
import com.philippe75.libraryWS.business.dto.BookDto;
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
	
	/**
	 * Method that gets, the waiting list of members for a book.  
	 * 
	 * @param book the book.
	 * 
	 * @return List<BookBookingDto> list of {@link BookBookingDto} for all copies of this book.
	 */
	List<BookBookingDto> getAllBookingsForABook(BookDto bookDto) throws LibraryServiceException, Exception;
	
	/**
	 * Method that gets, all the bookings of a members.  
	 * 
	 * @param String user member id.
	 * 
	 * @return List<BookBookingDto> list of all {@link BookBookingDto} for a user.
	 */
	List<BookBookingDto> getAllBookingsForMember(String userMemberId) throws LibraryServiceException, Exception;
	
	/**
	 * Method that gets, all the active bookings.  
	 * 
	 * @return List<BookBookingDto> list of all active {@link BookBookingDto}.
	 * @throws LibraryServiceException, Exception 
	 */
	List<BookBookingDto> getAllNotEndedBookings() throws LibraryServiceException, Exception;
	
	/**
	 * Method that adds a mail sending date to a booking.  
	 * 
	 * @param bookBookingId id of the Booking where date must be added .
	 * 
	 * @return Integer Id of {@link BookBooking} updated.
	 */
	void updateMailDateBooking(int bookBookingId) throws LibraryServiceException;
	
	/**
	 * Method that ends, all active {@link BookBooking} that have a date that is exceed, by the amount of time passed in parameter, the date of now.  
	 *
	 * @param typeFiel Calendar.type example Calendar.WEEK_OF_YEAR 
	 * @param quantity quantity of that type example 2 (Weeks) 
	 */
	void endAllActiveBookingsExceededOf(Integer typeField, Integer quantity) throws LibraryServiceException;

}
