package com.philippe75.libraryWS.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.philippe75.libraryWS.business.contract.manager.BookManager;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.model.book.Book;

@Named("bookManager")
public class BookManagerImpl extends AbstractManager implements BookManager{

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
