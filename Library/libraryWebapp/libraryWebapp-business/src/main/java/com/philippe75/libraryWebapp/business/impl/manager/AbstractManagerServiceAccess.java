package com.philippe75.libraryWebapp.business.impl.manager;


import com.philippe75.libraryWebapp.stub.generated.authServ.AuthentificationService;
import com.philippe75.libraryWebapp.stub.generated.authServ.AuthentificationServiceImplService;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookSearchService;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookSearchServiceImplService;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingService;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingServiceImplService;

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

	/**
	 * Object communicating with the AuthentificationService webservice endpoint.
	 * 
	 * @see AuthentificationService
	 */
	private AuthentificationService authService = new AuthentificationServiceImplService().getAuthentificationServiceImplPort();
	
	/**
	 * Object communicating with the BorrowingService webservice endpoint.
	 * 
	 * @see BorrowingService
	 */
	private BorrowingService borrowingService = new BorrowingServiceImplService().getBorrowingServiceImplPort();
	
	public BookSearchService getBookSearchService() {
		return bookSearchService;
	}

	public AuthentificationService getAuthService() {
		return authService;
	}
	
	public BorrowingService getBorrowingService() {
		return borrowingService;
	}
	
	
	
}
