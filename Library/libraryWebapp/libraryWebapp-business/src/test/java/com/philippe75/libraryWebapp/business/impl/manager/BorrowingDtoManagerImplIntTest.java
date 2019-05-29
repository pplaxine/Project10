package com.philippe75.libraryWebapp.business.impl.manager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.philippe75.libraryWebapp.business.contract.manager.BorrowingDtoManager;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookBookingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:bootstrapContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BorrowingDtoManagerImplIntTest {
	
	@Inject
	BorrowingDtoManager borrowingDtoManager;
	
	private static String userMemberId;
	private static int borrowingId, numberOfWeek;
	private BorrowingDto extendedBorrowing;
	private BookBookingDto bookBookingDto;
	
	 
	
	@BeforeClass
	public static void executeBeforeAll() throws ParseException {
		userMemberId = "MSegaux";
		borrowingId = 2; 
		numberOfWeek = 4;
	}

	//All the borrowing for a user are retrieved
	@Test
	public void intTest01getAllBorrowingForUser() throws LibraryServiceException_Exception{
		
		List<BorrowingDto> lb = borrowingDtoManager.getAllBorrowingForUser(userMemberId);
		assertTrue( "The list should contain 1 elements ",lb.size() == 3 );
	}
	
	//All the late borrowings are retrieved 
	@Test
	public void intTest02extendBorrowing() throws LibraryServiceException_Exception {
		
		//extend the borrowing   
		borrowingDtoManager.extendBorrowing(borrowingId, numberOfWeek);
		//retrieve extended borrowing
		List<BorrowingDto> lb = borrowingDtoManager.getAllBorrowingForUser(userMemberId);
		for (BorrowingDto bd : lb) {
			if(bd.getId() == borrowingId) {
				assertNotNull("The extension hasn't been taken to account" , bd.getSecondSupposedEndDate());
			}
		}
		
	}
	
	//All a new booking is created 
	@Test
	public void intTest03createBooking() throws LibraryServiceException_Exception {
		
		
		
		
		
		
	}
	
	
	

}
