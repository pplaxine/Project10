package com.philippe75.libraryWebapp.business.contract.manager;

import java.util.List;

import com.philippe75.libraryWebapp.stub.generated.libraryService.BookDto;

public interface BookDtoManager {
	
	List<BookDto> getListBookByName(String bookName);


}
