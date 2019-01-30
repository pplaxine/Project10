package com.philippe75.libraryWebapp.business.impl.manager;

import com.philippe75.libraryWebapp.stub.generated.libraryService.BookSearchService;
import com.philippe75.libraryWebapp.stub.generated.libraryService.BookSearchServiceImplService;

/**
 * <b>Abstract Class that allow access to the stub layer</b>
 * 
 * @see DaoHandler
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class AbstractManagerServiceAccess {
	
	/**
	 * Object communicating with the BookSearchService webservice endpoint.
	 * 
	 * @see BookSearchService
	 */
	private BookSearchService bookSearchService = new BookSearchServiceImplService().getBookSearchServiceImplPort();

	public BookSearchService getBookSearchService() {
		return bookSearchService;
	}
	
}
