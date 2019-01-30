package com.philippe75.libraryWS.business.impl.handler;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.contract.manager.BookManager;
import com.philippe75.libraryWS.business.contract.manager.UserAccountManager;

/**
 * <b>Implementation of ManagerHandler interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("managerHandler")
public class ManagerHandlerImpl implements ManagerHandler{
	
	/**
	 * injection of {@link UserAccountManager}
	 */
	@Inject
	private UserAccountManager userAccountManager;
	
	/**
	 * injection of {@link BookManager}
	 */
	@Inject
	private BookManager bookManager;

	@Override
	public UserAccountManager getUserAccountManager() {
		return userAccountManager;
	}

	@Override
	public BookManager getBookManager() {
		return bookManager;
	}
	
	
}
