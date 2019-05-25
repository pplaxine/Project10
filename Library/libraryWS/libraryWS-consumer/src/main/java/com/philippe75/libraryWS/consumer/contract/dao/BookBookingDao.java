package com.philippe75.libraryWS.consumer.contract.dao;

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
	 * Method that add a booking to the booking queue of a book.  
	 * 
	 * @param bookBooking the Booking to be added in queue.
	 * 
	 * @return Integer Id of {@link BookBooking} newly persisted.
	 */
	int createBookBooking(BookBooking bookBooking)throws Exception;
	
	void endBookBooking(int bookBookingId) throws Exception;
	
}
