package com.philippe75.env.business.impl.manager;

import javax.inject.Inject;

import com.philippe75.env.consumer.contract.handler.DaoHandler;

public class AbstractManagerImpl {

	@Inject
	private DaoHandler daoHandler;

	protected DaoHandler getDaoHandler() {
		return daoHandler;
	}
	
	public void setDaoHandler(DaoHandler daoHandler) {
		this.daoHandler = daoHandler;
	}

}
