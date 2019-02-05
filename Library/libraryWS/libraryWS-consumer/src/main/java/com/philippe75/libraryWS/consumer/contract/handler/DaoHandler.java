package com.philippe75.libraryWS.consumer.contract.handler;

import com.philippe75.libraryWS.consumer.contract.dao.BookDao;
import com.philippe75.libraryWS.consumer.contract.dao.BorrowingDao;
import com.philippe75.libraryWS.consumer.contract.dao.UserAccountDao;

/**
 * <b>Provides the different Dao object</b>
 * 
 * @see UserAccountDao
 * @see BookDao
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface DaoHandler {	
	
	/**
	 * Get access to {@link UserAccountDao}.
	 * 
	 * @return UserAccountDao
	 */
	UserAccountDao getUserAccountDao();
	
	/**
	 * Get access to {@link BookDao}.
	 * 
	 * @return BookDao
	 */
	BookDao getBookDao();
	
	/**
	 * Get access to {@link BorrowingDao}.
	 * 
	 * @return BorrowingDao
	 */
	BorrowingDao getBorrowingDao();
}
