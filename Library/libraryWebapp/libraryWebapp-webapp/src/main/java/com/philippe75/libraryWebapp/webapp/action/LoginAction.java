package com.philippe75.libraryWebapp.webapp.action;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.stub.generated.authServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.authServ.UserAccountDto;

public class LoginAction extends ActionSupport implements SessionAware, ServletRequestAware {
	
	//ATT
	@Inject
	private ManagerHandler managerHandler;
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	
	//in
	private String userMemberId;
	private String password;
	private String passwordConf;
	
	//GETTERS & SETTERS 
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
	
	public String getPasswordConf() {
		return password;
	}
	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	// METHODS
	
	public String doFirstLogin() {
		String result = ActionSupport.INPUT;
		
		if(!StringUtils.isAllEmpty(userMemberId,password,passwordConf)) {
			try {
				
				if(userMemberId.trim().equals("")) {
					this.addFieldError("userMemberId", getText("Vous devez entrer vote nom d'utilisateur"));
				}else if(password.trim().equals("")) {
					this.addFieldError("password", getText("Vous devez entrer un mot de passe"));
				}else if(!password.equals(passwordConf)) {
					this.addFieldError("passwordConf", getText("mot de passe pas le même"));
				}else {
					UserAccountDto uad = managerHandler.getUserAccountDtoManager().saveUserAccountPw(userMemberId, password);
					this.addActionMessage("Votre compte à été activé avec succès, vous pouvez à présent vous connecter.");
					result = ActionSupport.SUCCESS;
				}
				
			} catch (LibraryServiceException_Exception aEx) {
				if((aEx.getMessage()).equals("NoResultException")) {
					this.addFieldError("userMemberId", getText("login.failure.login"));
				}else if ((aEx.getMessage()).equals("ExistingPasswordException") ) {
					this.addActionError("Votre compte possède déjà un mot de passe.");
				}else {
					this.addActionError(getText("Une erreur inatendue est survenue. Veuillez re-essayer plus tard."));
				}
			}
		}
		
		return result;
	}
	
	public String doLogin() {
		String result = ActionSupport.INPUT;
		
		if(!StringUtils.isAllEmpty(userMemberId,password)) {
			try {
				UserAccountDto uad = managerHandler.getUserAccountDtoManager().getUserAccount(userMemberId, password);
				this.session.put("user", uad);
				result = ActionSupport.SUCCESS;
				
			} catch (LibraryServiceException_Exception e) {
	
				this.addActionError(getText("login.failure"));
				if((e.getMessage()).equals("NoResultException")) {
					this.addFieldError("userMemberId", getText("login.failure.login"));
				}else if ((e.getMessage()).equals("InvalidPasswordException")){
					this.addFieldError("password", getText("login.failure.password"));
				}else {
					this.addActionError(getText("Une erreur inatendue est survenue. Veuillez re-essayer plus tard."));
				}
			}
		}
		return result;
	}
	
	public String doLogout() {
		request.getSession().invalidate();
		return ActionSupport.SUCCESS;
	}
}
