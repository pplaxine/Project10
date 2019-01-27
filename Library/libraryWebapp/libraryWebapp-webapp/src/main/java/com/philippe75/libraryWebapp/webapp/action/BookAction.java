package com.philippe75.libraryWebapp.webapp.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.stub.generated.libraryService.BookDto;
import com.philippe75.libraryWebapp.stub.generated.libraryService.BookSearchService;
import com.philippe75.libraryWebapp.stub.generated.libraryService.BookSearchServiceImplService;


public class BookAction extends ActionSupport {
	
	//outcome
	private String test;
	private List<BookDto> listBookByName;
	

	//G&S
	public List<BookDto> getListBookByName() {
		return listBookByName;
	}
	public String getTest() {
		return test;
	}
	
	
	//METHODS
	
	public String getStringTest() {
		
		BookSearchService bss = new BookSearchServiceImplService().getBookSearchServiceImplPort();
		
		test = bss.test();
		
		
		listBookByName = bss.getListBookByName("Roméo et Juliette").getItem();
		
		return ActionSupport.SUCCESS; 
	}


	
	
}
