package com.philippe75.libraryWS.business.impl.manager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.contract.manager.BookBookingManager;
import com.philippe75.libraryWS.business.dto.BookBookingDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.business.impl.handler.ManagerHandlerImpl;
import com.philippe75.libraryWS.consumer.contract.dao.BorrowingDao;
import com.philippe75.libraryWS.consumer.impl.dao.BookBookingDaoImpl;
import com.philippe75.libraryWS.consumer.impl.dao.BookDaoImpl;
import com.philippe75.libraryWS.consumer.impl.dao.BorrowingDaoImpl;
import com.philippe75.libraryWS.consumer.impl.dao.UserAccountDaoImpl;
import com.philippe75.libraryWS.consumer.impl.handler.DaoHandlerImpl;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.BookBooking;
import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;
import com.philippe75.libraryWS.model.library.Library;
import com.philippe75.libraryWS.model.user.UserAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BookBookingManagerImplUnitTest {
	
	@InjectMocks
	private ManagerHandlerImpl managerHandler;
	@InjectMocks
	private BookBookingManagerImpl bookBookingManager;
	@InjectMocks
	private DaoHandlerImpl daoHandler;
	@Mock
	private BookBookingDaoImpl bookBookingDao;
	@Mock
	private BookDaoImpl bookDao;
	@Mock
	private BorrowingDaoImpl borrowingDao;
	
	private BookBooking bookBooking;
	private BookBookingDto bookBookingDto;
	private Book book;
	private UserAccount userAccount;
	private Borrowing borrowing;
	private List<Book> lb;
	private List<BookBooking> lbb;
	private List<Borrowing> lbrw;
	private String userMemberId;
	
	private static SimpleDateFormat sdf;
	private static Date mailSentDate;
	
    @BeforeClass
    public static void executeBeforeAll() throws ParseException {
    	sdf = new SimpleDateFormat("dd/MM/yyyy");
    	mailSentDate = sdf.parse("21/12/2018");
    }
	
    @Before
    public void executeBeforeEach() {
    	//set the mock in abstract class
    	AbstractManager.configure(daoHandler);
    	//set the Mock in ManagerHandler class
    	ManagerHandlerImpl.configure(bookBookingManager);
    	
    	
    	
    	lbb = new ArrayList<>();
    	userMemberId = "test02";
    	
    	//STUB BookBooking
    	//-- UserAccount
    	userAccount = new UserAccount();
    	userAccount.setUserMemberId("UserTest2");
    	userAccount.setFirstName("User2");
    	
    	bookBooking = new BookBooking();
    	bookBooking.setId(16);
    	bookBooking.setBookName("1984");
    	bookBooking.setBookAuthor("George Orwell");
    	bookBooking.setUserAccount(userAccount);
    	bookBooking.setMailSentDate(mailSentDate);
    	bookBooking.setEnded(false);
    	
    	//STUB BookBookingDto
    	bookBookingDto = new BookBookingDto();
    	bookBookingDto.setId(16);
    	bookBookingDto.setBookName("1984");
    	bookBookingDto.setBookAuthor("George Orwell");
    	bookBookingDto.setUserAccount(userAccount);
    	bookBookingDto.setMailSentDate(mailSentDate);
    	bookBookingDto.setEnded(true);
    	
    	//STUB List<Book>
    	lb = new ArrayList<>();
    	book = new Book();
    	book.setName("1984");
    	book.setAvailable(true);
    	lb.add(book);
    	
    	//Stub List<Borrowing>
    	lbrw = new ArrayList<>();
    	borrowing = new Borrowing();
    	borrowing.setBook(book);
    	lbrw.add(borrowing);
    	
    }
    
    @Test
    public void getAllBookingsForMemberUnitTest() throws Exception {
    	
    	//Mock setup
    	List<BookBookingDto> lbbd;
    	lbb.add(bookBooking);
    	when(daoHandler.getBookBookingDao().getAllBookingsForMember(userMemberId)).thenReturn(lbb);
    	
    	lbbd = managerHandler.getBookBookingManager().getAllBookingsForMember(userMemberId);
    	assertTrue("The size of the list should be 1", lbbd.size() == 1);
    }
    
    @Test
    public void getAllBookingsForMemberNoParamUnitTest() throws Exception {
    	assertNull("The result should be null", managerHandler.getBookBookingManager().getAllBookingsForMember(null));
    }
    
    @Test
    public void getAllBookingsForBookNoParamUnitTest() throws Exception {
    	assertNull("The result should be null", managerHandler.getBookBookingManager().getAllBookingsForABook(null));
    }
    
    // param is null
    @Test
    public void createBookingParamNullUnitTest() throws Exception {
    	int result;
    	result = managerHandler.getBookBookingManager().createBooking(null);
    	assertTrue(result == 0);
    }
    
    //Booking requested for an available book 
    @Test(expected = LibraryServiceException.class)
    public void createBookingRGBookAvailableExceptionUnitTest() throws Exception{
    	when(daoHandler.getBookDao().getListBookByName("1984")).thenReturn(lb);
    	managerHandler.getBookBookingManager().createBooking(bookBookingDto);
    }
    
    //Maximum of (book exemplar *2) is reached 
    @Test(expected = LibraryServiceException.class)
    public void createBookingRGMaxBookingReachedUnitTest() throws Exception{
    	
    	//Mock setup
    	lb.get(0).setAvailable(false);
    	lbb.add(bookBooking);
    	lbb.add(bookBooking);
    	
    	when(daoHandler.getBookDao().getListBookByName("1984")).thenReturn(lb);
    	when(daoHandler.getBookBookingDao().getAllBookingsForABook(book)).thenReturn(lbb);

    	managerHandler.getBookBookingManager().createBooking(bookBookingDto);
    }
    
    //Book already rented by member
    @Test(expected = LibraryServiceException.class)
    public void createBookingRGBookAlreadyRendtedUnitTest() throws Exception{
    	
    	//Mock setup
    	lb.get(0).setAvailable(false);
    	lbb.add(bookBooking);
    	
    	when(daoHandler.getBookDao().getListBookByName("1984")).thenReturn(lb);
    	when(daoHandler.getBookBookingDao().getAllBookingsForABook(book)).thenReturn(lbb);
    	when(daoHandler.getBorrowingDao().getAllBorrowingForUser(bookBooking.getUserAccount().getUserMemberId())).thenReturn(lbrw);

    	managerHandler.getBookBookingManager().createBooking(bookBookingDto);
    }
    
    //Booking already made by member
    @Test(expected = LibraryServiceException.class)
    public void createBookingRGBookingAlreadyMadeUnitTest() throws Exception{
    	
    	//Mock setup
    	String userMemberId = bookBooking.getUserAccount().getUserMemberId();
    	lb.get(0).setAvailable(false);
    	lbrw = new ArrayList<>();
    	lbb.add(bookBooking);
    	
    	when(daoHandler.getBookDao().getListBookByName("1984")).thenReturn(lb);
    	when(daoHandler.getBookBookingDao().getAllBookingsForABook(book)).thenReturn(lbb);
    	when(daoHandler.getBorrowingDao().getAllBorrowingForUser(bookBooking.getUserAccount().getUserMemberId())).thenReturn(lbrw);
    	when(daoHandler.getBookBookingDao().getAllBookingsForMember(userMemberId)).thenReturn(lbb);

    	managerHandler.getBookBookingManager().createBooking(bookBookingDto);
    }
    
    
    
    //BorrowingDto created from Borrowing contains all the values
    @Test
    public void bookBookingModelToDtoUnitTest() {
    	BookBookingDto bookBookingDto =  bookBookingManager.bookBookingModelToDto(bookBooking);
    	assertEquals("bookBookingDto : Id ",bookBookingDto.getId(), bookBooking.getId());
    	assertEquals("bookBookingDto : Name ",bookBookingDto.getBookName(), bookBooking.getBookName());
    	assertEquals("bookBookingDto : Author ",bookBookingDto.getBookAuthor(), bookBooking.getBookAuthor());
    	assertEquals("bookBookingDto : MailSentDate ",bookBookingDto.getMailSentDate(), bookBooking.getMailSentDate());
    	assertEquals("bookBookingDto : UserAccount ",bookBookingDto.getUserAccount(), bookBooking.getUserAccount());
    	assertEquals("bookBookingDto : Ended ",bookBookingDto.getEnded(), bookBooking.isEnded());
    }
    
    //Borrowing created from BorrowingDto contains all the values
    @Test
    public void bookBookingDtoToModelUnitTest() {
    	BookBooking bookBooking =  bookBookingManager.bookBookingDtoToModel(bookBookingDto);
    	assertEquals("bookBookingDto : Id ",bookBooking.getId(), bookBookingDto.getId());
    	assertEquals("bookBookingDto : Name ",bookBooking.getBookName(), bookBookingDto.getBookName());
    	assertEquals("bookBookingDto : Author ",bookBooking.getBookAuthor(), bookBookingDto.getBookAuthor());
    	assertEquals("bookBookingDto : MailSentDate ",bookBooking.getMailSentDate(), bookBookingDto.getMailSentDate());
    	assertEquals("bookBookingDto : UserAccount ",bookBooking.getUserAccount(), bookBookingDto.getUserAccount());
    	assertEquals("bookBookingDto : Ended ",bookBooking.isEnded(), bookBookingDto.getEnded());
    }
    
    //Is there available exemplar of a book in the list 
    @Test
    public void bookAvailabilityCheckerUnitTest() {
    	Book book;
    	List<Book> lb = new ArrayList<>();
    	//Creation of list of Books where half are available 
    	for (int i = 0; i < 4; i++) {
    		book = new Book();
    		if(i%2 == 0) {
    			book.setAvailable(true);
    		}else {
    			book.setAvailable(false);
    		}
    		lb.add(book);
		}
    	//List of book available from entry list
    	List<Book> lba = bookBookingManager.bookAvailabilityChecker(lb);
    	assertTrue(lba.size() == 2);
    }
    
    //Is the book in the list of borrowings 
    @Test
    public void isThisBookInTheBorrowingsUnitTest() {
    	String bookName = "1984";
    	String [] bookNames = {"1984","Roméo et Juliette","Roméo et Juliette", "1984"};
    	List<Borrowing> lb = new ArrayList<>();
    	for (int i = 0; i < 4; i++) {
    		Book book = new Book();
    		book.setName(bookNames[i]);
    		Borrowing borrowing = new Borrowing();
    		borrowing.setBook(book);
    		if(i%2 == 0) {
    			borrowing.setEffectiveEndDate(new Date());
    		}
    		lb.add(borrowing);
		}
    	
    	assertTrue(bookBookingManager.isThisBookInTheBorrowings(lb, bookName));
    	lb.forEach(e -> e.setEffectiveEndDate(new Date()));
    	assertFalse(bookBookingManager.isThisBookInTheBorrowings(lb, bookName));
    }
    
    //Remove all the ended bookings from the list
    @Test
    public void endedBookingRemoverUnitTest() {
    	bookBooking.setEnded(true);
    	lbb.add(bookBooking);
    	
    	assertTrue("The list should be empty.", bookBookingManager.endedBookingRemover(lbb).size() == 0);
    }
    


}
