package com.philippe75.libraryWS.exposure.service.authentification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.exposure.bootstrap.SpringConfiguration;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class AuthentificationServiceIntTest {

	@Inject
	private ManagerHandler managerHandler;

	private static String userMemberId;
	private static String password;
	private static UserAccountDto uad;
	
	@BeforeClass
	public static void executeBeforeAll() {
		userMemberId = "UserTest2";
		password = "test01";
		
		//userAccountDto STUB 
		uad = new UserAccountDto();
		uad.setUserMemberId(userMemberId);
		uad.setMailReminder(false);
	}
	
	//A password is registered for this user 
	@Test
	public void saveUserAccountPwIntTest() throws LibraryServiceException {
		UserAccountDto userAccount = managerHandler.getUserAccountManager().saveUserAccountPw(userMemberId, password);
		assertEquals(userAccount.getUserMemberId(), userMemberId);
	}
	
	
	//The account is accessed successfully 
	@Test
	public void getUserAccountIntTest() throws LibraryServiceException {
		UserAccountDto userAccount = managerHandler.getUserAccountManager().getUserAccountByMemberId(userMemberId, password);
		assertEquals(userAccount.getUserMemberId(), userMemberId);
	}
	
	//The mail reminder status is returned 
	@Test
	public void getUserMailReminderStatusIntTest() throws LibraryServiceException, Exception {
		assertTrue("The result should be true", managerHandler.getUserAccountManager().getUserMailReminderStatus("JTille").isMailReminder() == true);
		
		
	}
	
	//The mail reminder status for user is modified  
	@Test
	public void updateMailReminderIntTest() throws Exception {
		
		
		UserAccountDto userAccount = managerHandler.getUserAccountManager().getUserAccountByMemberId(userMemberId, password);
		managerHandler.getUserAccountManager().updateMailReminder(uad);
		UserAccountDto userAccount2 = managerHandler.getUserAccountManager().getUserAccountByMemberId(userMemberId, password);
		
		assertTrue("The status of mail reminder should be true", userAccount.isMailReminder() == true);
		assertTrue("The status of mail reminder should be false", userAccount2.isMailReminder() == false);
	}
	
}
