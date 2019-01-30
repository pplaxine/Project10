package com.philippe75.libraryWS.business.contract.manager;

import java.util.List;

import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;
import com.philippe75.libraryWS.model.book.Book;

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
	List<BookDto> getListBookByName(String name); 

}
