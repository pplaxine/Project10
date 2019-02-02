package com.philippe75.libraryWebapp.business.impl.handler;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.business.contract.manager.BookDtoManager;
import com.philippe75.libraryWebapp.business.contract.manager.UserAccountDtoManager;

/**
 * <b>Implementation of ManagerHandler interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("managerHandler")
public class ManagerHandlerImpl implements ManagerHandler {

	/**
	 * injection of {@link BookDtoManager}
	 */
	@Inject
	private BookDtoManager bookDtoManager;

	/**
	 * injection of {@link BookDtoManager}
	 */
	@Inject
	private UserAccountDtoManager userAccountDtoManager;
	
	@Override
	public BookDtoManager getBookDtoManager() {
		
		return bookDtoManager;
	}

	@Override
	public UserAccountDtoManager getUserAccountDtoManager() {
		
		return userAccountDtoManager;
	} 
	
}
