package com.philippe75.libraryWS.business.impl.manager;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.business.impl.handler.ManagerHandlerImpl;
import com.philippe75.libraryWS.consumer.impl.dao.UserAccountDaoImpl;
import com.philippe75.libraryWS.consumer.impl.handler.DaoHandlerImpl;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;
import com.philippe75.libraryWS.model.user.UserAccount;
import com.philippe75.libraryWS.model.user.UserAddress;

@RunWith(MockitoJUnitRunner.class)
public class UserAccountManagerImplUnitTest {
	
	@InjectMocks
	private ManagerHandlerImpl managerHandler;
	@InjectMocks
	private UserAccountManagerImpl userAccountManager;
	@InjectMocks
	private DaoHandlerImpl daoHandler;
	@Mock
	UserAccountDaoImpl userAccountDao;
	@Mock
	private PasswordEncoder bcPasswordEncodeur;
	
	
	
	private UserAccount userAccount;
	
    @Before
    public void executeBeforeEach() {
    	//set the mock in abstract class
    	AbstractManager.configure(daoHandler);
    	//set the Mock in ManagerHandler class
    	ManagerHandlerImpl.configure(userAccountManager);
    	
    	//STUB UserAccount
    	userAccount = new UserAccount();
    	userAccount.setId(4);
    	userAccount.setUserMemberId("8");
    	userAccount.setFirstName("Jean");
    	userAccount.setSureName("Tille");
    	userAccount.setAddress(new UserAddress());
    	userAccount.setEmail("orange@orange.fr");
    	userAccount.setPhoneNumber(0606060606);
    	userAccount.setBlockedAccount(false);
    }
	
    //UserAccountDto created from UserAccount contains all the values
    @Test
    public void userAccountModelToDtoTest() {
    	UserAccountDto userAccountDto = userAccountManager.userAccountModelToDto(userAccount);
    	assertEquals("UserAccountDto : UserMemberId ",userAccountDto.getUserMemberId(), userAccount.getUserMemberId());
    	assertEquals("UserAccountDto : FirstName ",userAccountDto.getFirstName(), userAccount.getFirstName());
    	assertEquals("UserAccountDto : SureName ",userAccountDto.getSureName(), userAccount.getSureName());
    	assertEquals("UserAccountDto : Adresse ",userAccountDto.getAddress(), userAccount.getAddress());
    	assertEquals("UserAccountDto : Email ",userAccountDto.getEmail(), userAccount.getEmail());
    	assertEquals("UserAccountDto : BlockedAccount ",userAccountDto.isBlockedAccount(), userAccount.isBlockedAccount());
    	
    }
    
    //UserAccountDto is created
    @Test
    public void getUserAccountByIdTest() throws Exception {
    
    	String password ="plop";
    	//---- mock setup
    	when(daoHandler.getUserAccountDao().getUserAccountByMemberId(userAccount.getUserMemberId())).thenReturn(userAccount);
    	when(bcPasswordEncodeur.matches(password, userAccount.getPassword())).thenReturn(true);
    	//---------------
    	
    	UserAccountDto userAccountDto = managerHandler.getUserAccountManager().getUserAccountByMemberId(userAccount.getUserMemberId(), password);
    	assertEquals("UserAccountDto not create as expected", userAccountDto.getFirstName() , userAccount.getFirstName());
    }
    
    //UserAccountDto is created
    @Test(expected=LibraryServiceException.class)
    public void getUserAccountByIdInvalidePasswordExceptionTest() throws Exception {
    
    	String password ="plop";
    	//---- mock setup
    	when(daoHandler.getUserAccountDao().getUserAccountByMemberId(userAccount.getUserMemberId())).thenReturn(userAccount);
    	when(bcPasswordEncodeur.matches(password, userAccount.getPassword())).thenReturn(false);
    	//---------------
    	
    	UserAccountDto userAccountDto = managerHandler.getUserAccountManager().getUserAccountByMemberId(userAccount.getUserMemberId(), password);
    	assertEquals("UserAccountDto not create as expected", userAccountDto.getFirstName() , userAccount.getFirstName());
    }
    
    @Test
    public void  saveUserAccountPwTest() throws Exception {
    	String password ="plopEncore";
    	//---- mock setup
    	userAccount.setPassword(null);
    	when(daoHandler.getUserAccountDao().getUserAccountByMemberId(userAccount.getUserMemberId())).thenReturn(userAccount);
    	when(bcPasswordEncodeur.encode(password)).thenReturn(password);
    	when(daoHandler.getUserAccountDao().saveUserAccountPw(userAccount.getUserMemberId(), password)).thenReturn(userAccount);
    	//---------------
    	managerHandler.getUserAccountManager().saveUserAccountPw(userAccount.getUserMemberId(), password);
    }
    
    @Test(expected=LibraryServiceException.class)
    public void  saveUserAccountPwShortPasswordExceptionTest() throws Exception {
    	String password ="plo";
    	//---- mock setup
    	userAccount.setPassword(password);
    	//---------------
    	managerHandler.getUserAccountManager().saveUserAccountPw(userAccount.getUserMemberId(), password);
    }
    
    @Test(expected=LibraryServiceException.class)
    public void  saveUserAccountPwExistingPasswordExceptionTest() throws Exception {
    	String password ="plopEncore";
    	//---- mock setup
    	userAccount.setPassword(password);
    	when(daoHandler.getUserAccountDao().getUserAccountByMemberId(userAccount.getUserMemberId())).thenReturn(userAccount);
    	//---------------
    	managerHandler.getUserAccountManager().saveUserAccountPw(userAccount.getUserMemberId(), password);
    }
	
}
