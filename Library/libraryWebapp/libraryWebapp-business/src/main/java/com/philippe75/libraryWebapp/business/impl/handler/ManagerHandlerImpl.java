package com.philippe75.libraryWebapp.business.impl.handler;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.business.contract.manager.BookDtoManager;

/**
 * <b>Implementation of ManagerHandler interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("managerHandler")
public class ManagerHandlerImpl implements ManagerHandler {

	/**
	 * injection of {@link BookManager}
	 */
	@Inject
	private BookDtoManager bookDtoManager;

	@Override
	public BookDtoManager getBookDtoManager() {
		
		return bookDtoManager;
	} 
	
}
