package com.philippe75.libraryBatch.tools.tasklet.reminderBorrowingsJob;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.Exception_Exception;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryBatch.tools.model.Borrowing;
import com.philippe75.libraryBatch.tools.model.BorrowingEmail;

/**
 * <b>Tasklet Reader {@link Tasklet}</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Component
public class ReminderBorrowingReader implements Tasklet, StepExecutionListener{
	
	private final int CALENDAR_TYPE = Calendar.DAY_OF_YEAR;
	private final int QUANTITY_OF_TYPE = 5;
	
	/**
	 * The borrowing web service.
	 */
	@Autowired
	private BorrowingService borrowingService; 
	/**
	 * list of borrowings to be reminded {@link BorrowingDto} 
	 */
	private List<BorrowingDto> listBorrowingDtoToRemindOfAllUsers, listBorrowingDtoToRemindOfOneUser;
	private List<Borrowing> listBorrowingToRemindOfOneUser, finalListBorrowingToRemindOfOneUser;
	private Borrowing mainBorrowingOfOneUser;
	
	/**
	 * list of borrowings to be reminded {@link Borrowing} 
	 */
	//private Map<Borrowing, List<Borrowing>> borrowingToRemindOfOneUserMap; 
	
	/**
	 * list of Emails containing the reminders {@link Borrowing} to be sent. 
	 */
	private List<BorrowingEmail> reminderBorrowingEmailList;
	private BorrowingEmail borrowingEmail;
	
	@Override
	public void beforeStep(StepExecution se) {
		try {
			reminderBorrowingEmailList = new ArrayList<>();
			
			//get all borrowings to be reminded between now and date 
			listBorrowingDtoToRemindOfAllUsers = (List<BorrowingDto>) borrowingService.getAllBorrowingsToBeRemindedWithin(CALENDAR_TYPE, QUANTITY_OF_TYPE).getItem();
		} catch (LibraryServiceException_Exception e) {
			e.printStackTrace();
		} catch (Exception_Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		
		//Date of research   
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(CALENDAR_TYPE, QUANTITY_OF_TYPE);
		Date date = cal.getTime();
		//------------------------
		
		//for each user
		listBorrowingDtoToRemindOfAllUsers.forEach(e->{
			
			//the list of borrowing to be added to mail 
			finalListBorrowingToRemindOfOneUser = new ArrayList<>();

			listBorrowingToRemindOfOneUser = new ArrayList<>();
			
			//The main borrowing 
			mainBorrowingOfOneUser = Borrowing.dtoToModel(e);
			
			//add main borrowing
			finalListBorrowingToRemindOfOneUser.add(mainBorrowingOfOneUser);
			
			String userMemberId = e.getUserAccount().getUserMemberId();
			
			try {
				
				//get all borrowings of a user 
				listBorrowingDtoToRemindOfOneUser = borrowingService.getAllBorrowingForUser(userMemberId).getItem();
				
				//Dto to model object
				listBorrowingDtoToRemindOfOneUser.forEach(j ->{
					listBorrowingToRemindOfOneUser.add(Borrowing.dtoToModel(j));
				});
				
				//filter all ended borrowings of user + remove the main borrowing of the email 
				listBorrowingToRemindOfOneUser = listBorrowingToRemindOfOneUser
												.stream()
												.filter(k -> k.getEffectiveEndDate() == null)
												.filter(k -> k.getId() != mainBorrowingOfOneUser.getId())	//filtrer borrowing avant now + filtrer les borrowing aprés date 
												.collect(Collectors.toList());
				
				//check why case 2 here below no book is saved sd
				
				//remove before now and after date 
				listBorrowingToRemindOfOneUser.forEach(l -> {
					if(l.getSecondSupposedEndDate() != null && l.getSupposedEndDate() != null) {
						if( !(l.getSecondSupposedEndDate().compareTo(new Date()) < 0 ) && !(l.getSecondSupposedEndDate().compareTo(date) > 0 )) {
							finalListBorrowingToRemindOfOneUser.add(l);
						}
					}else if (l.getSupposedEndDate() != null && l.getSecondSupposedEndDate() == null) {
						if( !(l.getSupposedEndDate().compareTo(new Date()) < 0) && !(l.getSupposedEndDate().compareTo(date) > 0)) {
							finalListBorrowingToRemindOfOneUser.add(l);
						}
					}
				});
			} catch (LibraryServiceException_Exception e1) {
				e1.printStackTrace();
			}

			//create de mail object 
			borrowingEmail = new BorrowingEmail();
			borrowingEmail.setListBorrowing(finalListBorrowingToRemindOfOneUser);
			borrowingEmail.setUserAccount(e.getUserAccount());
			
			reminderBorrowingEmailList.add(borrowingEmail);
		});
		
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution se) {
		se.getJobExecution()
			.getExecutionContext()
			.put("reminderBorrowingEmailList", reminderBorrowingEmailList);
		
		return ExitStatus.COMPLETED;
	}

}
