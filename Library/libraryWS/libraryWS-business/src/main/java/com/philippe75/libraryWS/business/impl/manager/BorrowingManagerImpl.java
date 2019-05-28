package com.philippe75.libraryWS.business.impl.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.philippe75.libraryWS.business.contract.manager.BorrowingManager;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.BookBooking;
import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;
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
		if(borrowingDto != null && borrowingDto.getId() != 0 && borrowingDto.getSecondSupposedEndDate() != null) {
			Borrowing bor = borrowingDtoToModel(borrowingDto);
			Boolean isExtended = false;
			
			try {
				isExtended = getDaoHandler().getBorrowingDao().getBorrowingById(bor.getId()).isExtended();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", ex.getMessage()));
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
					
					//throw e; // (throw the  Exception e ) 
					System.out.println(e.getMessage());
					throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
				}
			}
		}else {
			throw new LibraryServiceException("EmptyValueException", libraryServiceFaultFactory("1422","You must complete borrowingId and secondSupposedEndDate Values"));
			
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
				Borrowing borrowing = getDaoHandler().getBorrowingDao().getBorrowingById(borrowingId);
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
	 * Method that creates new borrowing.  
	 * 
	 * @param borrowingDto the dto object of a new borrowing.
	 */
	@Override
	public void createBorrowing(BorrowingDto borrowingDto) throws LibraryServiceException, Exception {
		if(borrowingDto != null) {
			Borrowing borrowing = borrowingDtoToModel(borrowingDto);
			
			String userMemberIdForBorrowing = borrowing.getUserAccount().getUserMemberId();
			List<BookBooking> lbb;
			List<Book> lb;
			List<String> memberIdList = new ArrayList<>();
			try {

				Book book = getDaoHandler().getBookDao().getBookById(borrowing.getBook().getId());
				
				//Règle gestion : exemplar of book already borrowed;  
				if(book.isAvailable() != true) {
					throw new LibraryServiceException("BookAlreadyBorrowedException", libraryServiceFaultFactory("1436", "The book selected hasn't been returned yet.")); 
				}
				
				//Règle de gestion : other members than the firsts in booking list try to borrow the book.  
				try {
					lbb = getDaoHandler().getBookBookingDao().getAllBookingsForABook(borrowing.getBook());
				} catch (NoResultException e) {
					lbb = new ArrayList<>();
				} 
				
				//number of copies of the book available
				lb = getDaoHandler().getBookDao().getListBookByName(borrowing.getBook().getName());
				int lbAvailableSize = bookAvailabilityChecker(lb).size();
				
				//number of Member queuing for the book   
				int bookingQueueSize = lbb.size();
				//Check if x books are available, x people from queuing list can borrow it first.  
				if(bookingQueueSize > 0 && lbAvailableSize > 0) {
					Collections.sort(lbb);
					int i = 0;
					while (i < lbAvailableSize && i < bookingQueueSize ) {
						memberIdList.add(lbb.get(i).getUserAccount().getUserMemberId());
						i++;
					}
					
					if(!memberIdList.contains(userMemberIdForBorrowing)) {
						throw new LibraryServiceException("BookHasBeenBookedException", libraryServiceFaultFactory("1465", "The book selected hasn been booked by an other Member and waiting to be collected."));
					}
				}
				
				UserAccount ua = getDaoHandler().getUserAccountDao().getUserAccountByMemberId(userMemberIdForBorrowing);
				book.setAvailable(false);
				borrowing.setBook(book);
				borrowing.setUserAccount(ua);
				borrowing.setExtended(false);
				borrowing.setStartDate(new Date());

				Set<ConstraintViolation<Borrowing>> violation = getConstraintValidator().validate(borrowing);
				
				//Règle gestion : contraints are not respected
				if(!violation.isEmpty()) {
					throw new LibraryServiceException("ConstraintException",  new ConstraintViolationException(violation), libraryServiceFaultFactory("1587", "One of the constraint is not fulfilled"));
				}	

				getDaoHandler().getBorrowingDao().createBorrowing(borrowing);
				
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));	
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
		
		if(borrowingDto != null && borrowingDto.getId() != 0 ) {
			
			Borrowing borrowing = borrowingDtoToModel(borrowingDto);
			
			Borrowing borrowingDB;
			
			// Check if this borrowing hasn't been ended already - separate try catch for the throw AttributAlreadyFieldException to not be caught in catch Exception
			try {
				borrowingDB = getDaoHandler().getBorrowingDao().getBorrowingById(borrowing.getId());
			} catch (NoResultException ex) {
				System.out.println(ex.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));	
				
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", ex.getMessage()));
			}

			if(borrowingDB.getEffectiveEndDate() == null) {
				try {
					//injection of the book id in the borrowing object
					Book book = new Book();
					book.setId(borrowingDB.getBook().getId());
					borrowing.setBook(book);
					
					getDaoHandler().getBorrowingDao().endBorrowing(borrowing);
					
				} catch (NoResultException e) {
					System.out.println(e.getMessage());
					throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));	
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
					throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
				}
			}else {
				throw new LibraryServiceException("AttributAlreadyFieldException", libraryServiceFaultFactory("1215", "This borrowing has already an effective end Date."));
			}
		}else {
			throw new LibraryServiceException("MissingAttributException", libraryServiceFaultFactory("1214", "You must specify the id and the effectiveEndDate in the BorrowingDto object."));
		}
		
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

	//---- UTILITY METHODS ----------------------------------------------------------------
	
	/**
	 * Transform model objects fetched from database to data transfer object. Also remove the user password att.   
	 * 
	 * @param book object fetched from the data layer. 
	 * @return borrowingDto Dto object of {@link Borrowing}.  
	 */
	protected BorrowingDto borrowingModelToDto(Borrowing borrowing) {
		
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
	
	/**
	 * Transform dto objects to model object.   
	 * 
	 * @param borrowingDto  {@link BorrowingDto} . 
	 * @return borrowing {@link Borrowing}.  
	 */
	protected Borrowing borrowingDtoToModel(BorrowingDto bd) {
		
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
