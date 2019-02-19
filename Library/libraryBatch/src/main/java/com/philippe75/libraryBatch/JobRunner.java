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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobRunner {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job batchJob1;
	
	
	
	@Scheduled(fixedDelay=30000)
	public void runJobLauncher() {
		
		System.out.println("Starting batch job");
		
		JobExecution je;
		try {
			je = jobLauncher.run(batchJob1, new JobParameters());
			System.out.println("Job status " + je.getStatus());
			System.out.println("Job complete");
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
			System.out.println("job failed");
		}
	}
	
}
