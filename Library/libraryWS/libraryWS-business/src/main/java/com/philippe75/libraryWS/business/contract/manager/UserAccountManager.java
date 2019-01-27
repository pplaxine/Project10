package com.philippe75.libraryWS.business.contract.manager;

import com.philippe75.libraryWS.business.dto.UserAccountDto;

public interface UserAccountManager {

	UserAccountDto getUserAccountById(int userAccountId);
	
}
