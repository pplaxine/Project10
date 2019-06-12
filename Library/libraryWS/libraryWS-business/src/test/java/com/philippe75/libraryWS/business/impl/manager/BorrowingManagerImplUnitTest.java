package com.philippe75.libraryWS.business.impl.manager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.business.impl.handler.ManagerHandlerImpl;
import com.philippe75.libraryWS.consumer.contract.dao.BookBookingDao;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

@RunWith(MockitoJUnitRunner.class)
public class BorrowingManagerImplUnitTest {
	
	@InjectMocks
	private ManagerHandlerImpl managerHandler;
	@InjectMocks
	private BorrowingManagerImpl borrowingManager;
	@InjectMocks
	private DaoHandlerImpl daoHandler;
	@Mock
	private BorrowingDaoImpl borrowingDao;
	@Mock
	private BookDaoImpl bookDao;
	@Mock
	private UserAccountDaoImpl userAccountDao;
	@Mock
	private BookBookingDao bookBookingDao;
	
	private Borrowing borrowing;
	private BorrowingDto borrowingDto;
	private UserAccount userAccount;
	private Book book, bookForList;
	private BookDto bookDto;
	private BookBooking bookBooking;
	private List<BookBooking> lbb;
	private List<Book> lb;
	
	private static SimpleDateFormat sdf;
	private static Date startDate, supposedEndDate, supposedEndDateExceeded, secondSupposedEndDate, effectiveEndDate;
	
    @BeforeClass
    public static void executeBeforeAll() throws ParseException {
    	sdf = new SimpleDateFormat("dd/MM/yyyy");
    	startDate = sdf.parse("21/12/2018");
    	supposedEndDate = sdf.parse("03/09/2019");
    	supposedEndDateExceeded = sdf.parse("03/01/2019");
    	secondSupposedEndDate = sdf.parse("07/01/2019");
    	effectiveEndDate = sdf.parse("15/01/2019");
    }
	
    @Before
    public void executeBeforeEach() {
    	//set the mock in abstract class
    	AbstractManager.configure(daoHandler);
    	//set the Mock in ManagerHandler class
    	ManagerHandlerImpl.configure(borrowingManager);
    	
    	//STUB Borrowing
    	//-- UserAccount
    	userAccount = new UserAccount();
    	userAccount.setUserMemberId("3");
    	userAccount.setFirstName("Jean");
    	//-- Book
    	book = new Book();
    	book.setId(5);
    	book.setName("1984");
    	book.setAuthor("George Orwell");
    	book.setLibrary(new Library());
    	book.setAvailable(false);
    	
    	//BookDto
    	bookDto = new BookDto();
    	bookDto.setName("1984");
    	bookDto.setAuthor("George Orwell");
    	
    	borrowing = new Borrowing();
    	borrowing.setId(1);
    	borrowing.setUserAccount(userAccount);
    	borrowing.setBook(book);
    	borrowing.setStartDate(startDate);
    	borrowing.setSupposedEndDate(supposedEndDate);
    	borrowing.setExtended(true);
    	borrowing.setReminderMailSent(false);
    	borrowing.setSecondSupposedEndDate(secondSupposedEndDate);
    	borrowing.setEffectiveEndDate(effectiveEndDate);
    	
    	//STUB BorrowingDto
    	borrowingDto = new BorrowingDto();
    	borrowingDto.setId(1);
    	borrowingDto.setUserAccount(userAccount);
    	borrowingDto.setBook(book);
    	borrowingDto.setStartDate(startDate);
    	borrowingDto.setSupposedEndDate(supposedEndDate);
    	borrowingDto.setExtended(true);
    	borrowingDto.setReminderMailSent(false);
    	borrowingDto.setSecondSupposedEndDate(secondSupposedEndDate);
    	borrowingDto.setEffectiveEndDate(effectiveEndDate);
    	
    	//STUB List<BookBooking> 
    	String[] userMemberIds = {"JTille", "MSegaux","UserTest01", "UserTest02"};
    	lbb = new ArrayList<>();
    	for (int i = 4; i > 0; i--) {
    		UserAccount ua = new UserAccount();
    		ua.setUserMemberId(userMemberIds[i-1]);
    		bookBooking = new BookBooking();
    		bookBooking.setId(i);
    		bookBooking.setUserAccount(ua);
    		lbb.add(bookBooking);
		}
    	
    	//Stub list<Book>
    	lb = new ArrayList<>();
    	for (int i = 0; i < 4; i++) {
    		bookForList = new Book();
    		bookForList.setAvailable(true);
    		lb.add(book);
		}
    	
    }
    
