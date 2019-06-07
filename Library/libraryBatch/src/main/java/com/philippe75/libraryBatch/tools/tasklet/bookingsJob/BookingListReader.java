package com.philippe75.libraryBatch.tools.tasklet.bookingsJob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.philippe75.libraryBatch.stub.generated.bookService.BookDto;
import com.philippe75.libraryBatch.stub.generated.bookService.BookSearchService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BookBookingDto;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.Exception_Exception;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.UserAccount;

@Component
public class BookingListReader implements Tasklet, StepExecutionListener{

	/**
	 * The borrowing web service.
	 */
	@Autowired
	private BorrowingService borrowingService;
	
	@Autowired
	private BookSearchService bookSearchService;
	
	
	private List<BookBookingDto> allActiveBookings;
	private List<BookDto> lbd;
	private List<String> listNameOfBookWithBooking;
	private Map<UserAccount, BookDto> mapMailToSend; 
	
	
	@Override
	public void beforeStep(StepExecution se) {
			listNameOfBookWithBooking = new ArrayList<>();
			try {
				//get list of all active bookings 
				allActiveBookings = (List<BookBookingDto>)borrowingService.getAllNotEndedBookings().getItem();
				
				//get list of names of books that have bookings 
				allActiveBookings.forEach((e) -> {
					if(!listNameOfBookWithBooking.contains(e.getBookName())) {
						listNameOfBookWithBooking.add(e.getBookName());
					}
				});
			} catch (Exception_Exception e) {
				e.printStackTrace();
			} catch (LibraryServiceException_Exception e) {
				e.printStackTrace();
			}
			
			//get list of book having bookings
			
	}

	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		mapMailToSend = new HashMap<>();
		//For each book with bookings  
		listNameOfBookWithBooking.forEach((e) ->{
			try {
				
				//check number of copies available
				List<BookDto> lbd = bookSearchService.getListBookByName(e).getItem();
				lbd = lbd
						.stream()
						.filter( j -> j.isAvailable() == true)
						.collect(Collectors.toList());
				int availableCopieNumber = lbd.size();
				
				//if at least 1 copie is available
				if(availableCopieNumber != 0) {
					
					//get all bookings for a book 
					com.philippe75.libraryBatch.stub.generated.borrowingServ.BookDto book = new com.philippe75.libraryBatch.stub.generated.borrowingServ.BookDto();
					book.setName(lbd.get(0).getName());
					book.setAuthor(lbd.get(0).getAuthor());
					List<BookBookingDto> bookingsForAbook = borrowingService.getAllBookingsForABook(book).getItem();
					//remove the ended bookings
					bookingsForAbook = bookingsForAbook
							.stream()
							.filter( k -> k.isEnded() == false)
							.collect(Collectors.toList());
					//get order of the queue 
					Collections.sort(bookingsForAbook, new Comparator<BookBookingDto>() {
						
						@Override
						public int compare(BookBookingDto obb, BookBookingDto obb2) {
							return obb.getId().compareTo(obb2.getId());
						}
					});
					int memberInTheQueueNumber = bookingsForAbook.size();
					//create map with user to who a mail needs to be sent
					int i = 0;
					while (i < availableCopieNumber && i < memberInTheQueueNumber ) {
						//only if a mail hasn't been sent yet 
						if(bookingsForAbook.get(i).getMailSentDate() == null) {
							mapMailToSend.put(bookingsForAbook.get(i).getUserAccount(),lbd.get(0));
						}
						i++;
					}
				}
				
				
				
			} catch (com.philippe75.libraryBatch.stub.generated.bookService.LibraryServiceException_Exception e1) {
				e1.printStackTrace();
			} catch (LibraryServiceException_Exception e1) {
				e1.printStackTrace();
			} catch (Exception_Exception e1) {
				e1.printStackTrace();
			}
		});
		
		
		return null;
	}
	
	@Override
	public ExitStatus afterStep(StepExecution se) {
		// TODO Auto-generated method stub
		return null;
	}



}
