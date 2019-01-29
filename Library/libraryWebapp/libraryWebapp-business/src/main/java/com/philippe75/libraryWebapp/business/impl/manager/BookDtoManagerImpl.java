package com.philippe75.libraryWebapp.business.impl.manager;

import java.util.List;

import javax.inject.Named;

import com.philippe75.libraryWebapp.business.contract.manager.BookDtoManager;
import com.philippe75.libraryWebapp.stub.generated.libraryService.BookDto;

@Named("bookManager")
public class BookDtoManagerImpl extends AbstractManagerServiceAccess implements BookDtoManager{

	@Override
	public List<BookDto> getListBookByName(String bookName) {
		
		List<BookDto> listBookDto = getBookSearchService().getListBookByName(bookName).getItem();
		
		return listBookDto;
	}

 
}