    //BorrowingDto created from Borrowing contains all the values
    @Test
    public void borrowingModelToDtoTest() {
    	BorrowingDto borrowingDto = borrowingManager.borrowingModelToDto(borrowing);
    	assertEquals("borrowingDto : Id ",borrowingDto.getId(), borrowing.getId());
    	assertEquals("borrowingDto : UserAccount ",borrowingDto.getUserAccount(), borrowing.getUserAccount());
    	assertEquals("borrowingDto : Book ",borrowingDto.getBook(), borrowing.getBook());
    	assertEquals("borrowingDto : StartDate ",borrowingDto.getStartDate(), borrowing.getStartDate());
    	assertEquals("borrowingDto : SupposedEndDate ",borrowingDto.getSupposedEndDate(), borrowing.getSupposedEndDate());
    	assertEquals("borrowingDto : Extended ",borrowingDto.isExtended(), borrowing.isExtended());
    	assertEquals("borrowingDto : ReminderMailSent ",borrowingDto.isReminderMailSent(), borrowing.isReminderMailSent());
    	assertEquals("borrowingDto : SecondSupposedEndDate ",borrowingDto.getSecondSupposedEndDate(), borrowing.getSecondSupposedEndDate());
    	assertEquals("borrowingDto : EffectiveEndDate ",borrowingDto.getEffectiveEndDate(), borrowing.getEffectiveEndDate());
    	
    }
    
    //BorrowingDto created from Borrowing contains all the values
    @Test
    public void borrowingDtoToModelTest() {
    	Borrowing borrowing = borrowingManager.borrowingDtoToModel(borrowingDto);
    	assertEquals("borrowing : Id ",borrowingDto.getId(), borrowing.getId());
    	assertEquals("borrowing : UserAccount ",borrowingDto.getUserAccount(), borrowing.getUserAccount());
    	assertEquals("borrowing : Book ",borrowingDto.getBook(), borrowing.getBook());
    	assertEquals("borrowing : StartDate ",borrowingDto.getStartDate(), borrowing.getStartDate());
    	assertEquals("borrowing : SupposedEndDate ",borrowingDto.getSupposedEndDate(), borrowing.getSupposedEndDate());
    	assertEquals("borrowing : Extended ",borrowingDto.isExtended(), borrowing.isExtended());
    	assertEquals("borrowing : ReminderMailSent ",borrowingDto.isReminderMailSent(), borrowing.isReminderMailSent());
    	assertEquals("borrowing : SecondSupposedEndDate ",borrowingDto.getSecondSupposedEndDate(), borrowing.getSecondSupposedEndDate());
    	assertEquals("borrowing : EffectiveEndDate ",borrowingDto.getEffectiveEndDate(), borrowing.getEffectiveEndDate());
    	
    }
    
    //List of BorrowingDto is created
    @Test
    public void getAllBorrowingForUserTest() throws Exception {
    	
    	String userMemberId = "1";
    	//---- mock setup
    	List<Borrowing> lb = new ArrayList<>();
    	lb.add(borrowing);
    	lb.add(borrowing);
    	
    	when(daoHandler.getBorrowingDao().getAllBorrowingForUser(userMemberId)).thenReturn(lb);
    	//---------------
    	
    	List<BorrowingDto> lbd = managerHandler.getBorrowingManager().getAllBorrowingForUser(userMemberId);
    	assertTrue("The List of BorrowingDto is bigger than expected",lbd.size() == 2);
		assertEquals("The list of BorrowingDto don't contains the expected BorrowingDto ", lbd.get(0).getBook().getName() , borrowing.getBook().getName());
    }
    
    //BorrowingDto is created
    @Test
    public void getBorrowingByIdTest() throws Exception {
    	
    	int borrowingId = 5;
    	//---- mock setup
    	when(daoHandler.getBorrowingDao().getBorrowingById(borrowingId)).thenReturn(borrowing);
    	//---------------
    	
    	BorrowingDto borrowingDto = managerHandler.getBorrowingManager().getBorrowingById(borrowingId);
    	assertEquals("BorrowingDto not create as expected", borrowingDto.getBook().getName() , borrowing.getBook().getName());
    }
    
    //List of BorrowingDto is created
    @Test
    public void getAllLateBorrowingsTest() throws Exception {
    	
    	//---- mock setup
    	List<Borrowing> lb = new ArrayList<>();
    	lb.add(borrowing);
    	lb.add(borrowing);
    	
    	when(daoHandler.getBorrowingDao().getAllLateBorrowings()).thenReturn(lb);
    	//---------------
    	
    	List<BorrowingDto> lbd = managerHandler.getBorrowingManager().getAllLateBorrowings();
    	assertTrue("The List of BorrowingDto is bigger than expected",lbd.size() == 2);
		assertEquals("The list of BorrowingDto don't contains the expected BorrowingDto ", lbd.get(0).getBook().getName() , borrowing.getBook().getName());
    }
    
