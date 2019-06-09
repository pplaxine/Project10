package com.philippe75.libraryWS.consumer.contract.dao;

import java.util.Date;
import java.util.List;

import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.BookBooking;

/**
 * <b>Contains all methods related to {@link BookBooking} for database requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface BookBookingDao {

	/**
	 * Method that gets, the waiting list of members for a book.  
	 * 
	 * @param book the book.
	 * 
	 * @return List<BookBooking> list of {@link BookBooking} for all copies of this book.
	 */
	List<BookBooking> getAllBookingsForABook(Book book) throws Exception;
	
	/**
	 * Method that gets, all the bookings of a members.  
	 * 
	 * @param String user member id.
	 * 
	 * @return List<BookBooking> list of all {@link BookBooking} for a user.
	 */
	List<BookBooking> getAllBookingsForMember(String userMemberId) throws Exception;
	
	/**
	 * Method that gets, all active bookings.  
	 * 
	 * @return List<BookBooking> list of all active {@link BookBooking}.
	 */
	List<BookBooking> getAllNotEndedBookings() throws Exception;
	
	/**
	 * Method that gets, all active {@link BookBooking} before the date passed in parameter.  
	 *
	 * @param date the limit date. 
	 * @return List<BookBooking> list of all active {@link BookBooking} before the date.
	 */
	List<BookBooking> getAllActiveBookingsBeforeThisDate(Date date) throws Exception;
	
	/**
	 * Method that add a booking to the booking queue of a book.  
	 * 
	 * @param bookBooking the Booking to be added in queue.
	 * 
	 * @return Integer Id of {@link BookBooking} newly persisted.
	 */
	int createBookBooking(BookBooking bookBooking)throws Exception;
	
	/**
	 * Method that adds a mail sending date to a booking.  
	 * 
	 * @param bookBookingId id of the Booking where date must be added .
	 * 
	 * @return Integer Id of {@link BookBooking} updated.
	 */
	void updateMailDateBooking(int bookBookingId)throws Exception;
	
	/**
	 * Method that ends a booking.  
	 * 
	 * @param bookBookingId the id of the Booking to be deleted.
	 */
	void endBookBooking(int bookBookingId) throws Exception;
	
}
