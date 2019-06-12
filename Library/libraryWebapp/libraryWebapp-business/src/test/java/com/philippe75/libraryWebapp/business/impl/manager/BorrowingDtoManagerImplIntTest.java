package com.philippe75.libraryWebapp.business.impl.manager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.philippe75.libraryWebapp.business.contract.manager.BorrowingDtoManager;
import com.philippe75.libraryWebapp.business.contract.manager.UserAccountDtoManager;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookBookingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.Exception_Exception;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.UserAccount;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:bootstrapContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BorrowingDtoManagerImplIntTest {
	
	@Inject
	BorrowingDtoManager borrowingDtoManager;
	
	@Inject 
	UserAccountDtoManager userAccountDtoManager;
	
	private static String userMemberId, bookFullName;
	private static int borrowingId, numberOfWeek;
	private static BookDto bookDto;
	private static BookBookingDto bookBooking; 
	
	@BeforeClass
	public static void executeBeforeAll() throws ParseException {
		userMemberId = "MSegaux";
		borrowingId = 2; 
		numberOfWeek = 4;
		
		bookDto = new BookDto();
		bookDto.setName("Roméo et Juliette");
		bookDto.setAuthor("William Shakespeare");
		bookFullName = "Roméo et Juliette - William Shakespeare";
		bookBooking = new BookBookingDto();
		bookBooking.setBookName("Phèdre");
		bookBooking.setBookAuthor("Jean Racine");
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
		
		String bookName = "Roméo et Juliette";
		String bookAuthor = "William Shakespeare";
		
		BorrowingDto borrowingDto = borrowingDtoManager.getNextBorrowingToComeToEnd(bookName, bookAuthor);
		
		assertTrue("The borrowing id should be 3", borrowingDto.getId() == 3 );
	}
	
	//All the late borrowings are retrieved 
	@Test
	public void intTest04extendBorrowing() throws LibraryServiceException_Exception, Exception_Exception {
		
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
		String bookName = "Phèdre";
		String bookAuthor = "Jean Racine";
		assertTrue("The result should be 1", borrowingDtoManager.getAllNotEndedBookingsForABook(bookName, bookAuthor).size() == 1);
	}
	
	//All booking for a member are retrieved 
	@Test 
	public void intTest06getAllBookingsForMember() throws LibraryServiceException_Exception, Exception_Exception {
		String userMemberId = "UserTest";
		assertTrue("The result should be 1", borrowingDtoManager.getAllNotEndedBookingsForMember(userMemberId).size() == 1);
	}
	
	//create Booking 
	@Test
	public void intTest07createBooking() throws LibraryServiceException_Exception, Exception_Exception {
		
		int bookBookingsOfUserAfterCreation;
		userMemberId = "UserTest2";
		
		//BookBooking creation 
		UserAccount ua = new UserAccount();
		ua.setUserMemberId(userMemberId);
		bookBooking.setUserAccount(ua);
		
		//size of all bookings before creation 
		borrowingDtoManager.createBooking(bookBooking);
		bookBookingsOfUserAfterCreation = borrowingDtoManager.getAllNotEndedBookingsForMember(userMemberId).size();
		assertTrue("The size should be 1", bookBookingsOfUserAfterCreation == 1);
	}
	
	//end Booking 
	@Test
	public void intTest08endBooking() throws LibraryServiceException_Exception, Exception_Exception {
		List<BookBookingDto> lbbd, lbbdfiltered;
		borrowingDtoManager.endBooking(2);
		lbbd = borrowingDtoManager.getAllNotEndedBookingsForMember(userMemberId);
		lbbdfiltered = lbbd 
		.stream()
		.filter(e-> e.isEnded() == true)
		.collect(Collectors.toList());
		
		assertNotNull("Number of ended booking for user should be 1", lbbdfiltered.size() == 1);
	}
	
	//get next borrowing to come to an end 
	@Test
	public void intTest09getNexBorrowingToComeToEndForEachBookBooking() throws LibraryServiceException_Exception {
		
		//creation of List<BookBookingDto>
		bookBooking.setBookName("Phèdre");
		bookBooking.setBookAuthor("Jean Racine");
		List<BookBookingDto> listBookBooking = new ArrayList<>();
		listBookBooking.add(bookBooking);
		listBookBooking.add(bookBooking);
		
		Map<BookBookingDto, BorrowingDto> nextBorrowingForBooking = borrowingDtoManager.getNexBorrowingToComeToEndForEachBookBooking(listBookBooking);
		BorrowingDto nextBorrowingforFirstOfTheList = nextBorrowingForBooking.get(listBookBooking.get(0));
		
		assertTrue("The next borrowing id for this bookbooking should be 5", nextBorrowingforFirstOfTheList.getId() == 5);
	}
	
	//get users mail reminder status 
	@Test
	public void intTest10getUserMailReminderStatus() throws LibraryServiceException_Exception, Exception {
		
		Boolean userMailReminderStatus = borrowingDtoManager.getUserMailReminderStatus(userMemberId);
		assertTrue("The Reminder should be active / true", userMailReminderStatus == true);
	}
	
	//update users mail reminder status
	@Test
	public void intTest11updateMailReminder() throws LibraryServiceException_Exception, Exception {
		
		Boolean userMailReminderStatus; 
		
		userMailReminderStatus = false;
		borrowingDtoManager.updateMailReminder(userMemberId, userMailReminderStatus);
		Boolean newUserMailReminderStatus = borrowingDtoManager.getUserMailReminderStatus(userMemberId);
		assertTrue("The Reminder should be active / true", newUserMailReminderStatus == false);
		
		userMailReminderStatus = true;
		borrowingDtoManager.updateMailReminder(userMemberId, userMailReminderStatus);
		newUserMailReminderStatus = borrowingDtoManager.getUserMailReminderStatus(userMemberId);
		assertTrue("The Reminder should be active / true", newUserMailReminderStatus == true);
	}

}
