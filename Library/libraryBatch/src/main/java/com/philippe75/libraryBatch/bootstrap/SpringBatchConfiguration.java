package com.philippe75.libraryBatch.bootstrap;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.philippe75.libraryBatch.ListEmail;

@Configuration
@EnableBatchProcessing

public class SpringBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public ListEmail listEmail;
	
	@Bean
	public ListItemReader<String> reader() {
		ListItemReader<String> reader = new ListItemReader<String>(listEmail.getEmails());
		return reader;
	}
	
}
