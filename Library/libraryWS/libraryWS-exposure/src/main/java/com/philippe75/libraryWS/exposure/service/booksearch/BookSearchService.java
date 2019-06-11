package com.philippe75.libraryWS.exposure.service.booksearch;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

/**
 * <b>Book service end point Interface.</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */

@WebService
@SOAPBinding(style = Style.RPC)
public interface BookSearchService {

	/**
	 * Method that gets, as there is several exemplar of the same book in each library, all the books with the required name,.
	 * 
	 * @param name of the book
	 * @return list of all the books 
	 */
	@WebMethod
	public List<BookDto> getListBookByName(String name) throws LibraryServiceException;
	
	/**
	 * Method that gets, the 10 first book containing entry in their name.  
	 * 
	 * @param name the string to be contained in the book name
	 * @return List<BookDto> listBookDto of {@link BookDto} containing the entry in their name.
	 */
	@WebMethod
	public List<BookDto> getListBookStartingBy(String name) throws LibraryServiceException;
}
