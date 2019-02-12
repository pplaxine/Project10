package com.philippe75.libraryWebapp.webapp.action;

import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.bookServ.LibraryServiceException_Exception;


public class MainAction extends ActionSupport {
	
	@Inject
	ManagerHandler managerHandler;
	
	//income 
	
	//outcome

	//G&S
	
	//METHODS

	public String doMain() {
		return ActionSupport.SUCCESS; 
	}
}
