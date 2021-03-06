package com.philippe75.libraryWS.exposure.service.borrowing;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.philippe75.libraryWS.business.dto.BookBookingDto;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.model.book.BookBooking;
import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;
import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Borrowing service end point Interface.</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface BorrowingService {

	/**
	 * Method that gets, all the borrowings of a user.  
	 * 
	 * @param userMemeberId the member id of a user
	 * @return List<BorrowingDto> list of {@link Borrowing} of a user.
	 */
	@WebMethod
	List<BorrowingDto> getAllBorrowingForUser(String userMemberId) throws LibraryServiceException; 
	
	/**
	 * Method that gets, all the borrowings of a book.
	 * 
	 * @param userMemberID the user member id of the user
	 * @return List<BorrowingDto> list of Dto object of {@link Borrowing} of a user.
	 */
	@WebMethod
	List<BorrowingDto> getAllBorrowingForBook(BookDto bookDto) throws LibraryServiceException;
	
	/**
	 * Method that extend borrowing supposed end date. Also check first if an extention has already benn made.  
	 * 
	 * @param Borrowing the borrowing to update.
	 */
	@WebMethod
	void extendBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException, Exception;
	
	/**
	 * Method get the borrowing object.  
	 * 
	 * @param borrowingId the borrowing id.
	 * @return BorrowingDto the {@link Borrowing} dto object corresponding to the id.
	 */
	@WebMethod
	BorrowingDto getBorrowingById(Integer borrowingId) throws LibraryServiceException;
	
	/**
	 * Method get that creates new borrowing.  
	 * 
	 * @param borrowingDto the dto object of a new borrowing.
	 */
	@WebMethod
	void createBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException, Exception;
	
	/**
	 * Method that ends a borrowing when user returns a book.   
	 * 
	 * @param borrowingDto the borrowing comming to a end.
	 */
	@WebMethod
	void endBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException;
	
	/**
	 * Method that gets, all the borrowings with the either supposed end date or extended supposed end date overdue.  
	 * 
	 * @return List<BorrowingDto> list of {@link BorrowingDto}.
	 */
	@WebMethod
	List<BorrowingDto> getAllLateBorrowings() throws LibraryServiceException;
	
	/**
	 * Method that creates new booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of a new booking.
	 * 
	 * @return int id of the newly created BookBooking.
	 */
	@WebMethod
	int createBooking(BookBookingDto bookBookingDto) throws LibraryServiceException, Exception;
	
	/**
	 * Method that ends booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of booking to end.
	 */
	@WebMethod
	void endBooking(int bookBookingId) throws LibraryServiceException;
	
	/**
	 * Method that gets, the waiting list of members for a book.  
	 * 
	 * @param book the book.
	 * 
	 * @return List<BookBookingDto> list of {@link BookBookingDto} for all copies of this book.
	 */
	@WebMethod
	List<BookBookingDto> getAllBookingsForABook(BookDto bookDto) throws LibraryServiceException, Exception;
	
	/**
	 * Method that gets, all the bookings of a members.  
	 * 
	 * @param String user member id.
	 * 
	 * @return List<BookBookingDto> list of all {@link BookBookingDto} for a user.
	 */
	@WebMethod
	List<BookBookingDto> getAllBookingsForMember(String userMemberId) throws LibraryServiceException, Exception;
	
	/**
	 * Method that gets, all active bookings.  
	 * 
	 * @return List<BookBookingDto> list of all active {@link BookBookingDto}.
	 */
	@WebMethod
	List<BookBookingDto> getAllNotEndedBookings() throws LibraryServiceException, Exception;
	
	/**
	 * Method that adds a mail sending date to a booking.  
	 * 
	 * @param bookBookingId id of the Booking where date must be added .
	 * 
	 * @return Integer Id of {@link BookBooking} updated.
	 */
	@WebMethod
	void updateMailDateBooking(int bookBookingId) throws LibraryServiceException;
	
	/**
	 * Method that ends, all active {@link BookBooking} that have a date that is exceed, by the amount of time passed in parameter, the date of now.  
	 *
	 * @param typeFiel Calendar.type example Calendar.WEEK_OF_YEAR 
	 * @param quantity quantity of that type example 2 (Weeks) 
	 */
	@WebMethod
	void endAllActiveBookingsExceededOf(Integer typeField, Integer quantity) throws LibraryServiceException;
	
	/**
	 * Method that update users mail reminder status.  
	 * 
	 * @param userAccountDto the {@link UserAccountDto} object containing the userMemberId and mailReminder status.
	 * 
	 */
	@WebMethod
	void updateMailReminder(UserAccountDto userAccountDto) throws LibraryServiceException, Exception;
	
	/**
	 * @param userMemeberId the member id of the user.
	 * @return UserAccountDto the Dto object of a {@link UserAccount} with the id required.  
	 */
	@WebMethod
	UserAccountDto getUserMailReminderStatus(String userMemberId) throws LibraryServiceException, Exception;
	
	/**
	 * Method that gets, all Borrowings {@link BorrowingDto} that have a mail reminder activated and within the time passed in parameter.  
	 *
	 * @param typeFiel Calendar.type example Calendar.WEEK_OF_YEAR 
	 * @param quantity quantity of that type example 2 (Weeks) 
	 */
	@WebMethod
	List<BorrowingDto> getAllBorrowingsToBeRemindedWithin(Integer typeField, Integer quantity) throws LibraryServiceException, Exception;
	
	/**
	 * Method that adds status of reminder mail being sent or not for a borrowing.  
	 * 
	 * @param borrowingIdId id of the Borrowing where status must be changed.
	 * @param status the new status to be persist.
	 */
	@WebMethod
	void updateBorrowingReminderMailStatus(BorrowingDto borrowingDto) throws LibraryServiceException, Exception;
}

