package com.philippe75.libraryWebapp.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.philippe75.libraryWebapp.business.contract.manager.BookDtoManager;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.bookServ.LibraryServiceException_Exception;

/**
 * <b>Implements BookDtoManager Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("bookManager")
public class BookDtoManagerImpl extends AbstractManagerServiceAccess implements BookDtoManager {

	/**
	 * Get all the {@link BookDto} with name required.
	 * 
	 * @param bookName the name of the book
	 * @return List<BookDto> listBookDto of {@link BookDto} with name required.
	 */
	@Override
	public List<BookDto> getListBookByName(String bookName) throws LibraryServiceException_Exception {
		String bookNameOnly = getBookNameOnly(bookName);
		
		return getBookSearchService().getListBookByName(bookNameOnly).getItem();
	}
	
	//TODO : Unit test
	/**
	 * Method that gets, all the available copies of the book. 
	 * 
	 * @param name the name of the book. 
	 * @return List<BookDto> listBookDto of {@link BookDto} all the available copies.
	 */
	@Override
	public List<BookDto> getListBookAvailableByName(String bookName) throws LibraryServiceException_Exception {
		List<BookDto> listAvailableBooks = new ArrayList<>();
		String bookNameOnly = getBookNameOnly(bookName);
		
		List<BookDto> lbd = getBookSearchService().getListBookByName(bookNameOnly).getItem();
		lbd.forEach((e) -> {
			if(e.isAvailable()) {
				listAvailableBooks.add(e);
			}
		});
		
		return listAvailableBooks;
	}


	/**
	 * If entry contained in the name of books. Get the 10 first book matching with.  
	 * 
	 * @param name the string to be contained in the book name
	 * @return List<BookDto> listBookDto of {@link BookDto} containing the entry in their name.
	 */
	@Override
	public List<BookDto> getListBookStartingBy(String name) throws LibraryServiceException_Exception {
		return getBookSearchService().getListBookStartingBy(name).getItem();
	}
	
	//------------------- UTILITY METHODE -----------------------------
	
	/**
	 * Return the name of the book without the author.  
	 * 
	 * @param book full name.
	 * @return String book name only.
	 */
	public static String getBookNameOnly(String bookNameWithAuthor) {
		String[] ls = bookNameWithAuthor.split("-");
		String bookNameOnly = ls[0].trim();
		return bookNameOnly;
	}
	
	/**
	 * Return the author of the book without the name.  
	 * 
	 * @param book full name.
	 * @return String Author name only.
	 */
	public static String getBookAuthorOnly(String bookNameWithAuthor) {
		String[] ls = bookNameWithAuthor.split("-");
		String bookAuthorOnly = ls[1].trim();
		return bookAuthorOnly;
	}


 
}
