package com.philippe75.libraryWS.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;

import com.philippe75.libraryWS.business.contract.manager.BookManager;
import com.philippe75.libraryWS.business.contract.manager.BorrowingManager;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;
import com.philippe75.libraryWS.model.library.Library;
import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Implements BookManager Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("borrowingManager")
public class BorrowingManagerImpl extends AbstractManager implements BorrowingManager{

	/**
	 * Get all the {@link BorrowingDto} of a user.
	 * 
	 * @param userMemberID the user member id of the user
	 * @return List<BorrowingDto> list of Dto object of {@link Borrowing} of a user.
	 */
	@Override
	public List<BorrowingDto> getAllBorrowingForUser(String userMemberId) throws LibraryServiceException {
		
		List<Borrowing> lb;
		List<BorrowingDto> lbd = new ArrayList<>();

			try {
				lb = getDaoHandler().getBorrowingDao().getAllBorrowingForUser(userMemberId);
				lb.forEach(e -> lbd.add(borrowingModelToDto(e)));
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
			} 

		return lbd;
	}
	
	/**
	 * Transform model objects fetched from database to data transfer object. Also remove the user password att.   
	 * 
	 * @param book object fetched from the data layer. 
	 * @return borrowingDto Dto object of {@link Borrowing}.  
	 */
	private BorrowingDto borrowingModelToDto(Borrowing borrowing) {
		
		BorrowingDto bd = new BorrowingDto();
		
			bd.setId(borrowing.getId());
			bd.setStartDate(borrowing.getStartDate());
			bd.setSupposedEndDate(borrowing.getSupposedEndDate());
			bd.setSecondSupposedEndDate(borrowing.getSecondSupposedEndDate());
			bd.setEffectiveEndDate(borrowing.getEffectiveEndDate());
			bd.setExtended(borrowing.isExtended());
			borrowing.getBook().getLibrary().setListStaffAccount(null);
			borrowing.getBook().setListBorrowing(null);
			bd.setBook(borrowing.getBook());
			borrowing.getUserAccount().setPassword(null);
			borrowing.getUserAccount().setListBorrowing(null);
			bd.setUserAccount(borrowing.getUserAccount());
			
		
		return bd;
	}






}
