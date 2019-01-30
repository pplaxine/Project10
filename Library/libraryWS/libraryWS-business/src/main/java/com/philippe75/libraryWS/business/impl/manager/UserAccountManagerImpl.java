package com.philippe75.libraryWS.business.impl.manager;


import javax.inject.Named;

import com.philippe75.libraryWS.business.contract.manager.UserAccountManager;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.model.exception.AuthentificationException;
import com.philippe75.libraryWS.model.exception.DataBaseException;
import com.philippe75.libraryWS.model.exception.NotFoundException;
import com.philippe75.libraryWS.model.exception.fault.AuthentificationFault;
import com.philippe75.libraryWS.model.user.UserAccount;


/**
 * <b>Implements UserAccountManager Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("userAccountManager")
public class UserAccountManagerImpl extends AbstractManager implements UserAccountManager{
	
	/**
	 * Get the {@link UserAccountDto} with name required.
	 * 
	 * @param userAccountId the id of the user
	 * @return UserAccountDto Dto object of {@link UserAccount} with id required.
	 */
	@Override
	public UserAccountDto getUserAccountByMemberId(String userMemberId, String password) throws AuthentificationException {
		
		UserAccount ua;
		AuthentificationFault af;
		
		try {
			ua = getDaoHandler().getUserAccountDao().getUserAccountByMemberId(userMemberId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			af = new AuthentificationFault();
			af.setFaultCode("1234");
			af.setFaultMessage(e.getMessage());
			throw new AuthentificationException("An error happened in database", af);
		}
		
		if(!(userMemberId.equals(ua.getUserMemberId()) && password.equals(ua.getPassword()))) {

			af = new AuthentificationFault();
			af.setFaultCode("1235");
			af.setFaultMessage("The password is incorrect ...");
			throw new AuthentificationException("Invalid Password", af);
		}
		return userAccountModelToDto(ua);
		
	}

	/**
	 * Transform model objects fetched from database to data transfer object.   
	 * 
	 * @param userAccount object fetched from the data layer. 
	 * @return UserAccountDto Dto object of {@link UserAccount}.  
	 */
	private UserAccountDto userAccountModelToDto(UserAccount userAccount) {
		
		UserAccountDto uad = new UserAccountDto();
		
			uad.setUserMemberId(userAccount.getUserMemberId());
			uad.setAccess(userAccount.getAccess());
			uad.setFirstName(userAccount.getFirstName());
			uad.setSureName(userAccount.getSureName());
			uad.setAddress(userAccount.getAddress());
			uad.setEmail(userAccount.getEmail());
			uad.setPhoneNumber(userAccount.getPhoneNumber());
			uad.setBlockedAccount(userAccount.isBlockedAccount());
		
		return uad;
	}


}
