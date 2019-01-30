package com.philippe75.libraryWS.business.contract.handler;

import com.philippe75.libraryWS.business.contract.manager.BookManager;
import com.philippe75.libraryWS.business.contract.manager.UserAccountManager;

/**
 * <b>Provides the different object managers</b>
 * 
 * @see UserAccountManager
 * @see BookManager
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface ManagerHandler {
	
	/**
	 * Get access to {@link UserAccountManager}.
	 * 
	 * @return UserAccountManager
	 */
	UserAccountManager getUserAccountManager();
	
	/**
	 * Get access to {@link BookManager}.
	 * 
	 * @return BookManager
	 */
	BookManager getBookManager();
}
