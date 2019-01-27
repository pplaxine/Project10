package com.philippe75.libraryWS.consumer.impl.handler;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.libraryWS.consumer.contract.dao.BookDao;
import com.philippe75.libraryWS.consumer.contract.dao.UserAccountDao;
import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;
import com.philippe75.libraryWS.consumer.impl.dao.AbstractDao;

@Named("daoHandler")
public class DaoHandlerImpl extends AbstractDao implements DaoHandler{
	
	@Inject
	private UserAccountDao userAccountDao;
	
	@Inject
	private BookDao bookDao;

	public UserAccountDao getUserAccountDao() {
		return userAccountDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}
	
}
