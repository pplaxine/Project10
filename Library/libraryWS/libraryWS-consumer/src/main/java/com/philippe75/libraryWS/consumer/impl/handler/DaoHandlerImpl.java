package com.philippe75.libraryWS.consumer.impl.handler;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.libraryWS.consumer.contract.dao.BookDao;
import com.philippe75.libraryWS.consumer.contract.dao.UserAccountDao;
import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;
import com.philippe75.libraryWS.consumer.impl.dao.AbstractDao;

/**
 * <b>Implementation of DaoHandler interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("daoHandler")
public class DaoHandlerImpl extends AbstractDao implements DaoHandler{
	
	/**
	 * injection of {@link UserAccountDao}
	 */
	@Inject
	private UserAccountDao userAccountDao;
	
	/**
	 * injection of {@link BookDao}
	 */
	@Inject
	private BookDao bookDao;

	public UserAccountDao getUserAccountDao() {
		return userAccountDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}
	
}
