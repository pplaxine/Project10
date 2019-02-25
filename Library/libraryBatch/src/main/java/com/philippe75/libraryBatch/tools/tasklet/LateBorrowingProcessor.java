package com.philippe75.libraryBatch.tools.tasklet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryBatch.tools.model.Borrowing;
import com.philippe75.libraryBatch.tools.model.LateBorrowingEmail;

@Component
@PropertySource("classpath:/Config.properties")
public class LateBorrowingProcessor implements Tasklet, StepExecutionListener{
	
	/**
	 * Allow access to DataBaseConf.properties
	 *
	 * @see #dataSource()
	 */
	@Autowired
	Environment env;
	
	private Map<String, LateBorrowingEmail> lateBorrowingEmailMap;
	
	
	@Override
	public void beforeStep(StepExecution se) {
		ExecutionContext ec = se.getJobExecution()
								.getExecutionContext();
		lateBorrowingEmailMap = (Map<String, LateBorrowingEmail>)ec.get("lateBorrowingEmailMap");
	}
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		
		lateBorrowingEmailMap.values().forEach(e ->{
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
		
		try(BufferedReader br = Files.newBufferedReader(Paths.get(env.getProperty("mail.template.path")))){
			br.lines().forEach(e-> sb.append(e));
			replaceAll(sb, "$$userName", name);
			replaceAll(sb, "$$att", listBorrowing.size()>1?"les":"l'");
			replaceAll(sb, "$$pluriel", listBorrowing.size()>1?"s":"");
			replaceAll(sb, "$$listBook", generateHTMLBookList(listBorrowing));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * Creates from a list of {@link BorrowingDto} a list in HTML.
	 * 
	 * @param listBorrowingDto the list of late borrowings for a user 
	 * @return HTML list in String format
	 */
	private String generateHTMLBookList(List<Borrowing> listBorrowing) {
		StringBuilder sb = new StringBuilder();
		sb.append("<ol>");
		listBorrowing.forEach(e->{
			
			sb.append("<li>"+ e.getBook().getName()
							+ " emprunté le "
							+ formatGC(e.getStartDate() , "dd MMMM yyyy") 
							+ " avec un retour prévu le "
							+ formatGC((e.getSecondSupposedEndDate() != null?e.getSecondSupposedEndDate():e.getSupposedEndDate()), "dd MMMM yyyy")
							+"</li>");
		});
		sb.append("</ol>");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * Format GregorianCalendar to String with the desired format.   
	 * 
	 * @param calendar {@link GregorianCalendar} the date to extract
	 * @param format the format desired
	 * @return String the time in a String with in the desired format
	 */
	public String formatGC(Date date, String format){
	    SimpleDateFormat fmt = new SimpleDateFormat(format);
	    fmt.set2DigitYearStart(date);
	    String dateFormatted = fmt.format(date.getTime());
	    return dateFormatted;
	}
	
	
	/**
	 * Replace all the occurrences of a String by another String.
	 * 
	 * @param builder {@link StringBuilder}
	 * @param from the String to replace 
	 * @param to the String to replace with 
	 */
	private void replaceAll(StringBuilder builder, String from, String to)
	{
	    int index = builder.indexOf(from);
	    while (index != -1)
	    {
	        builder.replace(index, index + from.length(), to);
	        index += to.length(); // Move to the end of the replacement
	        index = builder.indexOf(from, index);
	    }
	}


}
