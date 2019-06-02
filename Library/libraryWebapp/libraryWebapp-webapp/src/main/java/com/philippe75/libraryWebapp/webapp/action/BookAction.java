package com.philippe75.libraryWebapp.webapp.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.business.impl.manager.BookDtoManagerImpl;
import com.philippe75.libraryWebapp.stub.generated.authServ.UserAccountDto;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.bookServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookBookingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.Exception_Exception;

@Named("bookAction")
public class BookAction extends ActionSupport implements SessionAware{
	
	@Inject
	ManagerHandler managerHandler;
	
	private static final int BOOKING_QUEUE_MULTIPLICATOR = 2; 
	
	//income 
	private String bookDto;
	
	//outcome
	private List<BookDto> listBookByName;
	private List<BookDto> listBookAvailable;
	private BorrowingDto borrowing;
	private List<BookBookingDto> listBookBooking;
	private int waitingListSize, numberOfCopiesOfBook;
	private boolean isBookingFull, isMemberIdentified;
	private Map<String, Object> session;
	private BookBookingDto bookBooking;
	private UserAccountDto uad;

	//G&S
	public List<BookDto> getListBookByName() {
		return listBookByName;
	}
	public List<BookDto> getListBookAvailable() {
		return listBookAvailable;
	}
	public List<BookBookingDto> getListBookBooking() {
		return listBookBooking;
	}
	public BorrowingDto getBorrowing() {
		return borrowing;
	}
	public int getWaitingListSize() {
		return waitingListSize;
	}
	public Boolean getIsBookingFull() {
		return isBookingFull;
	}
	public String getBookDto() {
		return bookDto;
	}
	public boolean getIsMemberIdentified() {
		return isMemberIdentified;
	}
	public void setBookDto(String bookDto) {
		this.bookDto = bookDto;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
	//METHODS
	public String doListBookByName() {
		String result = ActionSupport.INPUT;
		if(bookDto.trim().length() != 0) {
			try {
				
				//Check if Member is identified via session 
				if(session != null && session.size() != 0) {
					uad = (UserAccountDto)this.session.get("user");
					this.isMemberIdentified = true;
				}
				
				//get all copies of a book
				listBookByName = managerHandler.getBookDtoManager().getListBookByName(bookDto);
				numberOfCopiesOfBook = listBookByName.size();
				//get all the available copies of a book 
				listBookAvailable = managerHandler.getBookDtoManager().getListBookAvailableByName(bookDto);
				//get next borrowing to come to an end 
				borrowing = managerHandler.getBorrowingDtoManager().getNextBorrowingToComeToEnd(bookDto);
				//get all bookings for a book 
				listBookBooking = managerHandler.getBorrowingDtoManager().getAllNotEndedBookingsForABook(bookDto);
				waitingListSize = listBookBooking.size();
				if( waitingListSize >= (numberOfCopiesOfBook*BOOKING_QUEUE_MULTIPLICATOR) ) {
					isBookingFull = true;
				}
				
				
				result = ActionSupport.SUCCESS;
			} catch (LibraryServiceException_Exception e) {
				if((e.getMessage()).equals("NoResultException")) {
					this.addActionError(getText("book.failure.noResult"));
				}else {
					this.addActionError(getText("general.failure"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result; 
	}
	
	public String doBookByName() {
	
		try {
			listBookByName = managerHandler.getBookDtoManager().getListBookStartingBy(bookDto);
		} catch (LibraryServiceException_Exception e) {
			if((e.getMessage()).equals("NoResultException")) {
				this.addFieldError("bookDto", getText("login.failure.login"));
			}else {
				this.addActionError(getText("general.failure"));
			}
		}
		
		return ActionSupport.SUCCESS; 
	}
	
	public String doCreateBooking() {
		String result = ActionSupport.ERROR;
		if(bookDto.trim().length() != 0) {
			
			//creation of the BookBookingDto	
			bookBooking = new BookBookingDto();
			
			//bookBooking.setBookName(BookDtoManagerImpl.getBookNameOnly(bookDto));
			//bookBooking.setBookAuthor(BookDtoManagerImpl.getBookAuthorOnly(bookDto));
			bookBooking.setBookName("Phèdre");
			bookBooking.setBookAuthor("Jean Racine");
			
			try {
				managerHandler.getBorrowingDtoManager().createBooking(bookBooking);
				result = ActionSupport.SUCCESS;
			} catch (com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception e) {
				if(e.getFaultInfo().getFault().getFaultCode().equals("4862")) {
					this.addActionError(getText("Vous avez déjà ce livre dans vos locations"));			//TODO: faire internationalisation 
				}else if (e.getFaultInfo().getFault().getFaultCode().equals("4852")) {
					this.addActionError(getText("Vous etes déjà dans la file d'attente"));			//TODO: faire internationalisation 
				}else {
					this.addActionError(getText("general.failure"));
				}
			} catch (Exception_Exception e) {
				this.addActionError(getText("general.failure"));
			}
		}
		
		return result;
	}



	
	
}
