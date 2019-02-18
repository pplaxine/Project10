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
	 * Method that extend borrowing supposed end date. Also check first if an extention has already been made.  
	 * 
	 * @param Borrowing the borrowing to update.
	 */
	@Override
	public void extendBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException {
		if(borrowingDto != null) {
			Borrowing bor = borrowingDtoToModel(borrowingDto);
			Boolean isExtended = true;
			
			try {
				isExtended = daoHandler.getBorrowingDao().getBorrowingById(bor.getId()).isExtended();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			
			if(bor.isExtended() == true || isExtended == true ) {
				throw new LibraryServiceException("AlreadyExtendedException", libraryServiceFaultFactory("1303", "The borrowing has been already extended. Each borrowing can be extended only once."));
			}else {
				try {
					getDaoHandler().getBorrowingDao().extendBorrowing(bor);
				} catch (NoResultException e) {
					System.out.println(e.getMessage());
					throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
					throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
				}
			}
		}
	}
	
	/**
	 * Method get the borrowing object.  
	 * 
	 * @param borrowingId the borrowing id.
	 * @return BorrowingDto the {@link Borrowing} dto object corresponding to the id.
	 */
	@Override
	public BorrowingDto getBorrowingById(Integer borrowingId) throws LibraryServiceException {
		if(borrowingId != null) {
			try {
				Borrowing borrowing = daoHandler.getBorrowingDao().getBorrowingById(borrowingId);
				BorrowingDto bd = borrowingModelToDto(borrowing);
				return bd;
				
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));	
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
			}
		}
		return null;
	}
	
	
	/**
	 * Method get that creates new borrowing.  
	 * 
	 * @param borrowingDto the dto object of a new borrowing.
	 */
	@Override
	public void createBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException {
		if(borrowingDto != null) {
			Borrowing borrowing = borrowingDtoToModel(borrowingDto);
			try {
				
				Book book = daoHandler.getBookDao().getBookById(borrowing.getBook().getId());
				
				UserAccount ua = daoHandler.getUserAccountDao().getUserAccountByMemberId(borrowing.getUserAccount().getUserMemberId());
				
				borrowing.setBook(book);
				borrowing.setUserAccount(ua);
				
				daoHandler.getBorrowingDao().createBorrowing(borrowing);
				
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));	
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
			}
		}
	}
	
	/**
	 * Method that ends a borrowing when user returns a book.   
	 * 
	 * @param borrowingDto the borrowing comming to a end.
	 */
	@Override
	public void endBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException {
		if(borrowingDto != null && borrowingDto.getId() != 0 && borrowingDto.getEffectiveEndDate() != null) {
			Borrowing borrowing = borrowingDtoToModel(borrowingDto);
			try {
				daoHandler.getBorrowingDao().endBorrowing(borrowing);
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));	
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
			}
		}
		throw new LibraryServiceException("MissingAttributException", libraryServiceFaultFactory("1214", "You must specify the id and the effectiveEndDate in the BorrowingDto object."));
	}
	
	/**
	 * Method that gets, all the borrowings with the either supposed end date or extended supposed end date overdue.  
	 * 
	 * @return List<BorrowingDto> list of {@link BorrowingDto}.
	 */
	@Override
	public List<BorrowingDto> getAllLateBorrowings() throws LibraryServiceException {
		
		List<Borrowing> lb;
		List<BorrowingDto> lbd = new ArrayList<>();

			try {
				lb = getDaoHandler().getBorrowingDao().getAllLateBorrowings();
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
	
	private Borrowing borrowingDtoToModel(BorrowingDto bd) {
		
		Borrowing borrowing = new Borrowing();
		
			borrowing.setId(bd.getId());
			borrowing.setStartDate(bd.getStartDate());
			borrowing.setSupposedEndDate(bd.getSupposedEndDate());
			borrowing.setSecondSupposedEndDate(bd.getSecondSupposedEndDate());
			borrowing.setEffectiveEndDate(bd.getEffectiveEndDate());
			borrowing.setExtended(bd.isExtended());
			borrowing.setBook(bd.getBook());
			borrowing.setUserAccount(bd.getUserAccount());
			
		
		return borrowing;
	}




















}
