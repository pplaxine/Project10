package com.philippe75.libraryBatch;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.philippe75.libraryBatch.bootstrap.SpringConfiguration;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.Book;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingServiceImplService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.UserAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class TestingEnvironnementSetUp {
	
	/**
	 * Object communicating with the BorrowingService webservice endpoint.
	 * 
	 * @see BorrowingService
	 */
	BorrowingService borrowingService = new BorrowingServiceImplService().getBorrowingServiceImplPort();
	private static SimpleDateFormat sdf;
	
	@BeforeClass
	public static void executeBeforeAll() throws ParseException {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	
	@Test
	public void intTestcreateFewBorrowingsForBatchTest() throws Exception {

		//creates new borrowings ----------------
		int bookId = 14;
		String userMemberId = "UserTest";
		
		Date date = new Date();
		
		Date supposedEndDate[] = {	sdf.parse("17/01/2019"),							// date before now	(should not appear in result) 
									addSomeTimeToDate(date, Calendar.DAY_OF_YEAR, 1),	// date now + 1 day
									addSomeTimeToDate(date, Calendar.DAY_OF_YEAR, 6),	// date now + 6 day (should not appear in result)	
									
									addSomeTimeToDate(date, Calendar.DAY_OF_YEAR, 1),	// date now + 2 day
									addSomeTimeToDate(date, Calendar.DAY_OF_YEAR, 2),	// date now + 2 day (extended borrwoing) 
								};	
		
		for (int i = 0; i < supposedEndDate.length; i++) {
			
			BorrowingDto newBorrowing;
			UserAccount userAccount;	
			
			newBorrowing = new BorrowingDto(); 
				
				
			//creates book 
			Book book =  new Book(); 
			book.setId(bookId++);
			//creates userAccount  
			userAccount = new UserAccount();
			userAccount.setUserMemberId(userMemberId);
			//create borrowing
			newBorrowing.setBook(book); 
			newBorrowing.setUserAccount(userAccount);
			
			if(i<supposedEndDate.length - 1) {
				newBorrowing.setSupposedEndDate(dateToXMLGregorianCalendar(supposedEndDate[i]));
				borrowingService.createBorrowing(newBorrowing);
			}else {
				//the last is extended
				BorrowingDto bd = new BorrowingDto();
				bd.setId(10);
				bd.setSecondSupposedEndDate(dateToXMLGregorianCalendar(supposedEndDate[i]));
				borrowingService.extendBorrowing(bd);
			}
		}
		//---------------------------------------

		List<BorrowingDto> lb = borrowingService.getAllBorrowingsToBeRemindedWithin(Calendar.DAY_OF_YEAR, 5).getItem();
		
		assertTrue("The number of reminder email to be sent should be 2",lb.size() == 2);
	}
	
	//------------------- UTILITY METHODE -----------------------------
	
	protected Date addSomeTimeToDate(Date initialDate, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(initialDate);
		cal.add(field, amount);
		return cal.getTime();
	}
	
	protected XMLGregorianCalendar dateToXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
		XMLGregorianCalendar xgc;
		GregorianCalendar gc;
		
		gc = new GregorianCalendar();
		gc.setTime(date);
		xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		
		return xgc;
	}
}
