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
import com.philippe75.libraryWS.business.impl.handler.ManagerHandlerImpl;
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
	
	private BookBooking bookBooking;
	private BookBookingDto bookBookingDto;
	private UserAccount userAccount;
	
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

    


}
