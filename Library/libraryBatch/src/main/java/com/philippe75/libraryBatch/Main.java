package com.philippe75.libraryBatch;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.philippe75.libraryBatch.bootstrap.SpringConfiguration;
import com.philippe75.libraryBatch.emailService.EmailService;
import com.philippe75.libraryBatch.emailService.EmailServiceImpl;

public class Main {
	
	
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    }
}
