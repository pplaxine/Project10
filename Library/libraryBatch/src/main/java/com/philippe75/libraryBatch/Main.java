package com.philippe75.libraryBatch;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.philippe75.libraryBatch.bootstrap.SpringConfiguration;

/**
 * <b>Main Class to run the Batch</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class Main {
	
	/**
	 * Main methode to run the batch.
	 * 
	 * @param args
	 */
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    	
    }
}
