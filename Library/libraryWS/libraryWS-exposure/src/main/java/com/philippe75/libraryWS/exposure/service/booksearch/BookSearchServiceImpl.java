package com.philippe75.libraryWS.exposure.service.booksearch;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

/**
 * <b>Book service end point Class.</b>
 * 
 * <p>
 * 	  Service related to books of the library
 * </p>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@WebService(endpointInterface="com.philippe75.libraryWS.exposure.service.booksearch.BookSearchService")
public class BookSearchServiceImpl extends SpringBeanAutowiringSupport implements BookSearchService{
	
	/**
	 * Access to business layer.
	 * 
	 * @see ManagerHandler
	 */
	@Inject
	private ManagerHandler managerHandler;
	
	/**
	 * Method that gets, as there is several exemplar of the same book in each library, all the books with the required name,.
	 * 
	 * @param name of the book
	 * @return list of all the books 
	 */
	@Override
	public List<BookDto> getListBookByName(String name) throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return (List<BookDto>)managerHandler.getBookManager().getListBookByName(name);
	}

	/**
	 * Method that gets, the 10 first book containing entry in their name.  
	 * 
	 * @param name the string to be contained in the book name
	 * @return List<BookDto> listBookDto of {@link BookDto} containing the entry in their name.
	 */
	@Override
	public List<BookDto> getListBookStartingBy(String name) throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return (List<BookDto>)managerHandler.getBookManager().getListBookStartingBy(name);
	}
	
	
	
}
