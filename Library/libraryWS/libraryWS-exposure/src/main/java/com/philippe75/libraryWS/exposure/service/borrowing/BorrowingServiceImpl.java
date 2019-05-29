package com.philippe75.libraryWS.exposure.service.borrowing;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.BookBookingDto;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

/**
 * <b>Borrowing service end point Class.</b>
 * 
 * <p>
 * 	  Service related to borrowings of a user.
 * </p>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@WebService(endpointInterface="com.philippe75.libraryWS.exposure.service.borrowing.BorrowingService")
public class BorrowingServiceImpl extends SpringBeanAutowiringSupport implements BorrowingService{
	
	/**
	 * Access to business layer.
	 * 
	 * @see ManagerHandler
	 */
	@Inject
	private ManagerHandler managerHandler;
	
	/**
	 * Method that gets, all the borrowings of a user.  
	 * 
	 * @param userMemeberId the member id of a user
	 * @return List<BorrowingDto> list of {@link Borrowing} of a user.
	 */
	@Override
	public List<BorrowingDto> getAllBorrowingForUser(String userMemberId) throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return (List<BorrowingDto>)managerHandler.getBorrowingManager().getAllBorrowingForUser(userMemberId);
	}

	/**
	 * Method that gets, all the borrowings of a book.
	 * 
	 * @param userMemberID the user member id of the user
	 * @return List<BorrowingDto> list of Dto object of {@link Borrowing} of a user.
	 */
	@Override
	public List<BorrowingDto> getAllBorrowingForBook(BookDto bookDto) throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return managerHandler.getBorrowingManager().getAllBorrowingForBook(bookDto);
	}
	
	/**
	 * Method that extend borrowing supposed end date. Also check first if an extention has already benn made.  
	 * 
	 * @param Borrowing the borrowing to update.
	 */
	@Override
	public void extendBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		managerHandler.getBorrowingManager().extendBorrowing(borrowingDto);
	}

	/**
	 * Method get the borrowing object.  
	 * 
	 * @param borrowingId the borrowing id.
	 * @return BorrowingDto the {@link Borrowing} dto object corresponding to the id.
	 */
	@Override
	public BorrowingDto getBorrowingById(Integer borrowingId) throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return (BorrowingDto)managerHandler.getBorrowingManager().getBorrowingById(borrowingId);
	}

	/**
	 * Method get that creates new borrowing.  
	 * 
	 * @param borrowingDto the dto object of a new borrowing.
	 */
	@Override
	public void createBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException, Exception {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		managerHandler.getBorrowingManager().createBorrowing(borrowingDto);
	}
	
	/**
	 * Method that ends a borrowing when user returns a book.   
	 * 
	 * @param borrowingDto the borrowing comming to a end.
	 */
	@Override
	public void endBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		managerHandler.getBorrowingManager().endBorrowing(borrowingDto);
	}

	/**
	 * Method that gets, all the borrowings with the either supposed end date or extended supposed end date overdue.  
	 * 
	 * @return List<BorrowingDto> list of {@link BorrowingDto}.
	 */
	@Override
	public List<BorrowingDto> getAllLateBorrowings() throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return managerHandler.getBorrowingManager().getAllLateBorrowings();
	}

	/**
	 * Method that creates new booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of a new booking.
	 * 
	 * @return int id of the newly created BookBooking.
	 */
	@Override
	public int createBooking(BookBookingDto bookBookingDto) throws LibraryServiceException, Exception {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		return managerHandler.getBookBookingManager().createBooking(bookBookingDto);
	}

	/**
	 * Method that ends booking for a book.  
	 * 
	 * @param bookBookingDto the dto object of booking to end.
	 */
	@Override
	public void endBooking(int bookBookingId) throws LibraryServiceException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		managerHandler.getBookBookingManager().endBooking(bookBookingId);
	}




	
	
}
