package com.philippe75.libraryWebapp.business.impl.handler;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.business.contract.manager.BookDtoManager;

@Named("managerHandler")
public class ManagerHandlerImpl implements ManagerHandler {

	@Inject
	private BookDtoManager bookDtoManager;

	@Override
	public BookDtoManager getBookDtoManager() {
		
		return bookDtoManager;
	} 
	
}
