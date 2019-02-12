package com.philippe75.libraryWebapp.webapp.action;

import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.bookServ.LibraryServiceException_Exception;


public class BookAction extends ActionSupport {
	
	@Inject
	ManagerHandler managerHandler;
	
	//income 
	private String bookDto;
	
	//outcome
	private List<BookDto> listBookByName;

	//G&S
	public List<BookDto> getListBookByName() {
		return listBookByName;
	}
	public String getBookDto() {
		return bookDto;
	}
	public void setBookDto(String bookDto) {
		this.bookDto = bookDto;
	}

	
	//METHODS
	public String doListBookByName() {
		
		try {
			listBookByName = managerHandler.getBookDtoManager().getListBookByName("Roméo et Juliette");
		} catch (LibraryServiceException_Exception e) {
			if((e.getMessage()).equals("NoResultException")) {
				this.addFieldError("bookDto", getText("book.failure.noResult"));
			}else {
				this.addActionError(getText("general.failure"));
			}
		}
		
		return ActionSupport.SUCCESS; 
	}
	
	public String doBookByName() {
	
		try {
			listBookByName = managerHandler.getBookDtoManager().getListBookStartingBy(bookDto);
		} catch (LibraryServiceException_Exception e) {
			if((e.getMessage()).equals("NoResultException")) {
				this.addFieldError("bookDto", getText("login.failure.login"));
			}else {
				this.addActionError(getText("general.failure"));
			}
		}
		
		return ActionSupport.SUCCESS; 
	}


	
	
}
