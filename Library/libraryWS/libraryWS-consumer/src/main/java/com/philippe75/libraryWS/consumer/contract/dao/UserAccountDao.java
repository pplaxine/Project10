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
	 * Retrieve the User with its User member id. 
	 * 
	 * @param userMemberId the member id of the user.
	 * 
	 * @return UserAccount the {@link UserAccount} with the member id required.  
	 */
	UserAccount getUserAccountByMemberId(String userMemberId) throws Exception;
	
	
	/**
	 * Create a password for users First connection. 
	 * 
	 * @param userMemberId the member id of the user.
	 * @param password user password to save
	 * 
	 * @return UserAccount the {@link UserAccount} if password saved successfully.    
	 */
	UserAccount saveUserAccountPw(String userMemberId, String password) throws Exception;
	
	/**
	 * Update users mail reminder status.  
	 * 
	 * @param userMemberId the member id of the user.
	 * @param mailReminder the new state of mail reminder : true mail sent / false stop sending mail. 
	 * 
	 */
	void updateMailReminder(String userMemberId, boolean mailReminder) throws Exception; 
}
