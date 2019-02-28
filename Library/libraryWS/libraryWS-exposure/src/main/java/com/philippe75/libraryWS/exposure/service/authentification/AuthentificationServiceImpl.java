package com.philippe75.libraryWS.exposure.service.authentification;

import javax.inject.Inject;
import javax.jws.WebService;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

/**
 * <b>Authentification service end point Class.</b>
 * 
 * <p>
 * 	  Service related to authentification of users.
 * </p>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@WebService(endpointInterface="com.philippe75.libraryWS.exposure.service.authentification.AuthentificationService")
public class AuthentificationServiceImpl extends SpringBeanAutowiringSupport implements AuthentificationService {

	/**
	 * Access to business layer.
	 * 
	 * @see ManagerHandler
	 */
	@Inject
	private ManagerHandler managerHandler;
	
	/**
	 * Method that gets userAccount if the userMemberId and password match.
	 * 
	 * @param userMemberId the user Member id 
	 * @param password user password
	 * @return UserAccountDto 
	 * @throws LibraryServiceException 
	 */
	@Override
	public UserAccountDto getUserAccount(String userMemberId, String password) throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return (UserAccountDto)managerHandler.getUserAccountManager().getUserAccountByMemberId(userMemberId, password);

	}

	/**
	 * Method that saves user password if first login.
	 * 
	 * @param userMemberId the user Member id
	 * @param password user password to save 
	 * @return UserAccountDto 
	 */
	@Override
	public UserAccountDto saveUserAccountPw(String userMemberId, String password) throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return (UserAccountDto) managerHandler.getUserAccountManager().saveUserAccountPw(userMemberId, password);
	}
	
	

}
