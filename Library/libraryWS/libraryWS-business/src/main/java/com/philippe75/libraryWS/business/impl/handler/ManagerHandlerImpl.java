package com.philippe75.libraryWS.business.impl.handler;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.contract.manager.BookManager;
import com.philippe75.libraryWS.business.contract.manager.UserAccountManager;

@Named("managerHandler")
public class ManagerHandlerImpl implements ManagerHandler{
	
	@Inject
	private UserAccountManager userAccountManager;
	
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
