package com.philippe75.libraryWebapp.business.impl.manager;

import java.util.List;

import javax.inject.Named;

import com.philippe75.libraryWebapp.business.contract.manager.BookDtoManager;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookDto;

/**
 * <b>Implements BookDtoManager Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("bookManager")
public class BookDtoManagerImpl extends AbstractManagerServiceAccess implements BookDtoManager{

	/**
	 * Get all the {@link BookDto} with name required.
	 * 
	 * @param bookName the name of the book
	 * @return List<BookDto> listBookDto of {@link BookDto} with name required.
	 */
	@Override
	public List<BookDto> getListBookByName(String bookName) {
		
		List<BookDto> listBookDto = getBookSearchService().getListBookByName(bookName).getItem();
		
		return listBookDto;
	}

 
}
