package com.philippe75.libraryBatch.tools.tasklet.reminderBorrowingsJob;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryBatch.tools.model.Borrowing;
import com.philippe75.libraryBatch.tools.model.BorrowingEmail;
import com.philippe75.libraryBatch.tools.tasklet.EmailHelper;

/**
 * <b>Tasklet Processor {@link Tasklet}</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Component
@PropertySource("classpath:/Config.properties")
public class ReminderBorrowingProcessor extends EmailHelper implements Tasklet, StepExecutionListener{

	/**
	 * Allow access to DataBaseConf.properties
	 *
	 * @see #dataSource()
	 */
	@Autowired
	Environment env;
	
	/**
	 * All the Email to be sent.
	 */
	private List<BorrowingEmail> reminderBorrowingEmailList;
	
	@Override
	public void beforeStep(StepExecution se) {
		ExecutionContext ec = se.getJobExecution()
				.getExecutionContext();
		reminderBorrowingEmailList = (List<BorrowingEmail>) ec.get("reminderBorrowingEmailList");
		
	}

	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		reminderBorrowingEmailList.forEach( e-> {
			String emailContent = composeEmail(e.getUserAccount().getFirstName(), e.getListBorrowing());
			e.setEmailContent(emailContent);
		});
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution se) {
		return ExitStatus.COMPLETED;
	}

	
	//UTILITY METHODS 
	/**
	 * Compose a personilized email for a user with all its late borrowings in one email.
	 * 
	 * @param name 	name of the user
	 * @param listBorrowing	list of late borrowing for a user
	 * @return
	 */
	private String composeEmail(String name, List<Borrowing> listBorrowing) {
		
		StringBuilder sb = new StringBuilder();
		
		try(BufferedReader br = Files.newBufferedReader(Paths.get(env.getProperty("mail.template.borrowing.reminder.path")))){
			br.lines().forEach(e-> sb.append(e));
			replaceAll(sb, "$$userName", name);
			replaceAll(sb, "$$pluriel", listBorrowing.size()>2?"s":"");
			replaceAll(sb, "$$mainBorrowingReminder", generateHTMLMainBorrowing(listBorrowing.get(0)));
			if(listBorrowing.size() > 1) {
				replaceAll(sb, "$$otherBorrowingReminder", generateHTMLOtherBorrowingList(listBorrowing));
			}else {
				replaceAll(sb, "$$otherBorrowingReminder", "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	private String generateHTMLMainBorrowing(Borrowing borrowing) {
		StringBuilder sb = new StringBuilder();
		sb.append(borrowing.getBook().getName()
				+ " emprunté le "
				+ formatGC(borrowing.getStartDate() , "dd MMMM yyyy") 
				+ " arrive bientôt à échéance, avec un retour prévu le "
				+ formatGC((borrowing.getSecondSupposedEndDate() != null?borrowing.getSecondSupposedEndDate():borrowing.getSupposedEndDate()), "dd MMMM yyyy")
				+".");
		return sb.toString();
	}
	
	/**
	 * Creates from a list of {@link BorrowingDto} a list in HTML.
	 * 
	 * @param listBorrowingDto the list of other borrowings for a user to be reminded. 
	 * @return HTML list in String format
	 */
	private String generateHTMLOtherBorrowingList(List<Borrowing> listBorrowing) {
		StringBuilder sb = new StringBuilder();
		sb.append("<ol>");
		
		//all but not the first (main borrowing for email) 
		for (int i = 1; i < listBorrowing.size(); i++) {
			Borrowing bd = listBorrowing.get(i);
			
			sb.append("Voici également vos locations avec une échéance de moins de 5 jours :"
					+ "<div>"
					+ "<li>"+ bd.getBook().getName()
					+ " emprunté le "
					+ formatGC(bd.getStartDate() , "dd MMMM yyyy") 
					+ " avec un retour prévu le "
					+ formatGC((bd.getSecondSupposedEndDate() != null?bd.getSecondSupposedEndDate():bd.getSupposedEndDate()), "dd MMMM yyyy")
					+"</li>"
					+ "</div>");
		}
		sb.append("</ol>");
		
		return sb.toString();
	}

}
