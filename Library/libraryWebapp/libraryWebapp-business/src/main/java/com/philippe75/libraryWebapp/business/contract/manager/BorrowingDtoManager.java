package com.philippe75.libraryWebapp.business.contract.manager;

import java.util.List;
import java.util.Map;

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
	 * @param String bookFullName full name of the book (name + author).
	 * @return List<BorrowingDto> list of {@link Borrowing} of a user.
	 */
	List<BorrowingDto> getAllBorrowingForBook(String bookFullName) throws LibraryServiceException_Exception;
	
	/**
	 * Method that gets the next borrowing to come to an end for a book.  
	 * 
	 * @param String bookName the name of the book.
	 * @Param String bookAuthor the author of the book.
	 * @return BorrowingDto the next {@link BorrowingDto} to come to an end.
	 */
	BorrowingDto getNextBorrowingToComeToEnd(String bookName, String bookAuthor) throws LibraryServiceException_Exception;
	
	/**
	 * Method that gets the next borrowing to come to an end for each booking of a booking list.  
	 * 
	 * @param List<BookBookingDto> listBookBookingDto the list of {@link BookBookingDto} to be checked.
	 * @return Map<BookBookingDto,BorrowingDto> the map with the Key {@link BookBookingDto} and the Value the next {@link BorrowingDto} to come to an end for this key.
	 * @throws LibraryServiceException_Exception 
	 */
	Map<BookBookingDto,BorrowingDto> getNexBorrowingToComeToEndForEachBookBooking(List<BookBookingDto> listBookBooking) throws LibraryServiceException_Exception;
	
	/**
	 * Method that the number of people in front of member in the waiting list for each booking of a booking list.  
	 * 
	 * @param List<BookBookingDto> listBookBookingDto the list of {@link BookBookingDto} to be checked.
	 * @return Map<BookBookingDto,Integer> the map with the Key {@link BookBookingDto} and the Value the position.
	 * @throws LibraryServiceException_Exception 
	 * @throws Exception_Exception 
	 */
	Map<BookBookingDto,Integer> getPositionInWaintingListForEachBookBooking(List<BookBookingDto> listBookBooking) throws LibraryServiceException_Exception, Exception_Exception;
	
	/**
	 * Method that extends a borrowing.  
	 * 
	 * @param borrowingId the id of the borrowing
	 * @param numberOfWeek the number of week the borrowing needs to be extended. 
	 * 
	 */
	void extendBorrowing(Integer borrowingId, Integer numberOfWeek) throws LibraryServiceException_Exception, Exception_Exception;
	
	/**
	 * Method that gets, the waiting list of members for a book.  
	 * 
	 * @param bookName the name of the book.
	 * @param bookAuthor the author of the book.
	 * 
	 * @return List<BookBooking> list of {@link BookBooking} for all copies of this book.
	 */
	List<BookBookingDto> getAllNotEndedBookingsForABook(String bookName, String bookAuthor) throws LibraryServiceException_Exception, Exception_Exception;
	
	/**
	 * Method that gets, all the active bookings of a members.  
	 * 
	 * @param String user member id.
	 * 
	 * @return List<BookBooking> list of all {@link BookBooking} for a user.
	 */
	List<BookBookingDto> getAllNotEndedBookingsForMember(String userMemberId) throws LibraryServiceException_Exception, Exception_Exception;
	
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
