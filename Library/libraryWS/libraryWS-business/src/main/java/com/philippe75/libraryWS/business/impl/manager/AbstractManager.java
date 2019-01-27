package com.philippe75.libraryWS.business.impl.manager;

import javax.inject.Inject;

import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;

public abstract class AbstractManager {
	@Inject
	DaoHandler daoHandler;

	public DaoHandler getDaoHandler() {
		return daoHandler;
	}

}
