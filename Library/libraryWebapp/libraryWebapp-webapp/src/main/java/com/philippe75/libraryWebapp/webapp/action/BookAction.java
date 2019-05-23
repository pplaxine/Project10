package com.philippe75.libraryWebapp.webapp.action;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.bookServ.LibraryServiceException_Exception;

@Named("bookAction")
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
		String result = ActionSupport.INPUT;
		if(bookDto.trim().length() != 0) {
			try {
				listBookByName = managerHandler.getBookDtoManager().getListBookByName(bookDto);
				result = ActionSupport.SUCCESS;
			} catch (LibraryServiceException_Exception e) {
				if((e.getMessage()).equals("NoResultException")) {
					this.addActionError(getText("book.failure.noResult"));
				}else {
					this.addActionError(getText("general.failure"));
				}
			}
		}
		return result; 
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
