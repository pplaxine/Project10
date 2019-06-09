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
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.UserAccount;

@Named("bookAction")
public class BookAction extends ActionSupport implements SessionAware{
	
	@Inject
	ManagerHandler managerHandler;
	
	private static final int BOOKING_QUEUE_MULTIPLICATOR = 2; 
	private UserAccountDto uad;
	
	//income 
	private String bookDto;
	private String bookName;
	private String bookAuthor;
	//if redirection = 1 (avoid message "list booking is full" when last member of the list redirected for confirmation of booking) 
	private int isRedirection; 
	
	//outcome
	private List<BookDto> listBookByName;
	private List<BookDto> listBookAvailable;
	private BorrowingDto borrowing;
	private List<BookBookingDto> listBookBooking;
	private int waitingListSize, numberOfCopiesOfBook;
	private boolean isBookingFull, isMemberIdentified;
	private Map<String, Object> session;
	private BookBookingDto bookBooking;
	

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
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	public int getIsRedirection() {
		return isRedirection;
	}
	public void setIsRedirection(int isRedirection) {
		this.isRedirection = isRedirection;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
	//METHODS
	public String doListBookByName() {
		String result = ActionSupport.INPUT;
		if(this.bookDto.trim().length() != 0) {
			try {
				
				String bookName = BookDtoManagerImpl.getBookNameOnly(bookDto);
				String bookAuthor = BookDtoManagerImpl.getBookAuthorOnly(bookDto);
				
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
				borrowing = managerHandler.getBorrowingDtoManager().getNextBorrowingToComeToEnd(bookName, bookAuthor);
				
				//get all bookings for a book 
				listBookBooking = managerHandler.getBorrowingDtoManager().getAllNotEndedBookingsForABook(bookName, bookAuthor);
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
		String result = ActionSupport.INPUT;
		if(this.bookAuthor != null && this.bookName != null) {			
			
			//get user member id 
			if(session != null && session.size() != 0) {
				uad = (UserAccountDto)this.session.get("user");
			}
			
			//creation of the BookBookingDto	
			bookBooking = new BookBookingDto();
			UserAccount ua = new UserAccount();
			ua.setUserMemberId(uad.getUserMemberId());
			
			bookBooking.setBookName(bookName);
			bookBooking.setBookAuthor(bookAuthor);
			bookBooking.setUserAccount(ua);
			
			try {
				isRedirection = 1;
				managerHandler.getBorrowingDtoManager().createBooking(bookBooking);
				this.addActionMessage("Votre booking à bien été pris en compte !");
			} catch (com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception e) {
				if(e.getFaultInfo().getFault().getFaultCode().equals("4862")) {
					this.addActionMessage(getText("Vous avez déjà ce livre dans vos locations"));			//TODO: faire internationalisation 
				}else if (e.getFaultInfo().getFault().getFaultCode().equals("4852")) {
					this.addActionMessage(getText("Vous etes déjà dans la file d'attente"));			//TODO: faire internationalisation 
				}else {
					this.addActionMessage(getText("general.failure"));
				}
			} catch (Exception_Exception e) {
				this.addActionMessage(getText("general.failure"));
			}
		}
		
		return result;
	}



	
	
}
