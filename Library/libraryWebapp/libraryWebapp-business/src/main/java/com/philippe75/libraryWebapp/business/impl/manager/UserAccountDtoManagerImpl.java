package com.philippe75.libraryWebapp.business.impl.manager;

import javax.inject.Named;

import com.philippe75.libraryWebapp.business.contract.manager.UserAccountDtoManager;
import com.philippe75.libraryWebapp.stub.generated.authServ.UserAccountDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.UserAccount;
import com.philippe75.libraryWebapp.stub.generated.authServ.LibraryServiceException_Exception;

/**
 * <b>Implements UserAccountManager Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("userAccountDtoManager")
public class UserAccountDtoManagerImpl extends AbstractManagerServiceAccess implements UserAccountDtoManager{

	/**
	 * @param userMemeberId the member id of the user.
	 * @param password the password.
	 * @return UserAccountDto the Dto object of a {@link UserAccount} with the id required.  
	 * @throws AuthentificationException_Exception 
	 */
	@Override
	public UserAccountDto getUserAccount(String userMemberId, String password) throws LibraryServiceException_Exception {
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
	public UserAccountDto saveUserAccountPw(String userMemberId, String password) throws LibraryServiceException_Exception {
		return getAuthService().saveUserAccountPw(userMemberId, password);
	}
	
	//---- UTILITY METHODS
	
	/**
	 * Transform model objects fetched from database to data transfer object.   
	 * 
	 * @param userAccount object fetched from the data layer. 
	 * @return UserAccountDto Dto object of {@link UserAccount}.  
	 */
	public static UserAccountDto userAccountModelToDto(UserAccount userAccount) {
		
		UserAccountDto uad = new UserAccountDto();
		
			uad.setUserMemberId(userAccount.getUserMemberId());
			uad.setAccess(userAccount.getAccess());
			uad.setFirstName(userAccount.getFirstName());
			uad.setSureName(userAccount.getSureName());
			uad.setEmail(userAccount.getEmail());
			uad.setPhoneNumber(userAccount.getPhoneNumber());
			uad.setBlockedAccount(userAccount.isBlockedAccount());
		
		return uad;
	}
	
	/**
	 * Transform dto objects to model object.   
	 * 
	 * @param userAccountDto  {@link UserAccountDto} . 
	 * @return userAccount {@link UserAccount}.  
	 */
	public static UserAccount userAccountDtoToModel(UserAccountDto userAccountDto) {
		
		UserAccount ua = new UserAccount();
		
			ua.setUserMemberId(userAccountDto.getUserMemberId());
			ua.setAccess(userAccountDto.getAccess());
			ua.setFirstName(userAccountDto.getFirstName());
			ua.setSureName(userAccountDto.getSureName());
			ua.setEmail(userAccountDto.getEmail());
			ua.setPhoneNumber(userAccountDto.getPhoneNumber());
			ua.setBlockedAccount(userAccountDto.isBlockedAccount());
		
		return ua;
	}

}
