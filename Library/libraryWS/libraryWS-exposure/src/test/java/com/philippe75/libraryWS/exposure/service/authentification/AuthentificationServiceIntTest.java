package com.philippe75.libraryWS.exposure.service.authentification;

import static org.junit.Assert.assertEquals;

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
	
	@BeforeClass
	public static void executeBeforeAll() {
		userMemberId = "JTille";
		password = "test01";
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
}
