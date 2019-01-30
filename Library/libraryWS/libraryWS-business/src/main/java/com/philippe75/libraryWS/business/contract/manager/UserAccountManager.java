package com.philippe75.libraryWS.business.contract.manager;

import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.model.exception.AuthentificationException;
import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Contains all methods related to {@link UserAccount} requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface UserAccountManager  {
	
	/**
	 * @param userAccountId the id of the user.
	 * @return UserAccountDto the Dto object of a {@link UserAccount} with the id required.  
	 */
	UserAccountDto getUserAccountByMemberId(String userMemberId, String password) throws AuthentificationException;
	
}
