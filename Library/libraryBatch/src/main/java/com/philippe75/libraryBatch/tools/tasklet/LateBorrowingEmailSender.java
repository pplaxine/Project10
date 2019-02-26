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

import com.philippe75.libraryBatch.stub.generated.mailServ.EmailService;
import com.philippe75.libraryBatch.tools.model.LateBorrowingEmail;


/**
 * <b>Tasklet EmailSender {@link Tasklet}</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Component
public class LateBorrowingEmailSender implements Tasklet, StepExecutionListener {
	
	/**
	 * The email sender web service.
	 */
	@Autowired
	private EmailService emailService;
	
	/**
	 * All the Email to be sent.
	 */
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
			//SET ON MY EMAIL ADRESS FOR TESTING 
			//emailService.sendSimpleMessage(e.getUserAccount().getEmail(), e.getUserAccount().getFirstName() + " Vous etes en retard ...", e.getEmailContent());
			emailService.sendSimpleMessage("p.plaxine@orange.fr", e.getUserAccount().getFirstName() + " Vous etes en retard ...", e.getEmailContent());
		});
		
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		return ExitStatus.COMPLETED;
	}



}
