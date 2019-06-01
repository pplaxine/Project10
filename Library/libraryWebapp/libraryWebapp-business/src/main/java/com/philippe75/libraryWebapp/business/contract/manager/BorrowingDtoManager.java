package com.philippe75.libraryWebapp.business.contract.manager;

import java.util.List;

import javax.inject.Named;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookBookingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.Exception_Exception;
/**
 * <b>Contains all methods related to {@link Borrowing} requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("borrowingManager")
public interface BorrowingDtoManager {
	
	/**
	 * Method that gets, all the borrowings of a user.  
	 * 
	 * @param userMemeberId the member id of a user
	 * @return List<BorrowingDto> list of {@link Borrowing} of a user.
	 */
	List<BorrowingDto> getAllBorrowingForUser(String userMemberId) throws LibraryServiceException_Exception;
	
	/**
	 * Method that gets, all the borrowings of a Book.  
	 * 
	 * @param bookDto the book.
	 * @return List<BorrowingDto> list of {@link Borrowing} of a user.
	 */
	List<BorrowingDto> getAllBorrowingForBook(String bookFullName) throws LibraryServiceException_Exception;
	
	/**
	 * Method that get the next borrowing to come to an end for a book.  
	 * 
	 * @param BookDto bookDto the {@link BookDto} to be checked.
	 * @return BorrowingDto the next {@link BorrowingDto} to come to an end.
	 */
	BorrowingDto getNextBorrowingToComeToEnd(String bookDtoName) throws LibraryServiceException_Exception;
	
	/**
	 * Method that extends a borrowing.  
	 * 
	 * @param borrowingId the id of the borrowing
	 * @param numberOfWeek the number of week the borrowing needs to be extended. 
	 * 
	 */
	void extendBorrowing(Integer borrowingId, Integer numberOfWeek) throws LibraryServiceException_Exception;
	
	/**
	 * Method that gets, the waiting list of members for a book.  
	 * 
	 * @param book the book.
	 * 
	 * @return List<BookBooking> list of {@link BookBooking} for all copies of this book.
	 */
	List<BookBookingDto> getAllNotEndedBookingsForABook(String bookFullName) throws LibraryServiceException_Exception, Exception_Exception;
	
	/**
	 * Method that gets, all the bookings of a members.  
	 * 
	 * @param String user member id.
	 * 
	 * @return List<BookBooking> list of all {@link BookBooking} for a user.
	 */
	List<BookBookingDto> getAllBookingsForMember(String userMemberId) throws LibraryServiceException_Exception, Exception_Exception;
	
	/**
	 * Method that creates new booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of a new booking.
	 * 
	 * @return int id of the newly created BookBooking.
	 */
	int createBooking(BookBookingDto bookBookingDto) throws LibraryServiceException_Exception, Exception_Exception;
	
	/**
	 * Method that ends booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of booking to end.
	 */
	void endBooking(int bookBookingId) throws LibraryServiceException_Exception;
	


	
}
