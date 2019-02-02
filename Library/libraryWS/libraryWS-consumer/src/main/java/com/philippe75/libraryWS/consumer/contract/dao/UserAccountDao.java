package com.philippe75.libraryWS.consumer.contract.dao;

import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Contains all methods related to {@link UserAccount} requests</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public interface UserAccountDao {
	
	/**
	 * @param userAccountId the id of the user.
	 * 
	 * @return UserAccount the {@link UserAccount} with the member id required.  
	 */
	UserAccount getUserAccountByMemberId(String userMemberId) throws Exception;
	
	
	/**
	 * @param userAccountId the id of the user.
	 * @param password user password to save
	 * 
	 * @return UserAccount the {@link UserAccount} if password saved successfully.    
	 */
	UserAccount saveUserAccountPw(String userMemberId, String password) throws Exception;
	
}
