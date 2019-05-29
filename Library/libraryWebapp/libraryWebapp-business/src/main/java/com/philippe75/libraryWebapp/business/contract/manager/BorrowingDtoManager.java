package com.philippe75.libraryWebapp.business.contract.manager;

import java.util.List;

import javax.inject.Named;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookBookingDto;
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
	 * Method that extends a borrowing.  
	 * 
	 * @param borrowingId the id of the borrowing
	 * @param numberOfWeek the number of week the borrowing needs to be extended. 
	 * 
	 */
	void extendBorrowing(Integer borrowingId, Integer numberOfWeek) throws LibraryServiceException_Exception;
	
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
