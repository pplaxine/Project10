package com.philippe75.libraryWS.consumer.contract.dao;

import java.util.Date;
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
	
	/**
	 * Method that gets, all the borrowings of a book.  
	 * 
	 * @param book the book.
	 * @return List<Borrowing> list of {@link Borrowing} of a book.
	 */
	List<Borrowing> getAllBorrowingForBook(Book book) throws Exception;
	
	/**
	 * Method that extend borrowing supposed end date.  
	 * 
	 * @param Borrowing the borrowing to update.
	 */
	void extendBorrowing(Borrowing borrowing) throws Exception;
	
	/**
	 * Method get the borrowing object.  
	 * 
	 * @param borrowingId the borrowing id.
	 * @return Borrowing the borrowing object corresponding to the id.
	 */
	Borrowing getBorrowingById(Integer borrowingId) throws Exception;
	
	/**
	 * Method get that creates new borrowing.  
	 * 
	 * @param borrowing the new borrowing.
	 */
	void createBorrowing(Borrowing borrowing) throws Exception;
	
	/**
	 * Method that ends a borrowing when user returns a book.  
	 * 
	 * @param borrowing the borrowing comming to a end.
	 */
	void endBorrowing(Borrowing borrowing) throws Exception;
	
	/**
	 * Method that gets, all the borrowings with the either supposed end date or extended supposed end date overdue.  
	 * 
	 * @return List<Borrowing> list of {@link Borrowing}.
	 */
	List<Borrowing> getAllLateBorrowings() throws Exception;

	/**
	 * Method that gets, all Borrowings {@link Borrowing} that have a mail reminder activated before the date passed as parameter.  
	 *
	 * @param date the limit date. 
	 * @return List<BookBooking> list of all borrowings {@link Borrowing} before the date.
	 */
	List<Borrowing> getAllBorrowingsToRemindBeforeThisDate(Date date) throws Exception;
}
