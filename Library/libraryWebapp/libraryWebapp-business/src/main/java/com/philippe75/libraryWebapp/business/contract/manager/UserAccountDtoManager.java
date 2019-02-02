package com.philippe75.libraryWebapp.business.contract.manager;

import javax.inject.Named;

import com.philippe75.libraryWebapp.stub.generated.authServ.AuthentificationException_Exception;
import com.philippe75.libraryWebapp.stub.generated.authServ.UserAccountDto;
/**
 * <b>Contains all methods related to {@link UserAccount} requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("userAccountManager")
public interface UserAccountDtoManager {
	
	/**
	 * @param userMemeberId the member id of the user.
	 * @param password the password.
	 * @return UserAccountDto the Dto object of a {@link UserAccount} with the id required.  
	 */
	UserAccountDto getUserAccount(String userMemberId, String password) throws AuthentificationException_Exception;

	/**
	 * Method that saves user password if first login.
	 * 
	 * @param userMemberId the user Member id
	 * @param password user password to save 
	 * @return UserAccountDto the Dto object of a {@link UserAccount} with the id required.  
	 */
	UserAccountDto saveUserAccountPw(String userMemberId, String password) throws AuthentificationException_Exception;
}
