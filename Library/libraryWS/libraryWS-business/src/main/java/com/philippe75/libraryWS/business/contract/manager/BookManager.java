package com.philippe75.libraryWS.business.contract.manager;

import java.util.List;

import com.philippe75.libraryWS.business.dto.BookDto;

public interface BookManager {
	
	List<BookDto> getListBookByName(String name); 

}
