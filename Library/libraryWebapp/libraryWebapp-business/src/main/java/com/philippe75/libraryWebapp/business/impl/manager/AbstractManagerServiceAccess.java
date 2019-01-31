package com.philippe75.libraryWebapp.business.impl.manager;

import com.philippe75.libraryWebapp.stub.generated.authServ.AuthentificationService;
import com.philippe75.libraryWebapp.stub.generated.authServ.AuthentificationServiceImplService;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookSearchService;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookSearchServiceImplService;

/**
 * <b>Abstract Class that allow access to the stub layer</b>
 * 
 * @see DaoHandler
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public abstract class AbstractManagerServiceAccess {
	
	/**
	 * Object communicating with the BookSearchService webservice endpoint.
	 * 
	 * @see BookSearchService
	 */
	private BookSearchService bookSearchService = new BookSearchServiceImplService().getBookSearchServiceImplPort();

	private AuthentificationService authService = new AuthentificationServiceImplService().getAuthentificationServiceImplPort();
	
	public BookSearchService getBookSearchService() {
		return bookSearchService;
	}

	public AuthentificationService getAuthService() {
		return authService;
	}
	
	
	
}
