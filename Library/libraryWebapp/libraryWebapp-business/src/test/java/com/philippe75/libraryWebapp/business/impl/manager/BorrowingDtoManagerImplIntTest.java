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
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.Exception_Exception;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:bootstrapContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BorrowingDtoManagerImplIntTest {
	
	@Inject
	BorrowingDtoManager borrowingDtoManager;
	
	private static String userMemberId, bookFullName;
	private static int borrowingId, numberOfWeek;
	private static BookDto bookDto;
	
	@BeforeClass
	public static void executeBeforeAll() throws ParseException {
		userMemberId = "MSegaux";
		borrowingId = 2; 
		numberOfWeek = 4;
		bookDto = new BookDto();
		bookDto.setName("Roméo et Juliette");
		bookDto.setAuthor("William Shakespeare");
		bookFullName = "Roméo et Juliette - William Shakespeare";
	}

	//All the borrowing for a user are retrieved
	@Test
	public void intTest01getAllBorrowingForUser() throws LibraryServiceException_Exception{
		
		List<BorrowingDto> lb = borrowingDtoManager.getAllBorrowingForUser(userMemberId);
		assertTrue( "The list should contain 1 elements ",lb.size() == 3 );
	}
	
	//All the borrowing for a book are retrieved
	@Test
	public void intTest02getAllBorrowingForBook() throws LibraryServiceException_Exception{
		
		List<BorrowingDto> lb = borrowingDtoManager.getAllBorrowingForBook(bookFullName);
		assertTrue( "The list should contain 1 elements ",lb.size() == 5 );
	}
	
	//Next borrowing for the book is retrieved
	@Test 
	public void intTest03getNextBorrowingToComeToEnd() throws LibraryServiceException_Exception {
		
		
		BorrowingDto borrowingDto = borrowingDtoManager.getNextBorrowingToComeToEnd(bookFullName);
		
		assertTrue("The borrowing id should be 2", borrowingDto.getId() == 2 );
	}
	
	//All the late borrowings are retrieved 
	@Test
	public void intTest04extendBorrowing() throws LibraryServiceException_Exception {
		
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
	
	//All booking for a book are retrieved 
	@Test 
	public void intTest05getAllBookingsForBook() throws LibraryServiceException_Exception, Exception_Exception {
		bookFullName = "Phèdre - Jean Racine";
		assertTrue("The result should be 1", borrowingDtoManager.getAllNotEndedBookingsForABook(bookFullName).size() == 1);
	}
	
	//All booking for a member are retrieved 
	@Test 
	public void intTest06getAllBookingsForMember() throws LibraryServiceException_Exception, Exception_Exception {
		String userMemberId = "UserTest";
		assertTrue("The result should be 1", borrowingDtoManager.getAllBookingsForMember(userMemberId).size() == 1);
	}
	

	
	//creatBooking to test 
	
	//endBooking to test 
	

}
