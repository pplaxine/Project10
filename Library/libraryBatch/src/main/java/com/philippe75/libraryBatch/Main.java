package com.philippe75.libraryBatch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.philippe75.libraryBatch.bootstrap.SpringConfiguration;

/**
 * Hello world!
 *
 */


public class Main {
	
	
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    }
}
