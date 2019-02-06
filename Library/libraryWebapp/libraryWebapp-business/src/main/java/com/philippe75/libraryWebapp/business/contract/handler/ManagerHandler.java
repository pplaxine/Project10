package com.philippe75.libraryWebapp.business.contract.handler;

import com.philippe75.libraryWebapp.business.contract.manager.BookDtoManager;
import com.philippe75.libraryWebapp.business.contract.manager.BorrowingDtoManager;
import com.philippe75.libraryWebapp.business.contract.manager.UserAccountDtoManager;

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
	 * Get access to {@link BookDtoManager}.
	 * 
	 * @return BookDtoManager
	 */
	BookDtoManager getBookDtoManager();
	
	
	/**
	 * Get access to {@link UserAccountDtoManager}.
	 * 
	 * @return UserAccountDtoManager
	 */
	UserAccountDtoManager getUserAccountDtoManager();
	
	/**
	 * Get access to {@link BorrowingDtoManager}.
	 * 
	 * @return BorrowingDtoManager
	 */
	BorrowingDtoManager getBorrowingDtoManager();
	
}
