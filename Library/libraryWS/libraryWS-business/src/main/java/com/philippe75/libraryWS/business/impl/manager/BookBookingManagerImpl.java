package com.philippe75.libraryWS.business.impl.manager;

import javax.inject.Named;

import com.philippe75.libraryWS.business.contract.manager.BookBookingManager;
import com.philippe75.libraryWS.business.dto.BookBookingDto;
import com.philippe75.libraryWS.model.book.BookBooking;

/**
 * <b>Implements BookBooking Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("bookBookingManager")
public class BookBookingManagerImpl extends AbstractManager implements BookBookingManager{

	@Override
	public BookBooking createBooking() {
		// TODO Auto-generated method stub
		return null;
	}
	
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
	
	protected BookBooking bookBookingDtoToModel(BookBookingDto bookBooking) {
		BookBooking bb = new BookBooking();
		bookBooking.setId(bb.getId());
		bookBooking.setBookName(bb.getBookName());
		bookBooking.setBookAuthor(bb.getBookAuthor());
		bookBooking.setUserAccount(bb.getUserAccount());
		bookBooking.setMailSentDate(bb.getMailSentDate());
		bookBooking.setEnded(bb.isEnded());
		return bb;
	}

}
