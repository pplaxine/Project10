package com.philippe75.libraryWebapp.business.impl.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.philippe75.libraryWebapp.business.contract.manager.BorrowingDtoManager;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookBookingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.Exception_Exception;

/**
 * <b>Implements BorrowingManager Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("borrowingDtoManager")
public class BorrowingDtoManagerImpl extends AbstractManagerServiceAccess implements BorrowingDtoManager{
	
	BookDto bd;

	/**
	 * Get all the {@link BorrowingDto} of a user.
	 * 
	 * @param userMemberID the user member id of the user
	 * @return List<BorrowingDto> list of Dto object of {@link Borrowing} of a user.
	 */
	@Override
	public List<BorrowingDto> getAllBorrowingForUser(String userMemberId) throws LibraryServiceException_Exception {
		return getBorrowingService().getAllBorrowingForUser(userMemberId).getItem();
	}
	
	/**
	 * Get all the {@link BorrowingDto} of a user.
	 * 
	 * @param userMemberID the user member id of the user
	 * @return List<BorrowingDto> list of Dto object of {@link Borrowing} of a user.
	 */
	@Override
	public List<BorrowingDto> getAllBorrowingForBook(String bookFullName) throws LibraryServiceException_Exception {
		bd = new BookDto();
		bd.setName(BookDtoManagerImpl.getBookNameOnly(bookFullName));
		bd.setAuthor(BookDtoManagerImpl.getBookAuthorOnly(bookFullName));
		
		return getBorrowingService().getAllBorrowingForBook(bd).getItem();
	}
	
	// TODO : UnitTest
	/**
	 * Method that get the next borrowing to come to an end for a book.  
	 * 
	 * @param BookDto bookDto the {@link BookDto} to be checked.
	 * @return BorrowingDto the next {@link BorrowingDto} to come to an end.
	 */
	@Override
	public BorrowingDto getNextBorrowingToComeToEnd(String bookDto) throws LibraryServiceException_Exception {
		//Dto Creation 
		bd = new BookDto();
		bd.setName(BookDtoManagerImpl.getBookNameOnly(bookDto));
		bd.setAuthor(BookDtoManagerImpl.getBookAuthorOnly(bookDto));
		
		List<BorrowingDto> lbd = getBorrowingService().getAllBorrowingForBook(bd).getItem();
		if(lbd.size() != 0) {
			
			//remove ended borrowings 
			List<BorrowingDto> lbdNotEnded= lbd
					.stream()
					.filter(e -> e.getEffectiveEndDate() == null)
					.collect(Collectors.toList());
			
			//sort borrowingsDto by ending date 
			Collections.sort(lbdNotEnded, new Comparator<BorrowingDto>() {
				@Override
				public int compare(BorrowingDto b1, BorrowingDto b2) {
					
					if(b1.getSecondSupposedEndDate() != null && b2.getSecondSupposedEndDate() != null) {
						return b1.getSecondSupposedEndDate().compare(b2.getSecondSupposedEndDate());
					}else if(b1.getSecondSupposedEndDate() == null && b1.getSupposedEndDate() != null && b2.getSecondSupposedEndDate() != null ){
						return b1.getSupposedEndDate().compare(b2.getSecondSupposedEndDate());
					}else if(b1.getSecondSupposedEndDate() == null && b1.getSupposedEndDate() != null && b2.getSecondSupposedEndDate() == null && b2.getSupposedEndDate() != null) {
						return b1.getSupposedEndDate().compare(b2.getSupposedEndDate());
					}else if (b1.getSecondSupposedEndDate() != null && b2.getSecondSupposedEndDate() == null && b2.getSupposedEndDate() != null) {
						return b1.getSecondSupposedEndDate().compare(b2.getSupposedEndDate());
					}
					
					return 0;
				}
			});
			
			return lbdNotEnded.get(0);
		}
		return null;
	}

	/**
	 * Method that extends a borrowing.  
	 * 
	 * @param borrowingId the id of the borrowing
	 * @param numberOfWeek the number of week the borrowing needs to be extended. 
	 * 
	 */
	@Override
	public void extendBorrowing(Integer borrowingId, Integer numberOfWeek) throws LibraryServiceException_Exception {
		
		BorrowingDto bd = getBorrowingService().getBorrowingById(borrowingId); 
		//externaliser code pour tester
		Date supposedEndDate = bd.getSupposedEndDate().toGregorianCalendar().getTime();
		
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(supposedEndDate);
//		cal.add(Calendar.WEEK_OF_YEAR, numberOfWeek);
		Date secondSupposedToEndDate = addSomeTimeToDate(supposedEndDate, Calendar.WEEK_OF_YEAR, numberOfWeek);
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(secondSupposedToEndDate);
		
		try {
			XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			bd.setSecondSupposedEndDate(xgc);
			getBorrowingService().extendBorrowing(bd);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that gets, the waiting list of members for a book.  
	 * 
	 * @param book the book.
	 * 
	 * @return List<BookBooking> list of {@link BookBooking} for all copies of this book.
	 */
	@Override
	public List<BookBookingDto> getAllNotEndedBookingsForABook(String bookFullName) throws LibraryServiceException_Exception, Exception_Exception {
		bd = new BookDto();
		bd.setName(BookDtoManagerImpl.getBookNameOnly(bookFullName));
		bd.setAuthor(BookDtoManagerImpl.getBookAuthorOnly(bookFullName));
		List<BookBookingDto> lbbd = getBorrowingService().getAllBookingsForABook(bd).getItem();
		
		return endedBookingRemover(lbbd); 
	}

	/**
	 * Method that gets, all the bookings of a members.  
	 * 
	 * @param String user member id.
	 * 
	 * @return List<BookBooking> list of all {@link BookBooking} for a user.
	 */
	@Override
	public List<BookBookingDto> getAllBookingsForMember(String userMemberId) throws LibraryServiceException_Exception, Exception_Exception {
		return getBorrowingService().getAllBookingsForMember(userMemberId).getItem();
	}
	
	/**
	 * Method that creates new booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of a new booking.
	 * 
	 * @return int id of the newly created BookBooking.
	 * @throws Exception_Exception 
	 */
	@Override
	public int createBooking(BookBookingDto bookBookingDto) throws LibraryServiceException_Exception, Exception_Exception {
		int result = getBorrowingService().createBooking(bookBookingDto);
		return result;
	}

	/**
	 * Method that ends booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of booking to end.
	 */
	@Override
	public void endBooking(int bookBookingId) throws LibraryServiceException_Exception {
		getBorrowingService().endBooking(bookBookingId);
	}
	
	
	//------------------- UTILITY METHODE -----------------------------
	
	protected Date addSomeTimeToDate(Date initialDate, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(initialDate);
		cal.add(field, amount);
		return cal.getTime();
	}
	
	/**
	 * Remove from a List<BookBookingDto> the booking already ended.   
	 * 
	 * @param listBookBookingDto a list of {@link BookBookingDto}. 
	 * @return List<BookBookingDto> a list of booking that are not ended.
	 */
	public List<BookBookingDto> endedBookingRemover(List<BookBookingDto> listBookBooking){
		List<BookBookingDto> lbb = new ArrayList<>();
		listBookBooking.forEach((e)-> {
			if(e.isEnded() == false) {
				lbb.add(e);
			}
		});
		return lbb;
	}






}
