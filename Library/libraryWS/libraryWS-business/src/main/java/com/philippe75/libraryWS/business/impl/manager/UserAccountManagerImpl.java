package com.philippe75.libraryWS.business.impl.manager;


import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.philippe75.libraryWS.business.contract.manager.UserAccountManager;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;
import com.philippe75.libraryWS.model.user.UserAccount;


/**
 * <b>Implements UserAccountManager Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("userAccountManager")
public class UserAccountManagerImpl extends AbstractManager implements UserAccountManager{
	
	@Inject
	private PasswordEncoder bcPasswordEncodeur;
	
	/**
	 * @param userMemeberId the member id of the user.
	 * @param password the password.
	 * @return UserAccountDto the Dto object of a {@link UserAccount} with the id required.  
	 */
	@Override
	public UserAccountDto getUserAccountByMemberId(String userMemberId, String password) throws LibraryServiceException {
		
		UserAccount ua;
		
		try {
			ua = getDaoHandler().getUserAccountDao().getUserAccountByMemberId(userMemberId);
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
		} 
		
		if(!(userMemberId.equals(ua.getUserMemberId()) && bcPasswordEncodeur.matches(password, ua.getPassword()))) {
			throw new LibraryServiceException("InvalidPasswordException", libraryServiceFaultFactory("1235", "The password is incorrect ..."));
		}
		return userAccountModelToDto(ua);
		
	}
	


	/**
	 * Method that saves user password if first login.
	 * 
	 * @param userMemberId the user Member id
	 * @param password user password to save 
	 * 
	 * @return UserAccountDto the Dto object of a {@link UserAccount} with the id required.  
	 */
	@Override
	public UserAccountDto saveUserAccountPw(String userMemberId, String password) throws LibraryServiceException {
		
		if(password.length() < 4) {
			System.out.println("hello from password");
			throw new LibraryServiceException("ShortPasswordException", libraryServiceFaultFactory("1238", "Your password must be at least 4 character long."));
			
		}else {
			
			UserAccount ua;
			
			try {
				ua = getDaoHandler().getUserAccountDao().getUserAccountByMemberId(userMemberId);
				if(ua.getPassword() == null) {
					//Password BCrytpeEncoding
					String pwEncoded = bcPasswordEncodeur.encode(password);
					ua = daoHandler.getUserAccountDao().saveUserAccountPw(userMemberId, pwEncoded);
					return userAccountModelToDto(ua);
				}
				
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
			} 
			
			throw new LibraryServiceException("ExistingPasswordException", libraryServiceFaultFactory("1236", "A password already exists for this member id."));
		}
		
	}

	//UTILITY METHODS 
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
