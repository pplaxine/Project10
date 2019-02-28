package com.philippe75.libraryWebapp.webapp.action;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;


public class MainAction extends ActionSupport {
	
	@Inject
	ManagerHandler managerHandler;
	
	//income 
	
	//outcome

	//G&S
	
	//METHODS

	public String doMain() {
		return ActionSupport.SUCCESS; 
	}
}
