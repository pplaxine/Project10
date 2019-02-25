package com.philippe75.libraryBatch.tools.tasklet;

import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.philippe75.libraryBatch.emailService.EmailService;
import com.philippe75.libraryBatch.tools.model.LateBorrowingEmail;

@Component
public class LateBorrowingEmailSender implements Tasklet, StepExecutionListener {

	@Autowired
	EmailService emailService;
	
	private Map<String, LateBorrowingEmail> lateBorrowingEmailMap;
	
	@Override
	public void beforeStep(StepExecution se) {
		ExecutionContext ec = se.getJobExecution()
				.getExecutionContext();
		lateBorrowingEmailMap = (Map<String, LateBorrowingEmail>)ec.get("lateBorrowingEmailMap");
		
	}
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		
		lateBorrowingEmailMap.values().forEach(e-> {
			emailService.sendSimpleMessage("p.plaxine@orange.fr", e.getUserAccount().getFirstName() + " Vous etes en retard ...", e.getEmailContent());
		});
		
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		return ExitStatus.COMPLETED;
	}



}
