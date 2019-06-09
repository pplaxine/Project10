package com.philippe75.libraryWS.business.impl.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.persistence.NoResultException;

import com.philippe75.libraryWS.business.contract.manager.BookBookingManager;
import com.philippe75.libraryWS.business.dto.BookBookingDto;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.BookBooking;
import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;
import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Implements BookBooking Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("bookBookingManager")
public class BookBookingManagerImpl extends AbstractManager implements BookBookingManager{

	/**
	 * Method that creates new booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of a new booking.
	 * 
	 * @return int id of the newly created BookBooking.
	 */
	@Override
	public int createBooking(BookBookingDto bookBookingDto) throws LibraryServiceException, Exception {
		int result = 0;
		
		if(bookBookingDto != null) {
			int maxBookingForBook, bookingNumb;
			List<Book> lb;
			List<BookBooking> lbb,lbbNotEndedForBook, lbbMember;
			List<Borrowing> lbrw;
			BookBooking bookBooking = bookBookingDtoToModel(bookBookingDto);	
		
			String userMemberId = bookBooking.getUserAccount().getUserMemberId();
			
			try {
				//Règle gestion: check if all books are borrowed (booking only when all books are borrowed) ----
				lb = getDaoHandler().getBookDao().getListBookByName(bookBooking.getBookName());
				if((bookAvailabilityChecker(lb).size()) != 0 ){
					throw new LibraryServiceException("BookAvailableException", libraryServiceFaultFactory("7562", "There is still book available to borrow for the reference: "+ bookBooking.getBookName() + "."));
				}
				
				//Règle gestion : Check if max booking is reached (no more than the double of exemplar number) ----
				lbb = getDaoHandler().getBookBookingDao().getAllBookingsForABook(lb.get(0));
				lbbNotEndedForBook = lbb
						.stream()
						.filter(e -> e.isEnded() == false)
						.collect(Collectors.toList());
				bookingNumb = lbbNotEndedForBook.size(); 
				maxBookingForBook = lb.size() * 2;		//TODO : make int final 
				
				if(maxBookingForBook <= bookingNumb) {
					throw new LibraryServiceException("MaxBookingReachedException", libraryServiceFaultFactory("4635", "No more booking possible for this book. The "+maxBookingForBook +" bookings already in the queue."));
				}
				
				//Règle gestion : Check if member as already rented this book 
				lbrw = getDaoHandler().getBorrowingDao().getAllBorrowingForUser(userMemberId);
				if(isThisBookInTheBorrowings(lbrw, bookBookingDto.getBookName())) {
					throw new LibraryServiceException("BookAlreadyInMemberBorrowingsException", libraryServiceFaultFactory("4862", "The book "+ bookBooking.getBookName() +" is already in member active borrowings."));
				}
				
				//Règle gestion : Check if member as already made a booking for this book 
				lbbMember = getDaoHandler().getBookBookingDao().getAllBookingsForMember(userMemberId);
				
				//filter only active bookings
				lbbMember = lbbMember
						.stream()
						.filter(e -> e.isEnded() == false)
						.collect(Collectors.toList());
				
				for (BookBooking bbMember : lbbMember) {
					if(bbMember.getBookName().equals(bookBooking.getBookName())) {
						throw new LibraryServiceException("BookingAlreadyMadeException", libraryServiceFaultFactory("4852", "The booking for the "+ bookBooking.getBookName() +" has already been made."));
					}
				}
				UserAccount ua = getDaoHandler().getUserAccountDao().getUserAccountByMemberId(userMemberId);
				//----------------------------------->
				bookBooking.setUserAccount(ua);
				result = getDaoHandler().getBookBookingDao().createBookBooking(bookBooking);
				
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
			} 
		}
		return result;
	}
	
