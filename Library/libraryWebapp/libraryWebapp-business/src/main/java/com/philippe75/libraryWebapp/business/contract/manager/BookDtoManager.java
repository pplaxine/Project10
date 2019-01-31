package com.philippe75.libraryWebapp.business.contract.manager;

import java.util.List;

import com.philippe75.libraryWebapp.stub.generated.bookServ.BookDto;
/**
 * <b>Contains all methods related to {@link BookDto} requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface BookDtoManager {
	
	/**
	 * Method that gets, as there is several exemplar of the same book in each library, all the books with the required name,.
	 * 
	 * @param name of the book
	 * @return List<BookDto> list of all the books 
	 */
	List<BookDto> getListBookByName(String bookName);


}
