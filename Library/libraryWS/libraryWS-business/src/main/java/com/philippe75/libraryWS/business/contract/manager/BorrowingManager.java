package com.philippe75.libraryWS.business.contract.manager;

import java.util.List;

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
	 * Method that extend borrowing supposed end date. Also check first if an extention has already benn made.  
	 * 
	 * @param Borrowing the borrowing to update.
	 */
	void extendBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException;
	
	/**
	 * Method get the borrowing object.  
	 * 
	 * @param borrowingId the borrowing id.
	 * @return BorrowingDto the {@link Borrowing} dto object corresponding to the id.
	 */
	BorrowingDto getBorrowingById(Integer borrowingId) throws LibraryServiceException;
}
