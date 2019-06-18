package com.philippe75.libraryWS.exposure.service.borrowing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.BookBookingDto;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.business.impl.manager.AbstractManager;
import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;
import com.philippe75.libraryWS.exposure.bootstrap.SpringConfiguration;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.BookBooking;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;
import com.philippe75.libraryWS.model.user.UserAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BorrowingServiceIntTest extends AbstractManager {
	
	@Inject
	private ManagerHandler managerHandler;
	
	@Inject
	private DaoHandler daoHandler;
	
	private static String userMemberId;
	private static int bookId, borrowingId, newBorrowingId;
	private static SimpleDateFormat sdf;
	private static Date supposedEndDate, secondSupposedEndDate;
	private static BookDto bookDto;
	private static BookBookingDto bookingDto; 
	 
	
	@BeforeClass
	public static void executeBeforeAll() throws ParseException {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
    	supposedEndDate = sdf.parse("03/08/2019");
    	bookId=20;
    	secondSupposedEndDate = sdf.parse("06/08/2019");
		userMemberId = "JTille";
		borrowingId = 1; 
		newBorrowingId = 7;
		bookDto = new BookDto();
		bookDto.setName("Phèdre");
		bookDto.setAuthor("Jean Racine");
		//book
		bookingDto = new BookBookingDto();
		bookingDto.setBookAuthor("Jean Racine");
		bookingDto.setBookName("Phèdre");
		bookingDto.setEnded(true);
		
	}
	
	//All the borrowing for a user are retrieved
	@Test
	public void intTest01getAllBorrowingForUser() throws LibraryServiceException {
		
		List<BorrowingDto> lb = managerHandler.getBorrowingManager().getAllBorrowingForUser(userMemberId);
		assertTrue( "The list should contain 3 elements ",lb.size() == 3 );
	}
	
	//All the borrowing for a book are retrieved
	@Test
	public void intTest02getAllBorrowingForBook() throws LibraryServiceException {
		BookDto bookDto = new BookDto();
		bookDto.setName("Roméo et Juliette");
		bookDto.setAuthor("William Shakespeare");
		List<BorrowingDto> lb = managerHandler.getBorrowingManager().getAllBorrowingForBook(bookDto);
		assertTrue( "The list should contain 5 elements ",lb.size() == 5 );
	}
	
	//All the late borrowings are retrieved 
	@Test
	public void intTest03getAllLateBorrowings() throws LibraryServiceException {
		List<BorrowingDto> lb = managerHandler.getBorrowingManager().getAllLateBorrowings();
		assertTrue("The list should contain 3 elements " ,lb.size() == 3 );
	}
	
	//The correct borrowing is retrieved
	@Test 
	public void intTest04getBorrowingById() throws LibraryServiceException {
		String bookName = "Roméo et Juliette";
		BorrowingDto borrowing = managerHandler.getBorrowingManager().getBorrowingById(borrowingId);
		assertEquals("Wrong borrowing retrieved " ,borrowing.getBook().getName(), bookName);
	}
	
	//A borrowing is created 
	@Test
	public void  intTest05createBorrowing() throws Exception {
		int numberOfBooksBorrowedBefore, numberOfBooksBorrowedAfter ; 
		
		//new borrowing
		BorrowingDto newBorrowing = new BorrowingDto(); 
		
		Book book = daoHandler.getBookDao().getBookById(bookId);
		
		
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUserMemberId(userMemberId);
		
		newBorrowing.setBook(book); 
		newBorrowing.setUserAccount(userAccount);
		newBorrowing.setSupposedEndDate(supposedEndDate);
		//-----------------------------------------------------------
		
		//check the number of borrowings for this user 
		numberOfBooksBorrowedBefore = managerHandler.getBorrowingManager().getAllBorrowingForUser(userMemberId).size();
		//create a new borrowing
		managerHandler.getBorrowingManager().createBorrowing(newBorrowing);
		//retrieve the last borrowing 
		//check the number of borrowings for this user 
		numberOfBooksBorrowedAfter = managerHandler.getBorrowingManager().getAllBorrowingForUser(userMemberId).size(); 
		
		assertEquals(numberOfBooksBorrowedAfter, numberOfBooksBorrowedBefore +1);
		
	}
	
	//A Borrowing end date is extended
	@Test
	public void intTest06extendBorrowing() throws LibraryServiceException, Exception {

		
		//retrieve the newly create borrowing 
		BorrowingDto newBorrowing = managerHandler.getBorrowingManager().getBorrowingById(newBorrowingId);
		//new end date added de to object
		newBorrowing.setSecondSupposedEndDate(secondSupposedEndDate);
		//extend the borrowing with the new date  
		managerHandler.getBorrowingManager().extendBorrowing(newBorrowing);
		//retrieve the newly create borrowing after beeing extended 
		BorrowingDto newBorrowingExtended = managerHandler.getBorrowingManager().getBorrowingById(newBorrowingId);
		
		assertNotNull("The extension hasn't been taken to account" ,newBorrowingExtended.getSecondSupposedEndDate());
	}
	
	
	//A Borrowing is ended
	@Test
	public void intTest07endBorrowing() throws LibraryServiceException {
		
		//retrieve the newly create borrowing 
		BorrowingDto newBorrowing = managerHandler.getBorrowingManager().getBorrowingById(newBorrowingId);
		//end borrowing 
		managerHandler.getBorrowingManager().endBorrowing(newBorrowing);
		//retrieve ended borrowing
		BorrowingDto endedBorrowing = managerHandler.getBorrowingManager().getBorrowingById(newBorrowingId);
		
		assertNotNull("The end of the borrowing hasn't been taken to account" ,endedBorrowing.getEffectiveEndDate());
	}
	
	//All the booking for a book are retrieved 
	@Test
	public void intTest08getAllBookingsForABook() throws LibraryServiceException, Exception {
		assertTrue("The result should be 1", managerHandler.getBookBookingManager().getAllBookingsForABook(bookDto).size() == 1);
	}
	
	//All the booking for a member are retrieved 
	@Test
	public void intTest09getAllBookingsForAMember() throws Exception {
		userMemberId = "UserTest";
		assertTrue("The result should be 1", managerHandler.getBookBookingManager().getAllBookingsForMember(userMemberId).size() == 1);
	}
	
	//A booking is created 
	@Test
	public void intTest10createBooking() throws LibraryServiceException, Exception {
		
		int newBookingId;
		
		
		//user
		UserAccount ua = new UserAccount();
		ua.setUserMemberId("UserTest2");
		bookingDto.setUserAccount(ua);
		
		newBookingId = managerHandler.getBookBookingManager().createBooking(bookingDto);
		assertTrue("New booking id should be 2 ",newBookingId == 2);
		
		bookingDto.setEnded(false);
		newBookingId = managerHandler.getBookBookingManager().createBooking(bookingDto);
		assertTrue("New booking id should be 3 ", newBookingId == 3);
	}
	
	//All the active booking retrieved
	@Test
	public void intTest11getAllNotEndedBookings() throws Exception {
		assertTrue("The result should be 2", managerHandler.getBookBookingManager().getAllNotEndedBookings().size() == 2);
	}
	
	//the booking is updated with sending mail date. 
	@Test
	public void intTest12updateMailDateBooking() throws Exception {
		managerHandler.getBookBookingManager().updateMailDateBooking(3);
		List<BookBooking> listBookBooking = daoHandler.getBookBookingDao().getAllBookingsForMember("UserTest2");
		listBookBooking = listBookBooking
									.stream()
									.filter(e -> e.getMailSentDate() != null)
									.collect(Collectors.toList());
		assertTrue("The list size should be 1", listBookBooking.size() == 1);
	}
	
	//All outdated bookings are ended 
	@Test
	public void intTest13endAllActiveBookingsExceededOf() throws Exception {
		//make sure next executes at least 1 sec later 
		Thread.sleep(3000);
		managerHandler.getBookBookingManager().endAllActiveBookingsExceededOf(Calendar.SECOND, 1);
		assertTrue("The result should be 1", managerHandler.getBookBookingManager().getAllNotEndedBookings().size() == 1 );
	}
	
	@Test
	public void intTest14getAllBorrowingsToBeRemindedWithin() throws Exception {
		BorrowingDto newBorrowing;
		UserAccount userAccount;
		
		//creates new borrowings ----------------
		bookId = 14;
		userMemberId = "UserTest";
		
		Date date = new Date();
		
		Date supposedEndDate[] = {	sdf.parse("17/01/2019"),							// date before now	(should not appear in result) 
									addSomeTimeToDate(date, Calendar.DAY_OF_YEAR, 1),	// date now + 1 day
									addSomeTimeToDate(date, Calendar.DAY_OF_YEAR, 6),	// date now + 6 day (should not appear in result)	
									
									addSomeTimeToDate(date, Calendar.DAY_OF_YEAR, 1),	// date now + 2 day
									addSomeTimeToDate(date, Calendar.DAY_OF_YEAR, 2),	// date now + 2 day (extended borrwoing) 
								};	
		
		for (int i = 0; i < supposedEndDate.length; i++) {
				newBorrowing = new BorrowingDto(); 
				
				//creates book 
				Book book = daoHandler.getBookDao().getBookById(bookId++);
				//creates userAccount  
				userAccount = new UserAccount();
				userAccount.setUserMemberId(userMemberId);
				//create borrowing
				newBorrowing.setBook(book); 
				newBorrowing.setUserAccount(userAccount);
				
				if(i<supposedEndDate.length - 1) {
					newBorrowing.setSupposedEndDate(supposedEndDate[i]);
					managerHandler.getBorrowingManager().createBorrowing(newBorrowing);
				}else {
					//the last is extended
					BorrowingDto bd = new BorrowingDto();
					bd.setId(11);
					bd.setSecondSupposedEndDate(supposedEndDate[i]);
					managerHandler.getBorrowingManager().extendBorrowing(bd);
				}
		}
		//---------------------------------------

		List<BorrowingDto> lb = managerHandler.getBorrowingManager().getAllBorrowingsToBeRemindedWithin(Calendar.DAY_OF_YEAR, 5);
		
		assertTrue("The number of reminder email to be sent should be 2",lb.size() == 2);
	}
	
	//update status if a reminder mail is sent
	@Test
	public void intTest15updateMailReminder() throws Exception {
		
		Boolean mailReminderStatus;
		
		daoHandler.getBorrowingDao().updateBorrowingReminderMailStatus(1, true);
		mailReminderStatus = daoHandler.getBorrowingDao().getBorrowingById(1).isReminderMailSent();
		assertTrue("The mail reminder status should be false", mailReminderStatus == true);
		
		daoHandler.getBorrowingDao().updateBorrowingReminderMailStatus(1, false);
		mailReminderStatus = daoHandler.getBorrowingDao().getBorrowingById(1).isReminderMailSent();
		assertTrue("The mail reminder status should be true", mailReminderStatus == false);
	}
	
}

