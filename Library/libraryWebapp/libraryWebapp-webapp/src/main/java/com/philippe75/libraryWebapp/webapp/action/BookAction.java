package com.philippe75.libraryWebapp.webapp.action;

import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.business.contract.manager.BookDtoManager;
import com.philippe75.libraryWebapp.stub.generated.libraryService.BookDto;


public class BookAction extends ActionSupport {
	
	@Inject
	ManagerHandler managerHandler;
	
	//outcome
	private List<BookDto> listBookByName;

	//G&S
	public List<BookDto> getListBookByName() {
		return listBookByName;
	}
	
	//METHODS
	
	public String doListBookByName() {
		
		listBookByName = managerHandler.getBookDtoManager().getListBookByName("Roméo et Juliette");
		
		return ActionSupport.SUCCESS; 
	}


	
	
}
