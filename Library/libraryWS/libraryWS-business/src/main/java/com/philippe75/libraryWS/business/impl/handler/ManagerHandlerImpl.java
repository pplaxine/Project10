package com.philippe75.libraryWS.business.impl.handler;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.contract.manager.BookBookingManager;
import com.philippe75.libraryWS.business.contract.manager.BookManager;
import com.philippe75.libraryWS.business.contract.manager.BorrowingManager;
import com.philippe75.libraryWS.business.contract.manager.UserAccountManager;
import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;

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
	private static UserAccountManager userAccountManager;
	
	/**
	 * injection of {@link BookManager}
	 */
	private static BookManager bookManager;
	
	/**
	 * injection of {@link BorrowingManager}
	 */
	private static BorrowingManager borrowingManager;
	
	/**
	 * injection of {@link BookBookingManager}
	 */
	private static BookBookingManager bookBookingManager;
	
	//Unit Test Mock access
	public static void configure(UserAccountManager pUserAccountManager) {
		userAccountManager = pUserAccountManager;
	}
	//Unit Test Mock access
	public static void configure(BookManager pBookManager) {
		bookManager = pBookManager;
	}
	//Unit Test Mock access
	public static void configure(BorrowingManager pBorrowingManager) {
		borrowingManager = pBorrowingManager;
	}	
	//Unit Test Mock access
	public static void configure(BookBookingManager pBookBookingManager) {
		bookBookingManager = pBookBookingManager;
	}	

	@Override
	public UserAccountManager getUserAccountManager() {
		return userAccountManager;
	}

	@Override
	public BookManager getBookManager() {
		return bookManager;
	}

	public BorrowingManager getBorrowingManager() {
		return borrowingManager;
	}

	@Override
	public BookBookingManager getBookBookingManager() {
		return bookBookingManager;
	}
	
	@Inject
	public void setBookManager(BookManager bookManager) {
		ManagerHandlerImpl.bookManager = bookManager;
	}
	@Inject
	public void setUserAccountManager(UserAccountManager userAccountManager) {
		ManagerHandlerImpl.userAccountManager = userAccountManager;
	}
	@Inject
	public void setBorrowingManager(BorrowingManager borrowingManager) {
		this.borrowingManager = borrowingManager;
	}
	@Inject
	public void setBookBookingManager(BookBookingManager bookBookingManager) {
		this.bookBookingManager = bookBookingManager;
	}
	
	
	
	
	
}
