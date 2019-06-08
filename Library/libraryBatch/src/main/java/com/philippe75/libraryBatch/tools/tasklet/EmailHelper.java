package com.philippe75.libraryBatch.tools.tasklet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class EmailHelper {

	/**
	 * 
	 * Format GregorianCalendar to String with the desired format.   
	 * 
	 * @param calendar {@link GregorianCalendar} the date to extract
	 * @param format the format desired
	 * @return String the time in a String with in the desired format
	 */
	protected String formatGC(Date date, String format){
	    SimpleDateFormat fmt = new SimpleDateFormat(format);
	    fmt.set2DigitYearStart(date);
	    String dateFormatted = fmt.format(date.getTime());
	    return dateFormatted;
	}
	
	
	/**
	 * Replace all the occurrences of a String by another String.
	 * 
	 * @param builder {@link StringBuilder}
	 * @param from the String to replace 
	 * @param to the String to replace with 
	 */
	protected void replaceAll(StringBuilder builder, String from, String to)
	{
	    int index = builder.indexOf(from);
	    while (index != -1)
	    {
	        builder.replace(index, index + from.length(), to);
	        index += to.length(); // Move to the end of the replacement
	        index = builder.indexOf(from, index);
	    }
	}
}
