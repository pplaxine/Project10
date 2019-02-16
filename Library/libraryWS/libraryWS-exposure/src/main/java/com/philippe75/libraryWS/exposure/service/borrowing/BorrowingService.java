package com.philippe75.libraryWS.exposure.service.borrowing;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

/**
 * <b>Borrowing service end point Interface.</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface BorrowingService {

	/**
	 * Method that gets, all the borrowings of a user.  
	 * 
	 * @param userMemeberId the member id of a user
	 * @return List<BorrowingDto> list of {@link Borrowing} of a user.
	 */
	@WebMethod
	List<BorrowingDto> getAllBorrowingForUser(String userMemberId) throws LibraryServiceException; 
	
	/**
	 * Method that extend borrowing supposed end date. Also check first if an extention has already benn made.  
	 * 
	 * @param Borrowing the borrowing to update.
	 */
	@WebMethod
	void extendBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException;
	
	/**
	 * Method get the borrowing object.  
	 * 
	 * @param borrowingId the borrowing id.
	 * @return BorrowingDto the {@link Borrowing} dto object corresponding to the id.
	 */
	@WebMethod
	BorrowingDto getBorrowingById(Integer borrowingId) throws LibraryServiceException;
	
	/**
	 * Method get that creates new borrowing.  
	 * 
	 * @param borrowingDto the dto object of a new borrowing.
	 */
	@WebMethod
	void createBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException;
	
	/**
	 * Method that ends a borrowing when user returns a book.   
	 * 
	 * @param borrowingDto the borrowing comming to a end.
	 */
	@WebMethod
	void endBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException;
}
