package com.philippe75.libraryWS.consumer.contract.dao;

import java.util.List;

import com.philippe75.libraryWS.model.book.Book;

/**
 * <b>Contains all methods related to {@link Book} for database requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface BookDao {
	
	/**
	 * Method that gets, the 10 first book containing entry in their name.  
	 * 
	 * @param name the string to be contained in the book name
	 * @return List<Book> list of {@link Book} containing the entry in their name.
	 */
	List<Book> getListBookStartingBy(String name) throws Exception;
	
	/**
	 * Method that gets, as there is several exemplar of the same book in each library, all the books with the required name,.
	 * 
	 * @param name of the book
	 * @return List<Book> list of all the books with that name. 
	 */
	List<Book> getListBookByName(String name) throws Exception;
	
	
}
