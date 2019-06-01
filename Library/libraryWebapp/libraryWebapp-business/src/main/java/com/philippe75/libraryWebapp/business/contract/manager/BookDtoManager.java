package com.philippe75.libraryWebapp.business.contract.manager;

import java.util.List;

import com.philippe75.libraryWebapp.stub.generated.bookServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.bookServ.LibraryServiceException_Exception;
/**
 * <b>Contains all methods related to {@link BookDto} requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface BookDtoManager {
	
	/**
	 * Method that gets, as there is several exemplar of the same book in each library, all the books with the required name.
	 * 
	 * @param name the name of the book
	 * @return List<BookDto> list of all the books 
	 */
	List<BookDto> getListBookByName(String bookName) throws LibraryServiceException_Exception;

	/**
	 * If entry contained in the name of books. Get the 10 first book matching with.  
	 * 
	 * @param name the string to be contained in the book name
	 * @return List<BookDto> listBookDto of {@link BookDto} containing the entry in their name.
	 */
	List<BookDto> getListBookStartingBy(String name) throws LibraryServiceException_Exception;
	
	/**
	 * Method that gets, all the available copies of the book. 
	 * 
	 * @param name the name of the book. 
	 * @return List<BookDto> listBookDto of {@link BookDto} all the available copies.
	 */
	List<BookDto> getListBookAvailableByName(String bookName) throws LibraryServiceException_Exception;
	
	
}
