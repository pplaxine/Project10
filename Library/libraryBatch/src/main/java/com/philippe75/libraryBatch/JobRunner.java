package com.philippe75.libraryBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <b>Allows to set when batch needs to be ran</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@PropertySource("classpath:/Config.properties")
@Component
public class JobRunner {
	
	/**
	 * Allow access to Config.properties
	 */
	@Autowired
	Environment env;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job sendMailToLateBorrowingsJob;
	
	@Autowired
	private Job sendEmailToBookingListJob;
	
	@Autowired
	private Job sendEmailReminder; 
	
	/**
	 * Methode that run the batch on a scheduled delay.
	 */
	// FIXED DELAY FOR TESTING
	@Scheduled(fixedDelay=30000)
	//@Scheduled(cron ="0 1 * * *")
	public void runJobLauncher() {
		
		System.out.println("Starting batch job");
		
		JobExecution je;
		try {
			je = 
			jobLauncher.run(sendEmailReminder, new JobParameters());							//email 5 days reminder job  
			//jobLauncher.run(sendEmailToBookingListJob, new JobParameters());					//email booking job 
			//jobLauncher.run(sendMailToLateBorrowingsJob, new JobParameters());				//email late borrowings job 
			
			System.out.println("Job status " + je.getStatus());
			System.out.println("Job complete");
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
			System.out.println("job failed");
		}
	}
	
}
