package com.philippe75.libraryWS.business.impl.manager;


import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.philippe75.libraryWS.business.contract.manager.UserAccountManager;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.model.book.Borrowing;
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
			throw new LibraryServiceException("ShortPasswordException", libraryServiceFaultFactory("1238", "Your password must be at least 4 character long."));
			
		}else {
			
			UserAccount ua;
			
			try {
				ua = getDaoHandler().getUserAccountDao().getUserAccountByMemberId(userMemberId);
				if(ua.getPassword() == null) {
					//Password BCrytpeEncoding
					String pwEncoded = bcPasswordEncodeur.encode(password);
					ua = getDaoHandler().getUserAccountDao().saveUserAccountPw(userMemberId, pwEncoded);
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

	//---- UTILITY METHODS ----------------------------------------------------------------
	
	/**
	 * Transform model objects fetched from database to data transfer object.   
	 * 
	 * @param userAccount object fetched from the data layer. 
	 * @return UserAccountDto Dto object of {@link UserAccount}.  
	 */
	public static UserAccountDto userAccountModelToDto(UserAccount userAccount) {
		
		UserAccountDto uad = new UserAccountDto();
		if(userAccount != null) {
			if(userAccount.getUserMemberId() != null) {
				uad.setUserMemberId(userAccount.getUserMemberId());
			}
			if(userAccount.getAccess()!= null) {
				uad.setAccess(userAccount.getAccess());
			}
			if(userAccount.getFirstName() != null) {
				uad.setFirstName(userAccount.getFirstName());
			}
			if(userAccount.getSureName() != null) {
				uad.setSureName(userAccount.getSureName());
			}
			if(userAccount.getAddress() != null) {
				uad.setAddress(userAccount.getAddress());
			}
			if(userAccount.getEmail() != null) {
				uad.setEmail(userAccount.getEmail());
			}
			if(userAccount.getPhoneNumber() != 0) {
				uad.setPhoneNumber(userAccount.getPhoneNumber());
			}
			//can't be null
			uad.setBlockedAccount(userAccount.isBlockedAccount());
			
		}
		
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
		if(userAccountDto != null) {
			if(userAccountDto.getUserMemberId() != null) {
				ua.setUserMemberId(userAccountDto.getUserMemberId());
			}
			if(userAccountDto.getAccess() != null) {
				ua.setAccess(userAccountDto.getAccess());
			}
			if(userAccountDto.getFirstName() != null) {
				ua.setFirstName(userAccountDto.getFirstName());
			}
			if(userAccountDto.getSureName() != null) {
				ua.setSureName(userAccountDto.getSureName());
			}
			if(userAccountDto.getAddress() != null) {
				ua.setAddress(userAccountDto.getAddress());
			}
			if(userAccountDto.getEmail() != null) {
				ua.setEmail(userAccountDto.getEmail());
			}
			if(userAccountDto.getPhoneNumber() != 0) {
				ua.setPhoneNumber(userAccountDto.getPhoneNumber());
			}
			//can't be null
			ua.setBlockedAccount(userAccountDto.isBlockedAccount());
			
		}
			
		
		return ua;
	}
	


}
