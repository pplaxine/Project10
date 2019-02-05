package com.philippe75.libraryWS.consumer.contract.dao;

import java.util.List;

import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.Borrowing;

/**
 * <b>Contains all methods related to {@link Borrowing} for database requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface BorrowingDao {
	
	/**
	 * Method that gets, all the borrowings of a user.  
	 * 
	 * @param userMemeberId the member id of a user
	 * @return List<Borrowing> list of {@link Borrowing} of a user.
	 */
	List<Borrowing> getAllBorrowingForUser(String userMemberId) throws Exception;
	
	
}
