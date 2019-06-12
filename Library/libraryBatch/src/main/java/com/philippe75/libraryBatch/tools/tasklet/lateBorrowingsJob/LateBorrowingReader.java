package com.philippe75.libraryBatch.tools.tasklet.lateBorrowingsJob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class LateBorrowingReader implements Tasklet, StepExecutionListener{
	
	/**
	 * The borrowing web service.
	 */
	@Autowired
	private BorrowingService borrowingService; 
	/**
	 * list of late borrowings {@link BorrowingDto} 
	 */
	private List<BorrowingDto> listBorrowingDto;
	/**
	 * list of late borrowings {@link Borrowing} 
	 */
	private List<Borrowing> listBorrowing;
	/**
	 * list of Emails containing the late {@link Borrowing} to be sent. 
	 */
	private Map<String, BorrowingEmail> lateBorrowingEmailMap;
	
	@Override
	public void beforeStep(StepExecution se) {
		listBorrowing = new ArrayList<>();
		try {
			listBorrowingDto = (List<BorrowingDto>) borrowingService.getAllLateBorrowings().getItem();
			
			listBorrowingDto.forEach(e -> listBorrowing.add(Borrowing.dtoToModel(e)));
		} catch (LibraryServiceException_Exception e) {
			e.printStackTrace();
		}
		lateBorrowingEmailMap = new HashMap<>();
	}
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		
		listBorrowing.forEach((e)->{
			BorrowingEmail lbe;
			//if a user already in the map we just add the additional late book
			if(lateBorrowingEmailMap.containsKey(e.getUserAccount().getUserMemberId())) {
				lbe = lateBorrowingEmailMap.get(e.getUserAccount().getUserMemberId());
				lbe.getListBorrowing().add(e);	
				lateBorrowingEmailMap.replace(e.getUserAccount().getUserMemberId(), lbe);
			}else {
				lbe = new BorrowingEmail();
				lbe.setUserAccount(e.getUserAccount());
				lbe.getListBorrowing().add(e);			
				lateBorrowingEmailMap.put(e.getUserAccount().getUserMemberId(), lbe);
			}
		});
		
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution se) {
		se.getJobExecution()
			.getExecutionContext()
			.put("lateBorrowingEmailMap", lateBorrowingEmailMap );
		
		return ExitStatus.COMPLETED;
	}

}
