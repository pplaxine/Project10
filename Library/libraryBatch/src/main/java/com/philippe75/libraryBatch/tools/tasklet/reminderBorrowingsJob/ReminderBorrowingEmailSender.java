package com.philippe75.libraryBatch.tools.tasklet.reminderBorrowingsJob;

import java.util.List;
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

import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.Exception_Exception;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryBatch.stub.generated.mailServ.EmailService;
import com.philippe75.libraryBatch.tools.model.BorrowingEmail;


/**
 * <b>Tasklet EmailSender {@link Tasklet}</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Component
public class ReminderBorrowingEmailSender implements Tasklet, StepExecutionListener {
	
	/**
	 * The email sender web service.
	 */
	@Autowired
	private EmailService emailService;
	
	/**
	 * The borrowing web service.
	 */
	@Autowired
	private BorrowingService borrowingService; 
	
	/**
	 * All the Email to be sent.
	 */
	private List<BorrowingEmail> reminderBorrowingEmailList;
	private BorrowingDto borrowing;
	
	@Override
	public void beforeStep(StepExecution se) {
		
		ExecutionContext ec = se.getJobExecution()
				.getExecutionContext();
		reminderBorrowingEmailList = (List<BorrowingEmail>) ec.get("reminderBorrowingEmailList");
		
	}
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		
		reminderBorrowingEmailList.forEach(e-> {
			
			borrowing = new BorrowingDto();
			borrowing.setId(e.getListBorrowing().get(0).getId());
			borrowing.setReminderMailSent(true);
			try {
				borrowingService.updateBorrowingReminderMailStatus(borrowing);
			} catch (Exception_Exception e1) {
				e1.printStackTrace();
			} catch (LibraryServiceException_Exception e1) {
				e1.printStackTrace();
			}
			
			//SET ON MY EMAIL ADRESS FOR TESTING 
			//emailService.sendSimpleMessage(e.getUserAccount().getEmail(), e.getUserAccount().getFirstName() + " Vous etes en retard ...", e.getEmailContent());
			emailService.sendSimpleMessage("p.plaxine@orange.fr", e.getUserAccount().getFirstName() + " Votre location arrive à échéance ...", e.getEmailContent());
		});
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		return ExitStatus.COMPLETED;
	}



}
