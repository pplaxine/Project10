package com.philippe75.libraryWS.business.contract.manager;

import java.util.Date;
import java.util.List;

import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

/**
 * <b>Contains all methods related to {@link Borrowing} requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface BorrowingManager {
	
	/**
	 * Method that gets, all the borrowings of a user.  
	 * 
	 * @param userMemeberId the member id of a user
	 * @return List<BorrowingDto> list of {@link Borrowing} of a user.
	 */
	List<BorrowingDto> getAllBorrowingForUser(String userMemberId) throws LibraryServiceException; 

	/**
	 * Method that gets, all the borrowings of a book.  
	 * 
	 * @param bookDto the book.
	 * @return List<BorrowingDto> list of {@link Borrowing} of a user.
	 */
	List<BorrowingDto> getAllBorrowingForBook(BookDto bookDto) throws LibraryServiceException; 
	
	/**
	 * Method that extend borrowing supposed end date. Also check first if an extention has already benn made.  
	 * 
	 * @param Borrowing the borrowing to update.
	 */
	void extendBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException, Exception;
	
	/**
	 * Method get the borrowing object.  
	 * 
	 * @param borrowingId the borrowing id.
	 * @return BorrowingDto the {@link Borrowing} dto object corresponding to the id.
	 */
	BorrowingDto getBorrowingById(Integer borrowingId) throws LibraryServiceException;
	
	/**
	 * Method get that creates new borrowing.  
	 * 
	 * @param borrowingDto the dto object of a new borrowing.
	 */
	void createBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException, Exception;
	
	/**
	 * Method that ends a borrowing when user returns a book.   
	 * 
	 * @param borrowingDto the borrowing comming to a end.
	 */
	void endBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException;
	
	/**
	 * Method that gets, all the borrowings with the either supposed end date or extended supposed end date overdue.  
	 * 
	 * @return List<BorrowingDto> list of {@link BorrowingDto}.
	 */
	List<BorrowingDto> getAllLateBorrowings() throws LibraryServiceException;
	
	/**
	 * Method that gets, all Borrowings {@link BorrowingDto} that have a mail reminder activated and within the time passed in parameter.  
	 *
	 * @param typeFiel Calendar.type example Calendar.WEEK_OF_YEAR 
	 * @param quantity quantity of that type example 2 (Weeks) 
	 */
	List<BorrowingDto> getAllBorrowingsToBeRemindedWithin(Integer typeField, Integer quantity) throws LibraryServiceException, Exception;
	
	/**
	 * Method that adds status of reminder mail being sent or not for a borrowing.  
	 * 
	 * @param borrowingIdId id of the Borrowing where status must be changed.
	 * @param status the new status to be persist.
	 */
	void updateBorrowingReminderMailStatus(BorrowingDto borrowingDto) throws LibraryServiceException, Exception;
}
