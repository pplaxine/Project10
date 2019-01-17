package com.philippe75.env.consumer.impl.dao;

import javax.inject.Inject;

import com.philippe75.env.consumer.contract.handler.DaoHandler;

public abstract class AbstractDaoImpl {

	@Inject
	DaoHandler daoHandler;

	public DaoHandler getDaoHandler() {
		return daoHandler;
	}
	
}
