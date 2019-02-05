package com.philippe75.libraryWS.business.contract.manager;

import java.util.List;

import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;
import com.philippe75.libraryWS.model.book.Book;
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


}
