package com.philippe75.libraryWebapp.business.impl.manager;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.philippe75.libraryWebapp.business.contract.manager.UserAccountDtoManager;
import com.philippe75.libraryWebapp.stub.generated.authServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.authServ.UserAccountDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:bootstrapContext.xml"})
public class UserAccountDtoManagerImplIntTest {
	
	
	@Inject
	private UserAccountDtoManager userAccountDtoManager;
	
	private static String userMemberId;
	private static String password;
	
	@BeforeClass
	public static void executeBeforeAll() {
		userMemberId = "UserTest";
		password = "test01";
	}
	
	//A password is registered for this user 
	@Test
	public void saveUserAccountPwIntTest() throws LibraryServiceException_Exception {
		UserAccountDto userAccount = userAccountDtoManager.saveUserAccountPw(userMemberId, password);
		//assertEquals(userAccount.getUserMemberId(), userMemberId);
	}
	
	
	//The account is accessed successfully 
	@Test
	public void getUserAccountIntTest() throws LibraryServiceException_Exception{
		UserAccountDto userAccount = userAccountDtoManager.getUserAccount(userMemberId, password);
		assertEquals(userAccount.getUserMemberId(), userMemberId);
	}
	

	
	
}
