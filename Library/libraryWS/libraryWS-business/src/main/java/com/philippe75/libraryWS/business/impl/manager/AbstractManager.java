package com.philippe75.libraryWS.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.BookBooking;
import com.philippe75.libraryWS.model.exception.soap.fault.LibraryServiceFault;

/**
 * <b>Abstract Class that provides access to Dao layer</b>
 * 
 * @see DaoHandler
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public abstract class AbstractManager {
	
	/**
	 * injection of {@link DaoHandler}
	 */
	
	private static DaoHandler daoHandler;
	
	//Unit Test Mock access
	public static void configure(DaoHandler pdaoHandler) {
		daoHandler = pdaoHandler;
	}


	//GETTERS and SETTERS 
	protected DaoHandler getDaoHandler() {
		return daoHandler;
	}
	@Inject
	public void setDaoHandler(DaoHandler daoHandler) {
		AbstractManager.daoHandler = daoHandler;
	}
	
	//UTILITY METHODS 
	/**
	 * Creates a LibraryServiceFault.
	 * 
	 * @param faultCode the code of the fault
	 * @param message the message og the fault
	 * @return LibraryServiceFault
	 */
	protected LibraryServiceFault libraryServiceFaultFactory(String faultCode, String message) {
		LibraryServiceFault af = new LibraryServiceFault();
		af.setFaultCode(faultCode);
		af.setFaultMessage(message);
		return af;
	}
	
    /**
     * Renvoie un {@link Validator} de contraintes
     *
     * @return Validator
     */
    protected Validator getConstraintValidator() {
        Configuration<?> vConfiguration = Validation.byDefaultProvider().configure();
        ValidatorFactory vFactory = vConfiguration.buildValidatorFactory();
        Validator vValidator = vFactory.getValidator();
        return vValidator;
    }
    
	/**
	 * Check from list of book if some are available to borrow.   
	 * 
	 * @param listBook a list of {@link Book} . 
	 * @return List<Book> a list of copies of book available.
	 */
	public List<Book> bookAvailabilityChecker(List<Book> listBook) {
		List<Book> lb = new ArrayList<>();
		listBook.forEach((e) -> {
			if(e.isAvailable()) {
				lb.add(e);
			};
		});
		
		return lb;
	}
	
	/**
	 * Remove from a List<BookBooking> the booking already ended.   
	 * 
	 * @param listBookBooking a list of {@link BookBooking}. 
	 * @return List<BookBooking> a list of booking that are not ended.
	 */
	public List<BookBooking> endedBookingRemover(List<BookBooking> listBookBooking){
		List<BookBooking> lbb = new ArrayList<>();
		listBookBooking.forEach((e)-> {
			if(e.isEnded() == false) {
				lbb.add(e);
			}
		});
		return lbb;
	}
	
	/**
	 * Transform dto objects to model object.   
	 * 
	 * @param bookDto  {@link BookDto} . 
	 * @return book {@link Book}.  
	 */
	protected Book bookDtoToModel(BookDto bookDto) {
		
		Book book = new Book();
		
			book.setId(bookDto.getId());
			book.setName(bookDto.getName());
			book.setAuthor(bookDto.getAuthor());
			book.setSummary(bookDto.getSummary());
			book.setAvailable(bookDto.isAvailable());
			book.setGenre(bookDto.getGenre());
			
		
		return book;
	}



}
