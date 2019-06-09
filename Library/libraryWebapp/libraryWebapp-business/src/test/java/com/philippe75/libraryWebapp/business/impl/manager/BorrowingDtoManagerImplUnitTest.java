package com.philippe75.libraryWebapp.business.impl.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookBookingDto;

@RunWith(MockitoJUnitRunner.class)
public class BorrowingDtoManagerImplUnitTest{
	
	@InjectMocks
	BorrowingDtoManagerImpl borrowingDtoManager;
	
	private static SimpleDateFormat sdf;
	private static Date initialDate, initialDateWithAWeek;
	private static List<BookBookingDto> listBookBooking;
	private static BookBookingDto bookBooking;
	
	 @BeforeClass
	    public static void executeBeforeAll() throws ParseException {
	    	sdf = new SimpleDateFormat("dd/MM/yyyy");
	    	initialDate = sdf.parse("21/12/2018");
	    	initialDateWithAWeek = sdf.parse("28/12/2018");
	    	listBookBooking = new ArrayList<>();
	    	bookBooking = new BookBookingDto();
	    
	 }
	
	// Some time is added to a date
	@Test
	public void addSomeTimeToDateUnitTest() {
		
		Date newDate = borrowingDtoManager.addSomeTimeToDate(initialDate, Calendar.WEEK_OF_YEAR, 1);
		assertEquals(initialDateWithAWeek, newDate);
	}
	
    //Remove all the ended bookings from the list
    @Test
    public void endedBookingRemoverUnitTest() {
    	bookBooking.setEnded(true);
    	listBookBooking.add(bookBooking);
    	
    	assertTrue("The list should be empty.", borrowingDtoManager.endedBookingRemover(listBookBooking).size() == 0);
    }
	
}