    //The book is already borrowed
    @Test(expected = LibraryServiceException.class)
    public void createBorrowingBookAlreadyBorrowedExceptionTest() throws Exception {
    	
    	//---- mock setup
    	when(daoHandler.getBookDao().getBookById(book.getId())).thenReturn(book);
    	//---------------
    	
    	managerHandler.getBorrowingManager().createBorrowing(borrowingDto);
    }
   
	  @Test(expected = LibraryServiceException.class)
	  public void createBorrowingBookNotBookingQueuTest() throws Exception {
		String userMemberId = userAccount.getUserMemberId();
	  	//---- mock setup
	  	book.setAvailable(true);
	  	borrowingDto.setSupposedEndDate(null);
	  	when(daoHandler.getBookDao().getBookById(book.getId())).thenReturn(book);
	  	when(daoHandler.getBookBookingDao().getAllBookingsForABook(borrowing.getBook())).thenThrow(NoResultException.class);
	  	when(daoHandler.getUserAccountDao().getUserAccountByMemberId(userMemberId)).thenReturn(userAccount);
	  	//---------------
	  	
	  	managerHandler.getBorrowingManager().createBorrowing(borrowingDto);
	  }
	  
	//One of the x exemplar available borrowed by not the x firsts in the queue
    @Test(expected = LibraryServiceException.class)
    public void createBorrowingBookNotFirstInListExceptionTest() throws Exception {
    	
    	
    	//---- mock setup
    	lb.remove(0);
    	book.setAvailable(true);
    	borrowingDto.setSupposedEndDate(null);
    	borrowingDto.getUserAccount().setUserMemberId("OtherMember");
    	when(daoHandler.getBookDao().getBookById(book.getId())).thenReturn(book);
    	when(daoHandler.getBookBookingDao().getAllBookingsForABook(borrowing.getBook())).thenReturn(lbb);
    	when(daoHandler.getBookDao().getListBookByName(borrowing.getBook().getName())).thenReturn(lb);
    	//---------------
    	
    	managerHandler.getBorrowingManager().createBorrowing(borrowingDto);
    }
    
    //The Borrowing doesn't comply with contraints
    @Test(expected = LibraryServiceException.class)
    public void createBorrowingBookContraintViolationExceptionTest() throws Exception {
    	String userMemberId = userAccount.getUserMemberId();
    	
    	//---- mock setup
    	book.setAvailable(true);
    	borrowingDto.setSupposedEndDate(null);
    	when(daoHandler.getBookDao().getBookById(book.getId())).thenReturn(book);
    	when(daoHandler.getBookBookingDao().getAllBookingsForABook(borrowing.getBook())).thenThrow(NoResultException.class);
    	when(daoHandler.getUserAccountDao().getUserAccountByMemberId(userMemberId)).thenReturn(userAccount);
    	//---------------
    	managerHandler.getBorrowingManager().createBorrowing(borrowingDto);
    }
    

	//Null values pass as param to extend borrowing 
    @Test(expected = LibraryServiceException.class)
    public void extendBorrowingEmptyValueExceptionTest() throws Exception {
    	managerHandler.getBorrowingManager().extendBorrowing(null);
    }
    
    //Borrowing is extended whilst borrowing end date exceeded
    @Test(expected = LibraryServiceException.class)
    public void extendBorrowingEndDateExceededExceptionTest() throws Exception {
    	
    	//---- mock setup
    	borrowing.setSupposedEndDate(supposedEndDateExceeded);
    	borrowing.setExtended(true);
    	when(daoHandler.getBorrowingDao().getBorrowingById(borrowingDto.getId())).thenReturn(borrowing);
    	//---------------
    	
    	borrowingManager.extendBorrowing(borrowingDto);
    }
    
    //Borrowing is extended whilst borrowing already extended once 
    @Test(expected = LibraryServiceException.class)
    public void extendBorrowingAlreadyExtendedExceptionTest() throws Exception {
    	
    	//---- mock setup
    	borrowing.setExtended(true);
    	when(daoHandler.getBorrowingDao().getBorrowingById(borrowingDto.getId())).thenReturn(borrowing);
    	//---------------
    	
    	managerHandler.getBorrowingManager().extendBorrowing(borrowingDto);
    }
    
    //Null param to end borrowing 
    @Test(expected = LibraryServiceException.class)
    public void endBorrowingAttributMissingAttributExceptionTest() throws Exception {
    	managerHandler.getBorrowingManager().endBorrowing(null);
    	
    }
    
    //Borrowing is already ended 
    @Test(expected = LibraryServiceException.class)
    public void endBorrowingAttributAlreadyFieldExceptionTest() throws Exception {
    	
    	//---- mock setup
    	borrowingDto.setEffectiveEndDate(effectiveEndDate);
    	when(daoHandler.getBorrowingDao().getBorrowingById(borrowingDto.getId())).thenReturn(borrowing);
    	//---------------
    	
    	managerHandler.getBorrowingManager().endBorrowing(borrowingDto);
    }

}
