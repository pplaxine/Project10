package com.philippe75.libraryWebapp.webapp.action;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction {
	
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
		if(!StringUtils.isAllEmpty(userMemberId,password)) {
			if(getUserMemberId().equals("admin") && getPassword().equals("test01") ) {
				result = ActionSupport.SUCCESS;
			}
		}
		
		
		return result;
	}
	
}
