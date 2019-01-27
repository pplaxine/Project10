package com.philippe75.libraryWS.business.impl.manager;

import javax.inject.Named;

import com.philippe75.libraryWS.business.contract.manager.UserAccountManager;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.model.user.UserAccount;

@Named("userAccountManager")
public class UserAccountManagerImpl extends AbstractManager implements UserAccountManager{
	
	@Override
	public UserAccountDto getUserAccountById(int userAccountId) {
		
		UserAccount ua = null;
		
		if(getDaoHandler().getUserAccountDao().getUserAccountById(userAccountId)!=null){
			ua = (UserAccount)getDaoHandler().getUserAccountDao().getUserAccountById(userAccountId);
		}else {
			//Log
			System.out.println("");
		}
		
		return userAccountModelToDto(ua);
	}

	
	private UserAccountDto userAccountModelToDto(UserAccount userAccount) {
		
		UserAccountDto uad = new UserAccountDto();
		
			uad.setId(userAccount.getId());
			uad.setUserMemberId(userAccount.getUserMemberId());
			uad.setAccess(userAccount.getAccess());
			uad.setFirstName(userAccount.getFirstName());
			uad.setSureName(userAccount.getSureName());
			uad.setAddress(userAccount.getAddress());
			uad.setEmail(userAccount.getEmail());
			uad.setPhoneNumber(userAccount.getPhoneNumber());
			uad.setBlockedAccount(userAccount.isBlockedAccount());
			uad.setListBorrowing(userAccount.getListBorrowing());			
		
		return uad;
	}


}
