package com.philippe75.libraryWebapp.webapp.helper;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

public class HelperClass {
	
	public static boolean isDateBeforeNow(XMLGregorianCalendar date) {
	    boolean isBefore = false;
	    
	    Date now = new Date();
	    GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(now);
	    
	    if (calendar.compareTo(date.toGregorianCalendar()) < 0 ) {
	        isBefore = true;
	    } 
	    return isBefore;
	}
}
