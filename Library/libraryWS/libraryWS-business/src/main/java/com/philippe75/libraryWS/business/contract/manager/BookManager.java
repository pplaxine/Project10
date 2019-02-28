package com.philippe75.libraryWS.business.contract.manager;

import java.util.List;

import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

/**
 * <b>Contains all methods related to {@link Book} requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface BookManager {
	
	/**
	 * Method that gets, as there is several exemplar of the same book in each library, all the books with the required name,.
	 * 
	 * @param name of the book
	 * @return List<BookDto> list of all the books 
	 */
	List<BookDto> getListBookByName(String name) throws LibraryServiceException; 
	
	/**
	 * Method that gets, the 10 first book containing entry in their name.  
	 * 
	 * @param name the string to be contained in the book name
	 * @return List<BookDto> listBookDto of {@link BookDto} containing the entry in their name.
	 */
	List<BookDto> getListBookStartingBy(String name) throws LibraryServiceException;

}
