package com.philippe75.libraryBatch.tools.tasklet;

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
import com.philippe75.libraryBatch.tools.model.LateBorrowingEmail;

@Component
public class LateBorrowingReader implements Tasklet, StepExecutionListener{
	
	@Autowired
	private BorrowingService borrowingService; 
	private List<BorrowingDto> listBorrowingDto;
	private List<Borrowing> listBorrowing;
	private Map<String, LateBorrowingEmail> lateBorrowingEmailMap;
	
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
			LateBorrowingEmail lbe;
			//if a user already in the map we just add the additional late book
			if(lateBorrowingEmailMap.containsKey(e.getUserAccount().getUserMemberId())) {
				lbe = lateBorrowingEmailMap.get(e.getUserAccount().getUserMemberId());
				lbe.getListBorrowing().add(e);	
				lateBorrowingEmailMap.replace(e.getUserAccount().getUserMemberId(), lbe);
			}else {
				lbe = new LateBorrowingEmail();
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
