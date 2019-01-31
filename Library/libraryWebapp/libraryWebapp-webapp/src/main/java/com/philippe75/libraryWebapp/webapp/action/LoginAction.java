package com.philippe75.libraryWebapp.webapp.action;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.stub.generated.authServ.AuthentificationException_Exception;
import com.philippe75.libraryWebapp.stub.generated.authServ.AuthentificationService;
import com.philippe75.libraryWebapp.stub.generated.authServ.AuthentificationServiceImplService;
import com.philippe75.libraryWebapp.stub.generated.authServ.UserAccountDto;

public class LoginAction {
	
	//entry
	private String userMemberId;
	private String password;
	
	//G&S
	public String getUserMemberId() {
		return userMemberId;
	}
	public void setUserMemberId(String userMemberId) {
		this.userMemberId = userMemberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String doLogin() {
		String result = ActionSupport.INPUT;
		AuthentificationService authService = new AuthentificationServiceImplService().getAuthentificationServiceImplPort();
		
		if(!StringUtils.isAllEmpty(userMemberId,password)) {
		
		 try {
			UserAccountDto uad = authService.getUserAccount(userMemberId, password);
			System.out.println(uad.getEmail());
			result = ActionSupport.SUCCESS;
			
		} catch (AuthentificationException_Exception e) {
			System.out.println("Super " + e.getFaultInfo().getFault().getFaultMessage());
			}
			
			
		}
		
		
		return result;
	}
	
}
