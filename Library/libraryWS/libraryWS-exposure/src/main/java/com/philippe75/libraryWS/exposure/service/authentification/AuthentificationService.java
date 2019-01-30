package com.philippe75.libraryWS.exposure.service.authentification;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.consumer.contract.dao.UserAccountDao;
import com.philippe75.libraryWS.model.exception.AuthentificationException;

/**
 * <b>Authentification service end point Interface.</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface AuthentificationService {

	/**
	 * Method that gets userAccount if the userMemberId and password match.
	 * 
	 * @param userMemberId the user Member id 
	 * @param password user password
	 * @return UserAccountDto 
	 */
	@WebMethod
	public UserAccountDto getUserAccount(String userMemberId, String password) throws AuthentificationException;
}
