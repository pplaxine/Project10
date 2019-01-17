package com.philippe75.env.consumer.impl.handler;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.env.consumer.contract.dao.AddressDao;
import com.philippe75.env.consumer.contract.dao.UserTestDao;
import com.philippe75.env.consumer.contract.handler.DaoHandler;

@Named("daoHandler")
public class DaoHandlerImpl implements DaoHandler{
	
	@Inject
	private UserTestDao userTestDao;
	
	@Inject
	private AddressDao addressDao;

	
	public UserTestDao getUserTestDao() {
		return userTestDao;
	}

	public AddressDao getAddressDao() {
		return addressDao;
	}


	


}
