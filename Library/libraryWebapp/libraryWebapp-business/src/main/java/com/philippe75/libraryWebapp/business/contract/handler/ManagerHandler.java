package com.philippe75.libraryWebapp.business.contract.handler;

import com.philippe75.libraryWebapp.business.contract.manager.BookDtoManager;

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
	 * Get access to {@link BookManager}.
	 * 
	 * @return BookManager
	 */
	BookDtoManager getBookDtoManager();
	
}
