package com.philippe75.env.business.impl.handler;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.env.business.contract.handler.ManagerHandler;
import com.philippe75.env.business.contract.manager.UserTestManager;

@Named("manageHandler")
public class ManagerHandlerImpl implements ManagerHandler{
	
	@Inject
	private UserTestManager userTestManager;
	
	public UserTestManager getUserTestManager() {
		return userTestManager;
	}
	
	
}
