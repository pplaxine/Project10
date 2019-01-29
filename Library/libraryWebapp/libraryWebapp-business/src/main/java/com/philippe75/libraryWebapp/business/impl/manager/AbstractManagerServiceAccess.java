package com.philippe75.libraryWebapp.business.impl.manager;

import com.philippe75.libraryWebapp.stub.generated.libraryService.BookSearchService;
import com.philippe75.libraryWebapp.stub.generated.libraryService.BookSearchServiceImplService;


public class AbstractManagerServiceAccess {
	
	private BookSearchService bookSearchService = new BookSearchServiceImplService().getBookSearchServiceImplPort();

	public BookSearchService getBookSearchService() {
		return bookSearchService;
	}
	
}
