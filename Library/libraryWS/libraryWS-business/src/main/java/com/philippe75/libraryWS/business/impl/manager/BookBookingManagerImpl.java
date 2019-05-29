package com.philippe75.libraryWS.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;

import com.philippe75.libraryWS.business.contract.manager.BookBookingManager;
import com.philippe75.libraryWS.business.dto.BookBookingDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.BookBooking;
import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

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
			List<BookBooking> lbb, lbbMember;
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
				bookingNumb = lbb.size(); 
				maxBookingForBook = lb.size() * 2;
				
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
				for (BookBooking bbMember : lbbMember) {
					if(bbMember.getBookName().equals(bookBooking.getBookName())) {
						throw new LibraryServiceException("BookingAlreadyMadeException", libraryServiceFaultFactory("4852", "The booking for the "+ bookBooking.getBookName() +" has already been made."));
					}
				}
				
				result = getDaoHandler().getBookBookingDao().createBookBooking(bookBooking);
					
				
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
			} 
		}
		return result;
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
	
	
	
	//---- UTILITY METHODS ----------------------------------------------------------------
	/**
	 * Transform model objects fetched from database to data transfer object.   
	 * 
	 * @param bookBooking object fetched from the data layer. 
	 * @return bbd Dto object of {@link BookBooking}.  
	 */
	protected BookBookingDto bookBookingModelToDto(BookBooking bookBooking) {
		
		BookBookingDto bbd = new BookBookingDto();
		bbd.setId(bookBooking.getId());
		bbd.setBookName(bookBooking.getBookName());
		bbd.setBookAuthor(bookBooking.getBookAuthor());
		bbd.setUserAccount(bookBooking.getUserAccount());
		bbd.setMailSentDate(bookBooking.getMailSentDate());
		bbd.setEnded(bookBooking.isEnded());
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
		bb.setId(bookBookingDto.getId());
		bb.setBookName(bookBookingDto.getBookName());
		bb.setBookAuthor(bookBookingDto.getBookAuthor());
		bb.setUserAccount(bookBookingDto.getUserAccount());
		bb.setMailSentDate(bookBookingDto.getMailSentDate());
		bb.setEnded(bookBookingDto.getEnded());
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
