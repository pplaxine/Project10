package com.philippe75.libraryWS.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.philippe75.libraryWS.business.contract.manager.BookManager;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.model.book.Book;

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
	public List<BookDto> getListBookByName(String name) {
		
		List<BookDto> listBookDto = new ArrayList<>();
		List<Book> listBook = null;
		
		if(getDaoHandler().getBookDao().getListBookByName(name)!=null){
			listBook = getDaoHandler().getBookDao().getListBookByName(name);
			for (Book book : listBook) {
				listBookDto.add(bookModelToDto(book));
			}
		}else {
			//Log
			System.out.println("plop");
		}
		return listBookDto;
	}
	
	/**
	 * Transform model objects fetched from database to data transfer object.   
	 * 
	 * @param book object fetched from the data layer. 
	 * @return bookDto Dto object of {@link Book}.  
	 */
	private BookDto bookModelToDto(Book book) {
		
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
