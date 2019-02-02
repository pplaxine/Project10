package com.philippe75.libraryWebapp.business.impl.manager;

import javax.inject.Named;

import com.philippe75.libraryWebapp.business.contract.manager.UserAccountDtoManager;
import com.philippe75.libraryWebapp.stub.generated.authServ.AuthentificationException_Exception;
import com.philippe75.libraryWebapp.stub.generated.authServ.UserAccountDto;

/**
 * <b>Implements UserAccountManager Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("userAccountDtoManager")
public class UserAccountManagerImpl extends AbstractManagerServiceAccess implements UserAccountDtoManager{

	/**
	 * @param userMemeberId the member id of the user.
	 * @param password the password.
	 * @return UserAccountDto the Dto object of a {@link UserAccount} with the id required.  
	 * @throws AuthentificationException_Exception 
	 */
	@Override
	public UserAccountDto getUserAccount(String userMemberId, String password) throws AuthentificationException_Exception {
		return getAuthService().getUserAccount(userMemberId, password);
	}
	
	/**
	 * Method that saves user password if first login.
	 * 
	 * @param userMemberId the user Member id
	 * @param password user password to save 
	 * @return UserAccountDto 
	 */
	@Override
	public UserAccountDto saveUserAccountPw(String userMemberId, String password) throws AuthentificationException_Exception {
		
		
		
		return getAuthService().saveUserAccountPw(userMemberId, password);
	}

}
