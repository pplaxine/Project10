package com.philippe75.libraryWS.exposure.service.authentification;

import javax.inject.Inject;
import javax.jws.WebService;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.consumer.contract.dao.UserAccountDao;
import com.philippe75.libraryWS.model.exception.AuthentificationException;

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
	 * @throws AuthentificationException 
	 */
	@Override
	public UserAccountDto getUserAccount(String userMemberId, String password) throws AuthentificationException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		UserAccountDto uad = (UserAccountDto)managerHandler.getUserAccountManager().getUserAccountByMemberId(userMemberId, password);
		
		return uad;
	}

}