	/**
	 * Method that adds a mail sending date to a booking.  
	 * 
	 * @param bookBookingId id of the Booking where date must be added .
	 * 
	 * @return Integer Id of {@link BookBooking} updated.
	 */
	@Override
	public void updateMailDateBooking(int bookBookingId) throws LibraryServiceException {
		
		try {
			getDaoHandler().getBookBookingDao().updateMailDateBooking(bookBookingId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
		}
	}
	
	/**
	 * Method that ends booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of booking to end.
	 */
	@Override
	public void endBooking(int bookBookingId) throws LibraryServiceException {
		
		try {
			getDaoHandler().getBookBookingDao().endBookBooking(bookBookingId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
		}
	}
	
	/**
	 * Method that ends, all active {@link BookBooking} that have a date that is exceed, by the amount of time passed in parameter, the date of now.  
	 *
	 * @param typeFiel Calendar.type example Calendar.WEEK_OF_YEAR 
	 * @param quantity quantity of that type example 2 (Weeks) 
	 */
	@Override
	public void endAllActiveBookingsExceededOf(Integer typeField, Integer quantity) throws LibraryServiceException {
		
		if(typeField != null && quantity != null) {
			List<BookBooking> lbb;
			
			//Date of now removed of qty passed in param  
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(typeField, -quantity);
			Date date = cal.getTime();
			
			try {
				//get all bookings before that date
				lbb = getDaoHandler().getBookBookingDao().getAllActiveBookingsBeforeThisDate(date);
				for (BookBooking bb : lbb) {
					//end all bookings 
					getDaoHandler().getBookBookingDao().endBookBooking(bb.getId());
				}
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
			}
		}else {
			throw new LibraryServiceException("EmptyValueException", libraryServiceFaultFactory("1422","You must complete typeField and quantity Values"));
		}
		
	}
	
	
	/**
	 * Method that gets, the waiting list of members for a book.  
	 * 
	 * @param book the book.
	 * 
	 * @return List<BookBookingDto> list of {@link BookBookingDto} for all copies of this book.
	 * @throws LibraryServiceException 
	 */
	@Override
	public List<BookBookingDto> getAllBookingsForABook(BookDto bookDto) throws LibraryServiceException , Exception {
		if(bookDto != null) {
			List<BookBooking> lbb;
			List<BookBookingDto> lbbd = new ArrayList<>();
			Book book = bookDtoToModel(bookDto);
			try {
				lbb = getDaoHandler().getBookBookingDao().getAllBookingsForABook(book);
				lbb.forEach(e -> lbbd.add(bookBookingModelToDto(e)));
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
			} 
			return lbbd;
		}
		return null;
	}

	/**
	 * Method that gets, all the bookings of a members.  
	 * 
	 * @param String user member id.
	 * 
	 * @return List<BookBookingDto> list of all {@link BookBookingDto} for a user.
	 * @throws Exception 
	 */
	@Override
	public List<BookBookingDto> getAllBookingsForMember(String userMemberId) throws LibraryServiceException, Exception {
		if(userMemberId != null) {
			List<BookBooking> lbb;
			List<BookBookingDto> lbbd = new ArrayList<>();
			try {
				lbb = getDaoHandler().getBookBookingDao().getAllBookingsForMember(userMemberId);
				lbb.forEach(e -> lbbd.add(bookBookingModelToDto(e)));
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
			} 
			return lbbd;
		}
		return null;
	}
	
	/**
	 * Method that gets, all the active bookings.  
	 * 
	 * @return List<BookBookingDto> list of all active {@link BookBookingDto}.
	 * @throws Exception 
	 */
	@Override
	public List<BookBookingDto> getAllNotEndedBookings() throws LibraryServiceException, Exception {
		
		List<BookBooking> lbb;
		List<BookBookingDto> lbbd = new ArrayList<>();
		try {
			lbb = getDaoHandler().getBookBookingDao().getAllNotEndedBookings();
			lbb.forEach(e -> lbbd.add(bookBookingModelToDto(e)));
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
		} 
		return lbbd;
	}
	
	
	//---- UTILITY METHODS ----------------------------------------------------------------
	/**
	 * Transform model objects fetched from database to data transfer object.   
	 * 
	 * @param bookBooking object fetched from the data layer. 
	 * @return BookBookingDto Dto object of {@link BookBooking}.  
	 */
	protected BookBookingDto bookBookingModelToDto(BookBooking bookBooking) {
		
		BookBookingDto bbd = new BookBookingDto();
		if(bookBooking.getId() != null) {

			if(bookBooking.getId() != null) {
				bbd.setId(bookBooking.getId());
			}
			
			if(bookBooking.getBookName() != null) {
				bbd.setBookName(bookBooking.getBookName());
			}
			if(bookBooking.getBookAuthor() != null) {
				bbd.setBookAuthor(bookBooking.getBookAuthor());
			}
			if(bookBooking.getUserAccount() != null) {
				bbd.setUserAccount(bookBooking.getUserAccount());
			}
			if(bookBooking.getMailSentDate() != null) {
				bbd.setMailSentDate(bookBooking.getMailSentDate());
			}
			//Can't be null
			bbd.setEnded(bookBooking.isEnded());
		}
		
		return bbd;
	}
	
	/**
	 * Transform dto objects to model object.   
	 * 
	 * @param bookBookingDto  {@link BookBookingDto} . 
	 * @return bb {@link BookBooking}.  
	 */
	protected BookBooking bookBookingDtoToModel(BookBookingDto bookBookingDto) {
		BookBooking bb = new BookBooking();
		if(bookBookingDto != null) {
			if(bookBookingDto.getId() != null) {
				bb.setId(bookBookingDto.getId());
			}
	
			if(bookBookingDto.getBookName() != null) {
				bb.setBookName(bookBookingDto.getBookName());
			}
			if(bookBookingDto.getBookAuthor() != null) {
				bb.setBookAuthor(bookBookingDto.getBookAuthor());
			}
			if(bookBookingDto.getUserAccount() != null) {
				bb.setUserAccount(bookBookingDto.getUserAccount());
			}
			if(bookBookingDto.getMailSentDate() != null) {
				bb.setMailSentDate(bookBookingDto.getMailSentDate());
			}
			if(bookBookingDto.getEnded() != null) {
				bb.setEnded(bookBookingDto.getEnded());
			}
		}
		return bb;
	}


	
	/**
	 * Check if a book is in active borrowings of the borrowings list.   
	 * 
	 * @param lb list of all borrowings (active or not) {@link Borrowing} of a member.
	 * @param bookName name of the book to be checked.
	 * 
	 * return 
	 */
	protected boolean isThisBookInTheBorrowings(List<Borrowing> lb, String bookName) {
		boolean result = false;
		for (Borrowing b : lb) {
			if(b.getEffectiveEndDate() == null && b.getBook().getName() == bookName) {
				result = true;
			}
		}
		return result;
	}













}
