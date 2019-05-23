package com.philippe75.libraryWebapp.business.impl.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BorrowingDtoManagerImplUnitTest{
	
	@InjectMocks
	BorrowingDtoManagerImpl borrowingDtoManager;
	
	private static SimpleDateFormat sdf;
	private static Date initialDate, initialDateWithAWeek;
	
	 @BeforeClass
	    public static void executeBeforeAll() throws ParseException {
	    	sdf = new SimpleDateFormat("dd/MM/yyyy");
	    	initialDate = sdf.parse("21/12/2018");
	    	initialDateWithAWeek = sdf.parse("28/12/2018");
	 }
	
	
	@Test
	public void addSomeTimeToDateUnitTest() {
		
		Date newDate = borrowingDtoManager.addSomeTimeToDate(initialDate, Calendar.WEEK_OF_YEAR, 1);
		assertEquals(initialDateWithAWeek, newDate);
	}
	
}
