package com.philippe75.libraryWS.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;

import com.philippe75.libraryWS.business.contract.manager.BookManager;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

/**
 * <b>Implements BookManager Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("bookManager")
public class BookManagerImpl extends AbstractManager implements BookManager{

	/**
	 * Get all the {@link BookDto} with name required.
	 * 
	 * @param name the name of the book
	 * @return List<BookDto> list of Dto object of {@link Book} with name required.
	 */
	@Override
	public List<BookDto> getListBookByName(String name) throws LibraryServiceException {
		
		List<Book> lb;
		List<BookDto> lbd = new ArrayList<>();

			try {
				lb = getDaoHandler().getBookDao().getListBookByName(name);
				for (Book book : lb) {
					lbd.add(bookModelToDto(book));
				}
			} catch (NoResultException e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new LibraryServiceException("Exception", libraryServiceFaultFactory("1299", e.getMessage()));
			} 
			
			if(lb.size() == 0) {
				throw new LibraryServiceException("NoResultException", libraryServiceFaultFactory("1234", "No entity found for query."));
			}
			

		return lbd;
	}
	
	/**
	 * Method that gets, the 10 first book containing entry in their name.  
	 * 
	 * @param name the string to be contained in the book name
	 * @return List<BookDto> listBookDto of {@link BookDto} containing the entry in their name.
	 */
	@Override
	public List<BookDto> getListBookStartingBy(String name) throws LibraryServiceException {
		
		List<Book> lb; 
		List<BookDto> lbd= new ArrayList<>();
		try {
			lb = getDaoHandler().getBookDao().getListBookStartingBy(name);
			lb.forEach(e -> lbd.add(bookModelToDto(e)));
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
	 * Transform model objects fetched from database to data transfer object.   
	 * 
	 * @param book object fetched from the data layer. 
	 * @return bookDto Dto object of {@link Book}.  
	 */
	protected BookDto bookModelToDto(Book book) {
		
		BookDto bd = new BookDto();
		
			bd.setId(book.getId());
			bd.setName(book.getName());
			bd.setAuthor(book.getAuthor());
			bd.setSummary(book.getSummary());
			bd.setAvailable(book.isAvailable());
			bd.setLibrary(book.getLibrary().getName());
			bd.setGenre(book.getGenre());
			
		
		return bd;
	}






}
