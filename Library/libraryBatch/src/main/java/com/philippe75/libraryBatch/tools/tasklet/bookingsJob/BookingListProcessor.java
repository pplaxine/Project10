package com.philippe75.libraryBatch.tools.tasklet.bookingsJob;

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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.philippe75.libraryBatch.tools.model.BookAvailableEmail;
import com.philippe75.libraryBatch.tools.tasklet.EmailHelper;


@Component
public class BookingListProcessor extends EmailHelper implements Tasklet, StepExecutionListener{

	/**
	 * Allow access to DataBaseConf.properties
	 *
	 * @see #dataSource()
	 */
	@Autowired
	Environment env;
	
	private List<BookAvailableEmail> listBookAvailableEmail;
	
	@Override
	public void beforeStep(StepExecution se) {
		ExecutionContext ec = se.getJobExecution()
								.getExecutionContext();
		listBookAvailableEmail = (List<BookAvailableEmail>)ec.get("listBookAvailableEmail");
			
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		listBookAvailableEmail.forEach(e->{
			//compose email 
			String emailContent = composeEmail(e);
			e.setEmailContent(emailContent);

			
			
	});
		
		return RepeatStatus.FINISHED;
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return ExitStatus.COMPLETED;
	}


	
	//UTILITY METHODS 
	/**
	 * Compose a personilized email.
	 * 
	 * @param bookAvailableEmail the email object.
	 * @return email in string format 
	 */
	private String composeEmail(BookAvailableEmail bookAvailableEmail) {
		
		StringBuilder sb = new StringBuilder();
		
		try(BufferedReader br = Files.newBufferedReader(Paths.get(env.getProperty("mail.template.book.available.path")))){
			br.lines().forEach(e-> sb.append(e));
			replaceAll(sb, "$$userName", bookAvailableEmail.getUserAccount().getFirstName());
			replaceAll(sb, "$$book", bookAvailableEmail.getBookDto().getName() + " de " + bookAvailableEmail.getBookDto().getAuthor());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
