package com.philippe75.libraryBatch.tools.tasklet.bookingsJob;

import java.util.List;

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
import com.philippe75.libraryBatch.tools.model.BookAvailableEmail;

/**
 * <b>Tasklet EmailSender {@link Tasklet}</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Component
public class BookingListEmailSender implements Tasklet, StepExecutionListener {

	/**
	 * The email sender web service.
	 */
	@Autowired
	private EmailService emailService;
	
	private List<BookAvailableEmail> listBookAvailableEmail;

	@Override
	public void beforeStep(StepExecution se) {
		ExecutionContext ec = se.getJobExecution()
				.getExecutionContext();
		listBookAvailableEmail = (List<BookAvailableEmail>)ec.get("listBookAvailableEmail");
	}
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		
		listBookAvailableEmail.forEach(e-> {
			//SET ON MY EMAIL ADRESS FOR TESTING 
			//emailService.sendSimpleMessage(e.getUserAccount().getEmail(), e.getUserAccount().getFirstName() + " Réservation disponible ...", e.getEmailContent());
			emailService.sendSimpleMessage("p.plaxine@orange.fr", e.getUserAccount().getFirstName() + " réservation disponible ...", e.getEmailContent());
		});
		
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		
		return ExitStatus.COMPLETED;
	}


}
