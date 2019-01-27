package com.philippe75.libraryWS.consumer.contract.dao;

import com.philippe75.libraryWS.model.user.UserAccount;


public interface UserAccountDao {

	UserAccount getUserAccountById(int userAccountId);
	
}
