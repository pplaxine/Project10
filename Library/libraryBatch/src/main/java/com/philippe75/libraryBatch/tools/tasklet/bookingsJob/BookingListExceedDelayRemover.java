package com.philippe75.libraryBatch.tools.tasklet.bookingsJob;

import java.util.Calendar;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.LibraryServiceException_Exception;

@Component
public class BookingListExceedDelayRemover implements Tasklet, StepExecutionListener{

	//Modified from 48h for the example 
	private final int TYPE_OF_DURATION = Calendar.SECOND;
	private final int QUANTITY_OF_TYPE = 2;
	
	/**
	 * The borrowing web service.
	 */
	@Autowired
	private BorrowingService borrowingService;
	
	@Override
	public void beforeStep(StepExecution se) {
		//no before step
	}


	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cct) throws Exception {
		try {
			borrowingService.endAllActiveBookingsExceededOf(TYPE_OF_DURATION, QUANTITY_OF_TYPE);
		} catch (LibraryServiceException_Exception e) {
			e.printStackTrace();
		}
		return RepeatStatus.FINISHED;
	}
	
	@Override
	public ExitStatus afterStep(StepExecution se) {
		return ExitStatus.COMPLETED;
	}

}
